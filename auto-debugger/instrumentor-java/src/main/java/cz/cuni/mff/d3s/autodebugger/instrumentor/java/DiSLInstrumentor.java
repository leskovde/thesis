package cz.cuni.mff.d3s.autodebugger.instrumentor.java;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.Instrumentor;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.InstrumentationModel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.ExportableValue;
import cz.cuni.mff.d3s.autodebugger.model.java.JavaRunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.java.helper.DiSLPathHelper;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.JavaClassIdentifier;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;

/**
 * DiSL-based instrumentor for Java applications.
 * Generates DiSL instrumentation classes and compiles them into JAR files
 * for runtime analysis using the DiSL dynamic instrumentation framework.
 */
@Slf4j
@Builder
public class DiSLInstrumentor implements Instrumentor {

    private final JavaClassIdentifier instrumentationClassName;

    private final JavaRunConfiguration runConfiguration;

    @Builder.Default
    private Path generatedCodeOutputDirectory =
            Path.of("analyzer-disl/src/main/java/cz/cuni/mff/d3s/autodebugger/analyzer/disl/");

    @Getter @Builder.Default
    private Path jarOutputPath = Path.of("analyzer-disl/build/libs/instrumentation.jar");

    @Override
    public List<Path> generateInstrumentation(InstrumentationModel model) {
        var identifierMapping = serializeIdentifiers(Path.of("../identifiers"));
        var templateHandler = new JavaTemplateHandler(new JavaTemplateTransformer("${%s}"));

        // Ensure the Collector.jt template and CollectorRE.java are available in the output directory
        Path collectorTemplate = generatedCodeOutputDirectory.resolve("Collector.jt");
        ensureCollectorTemplateExists(collectorTemplate);
        ensureCollectorREExists(generatedCodeOutputDirectory.resolve("CollectorRE.java"));

        templateHandler.transformFile(
                collectorTemplate,
                generatedCodeOutputDirectory.resolve("Collector.java"),
                Pair.with("PATH", identifierMapping.toAbsolutePath().toString()));
        var instrumentationJarPath = generateDiSLClass(model).flatMap(this::compileDiSLClass);
        return List.of(instrumentationJarPath.orElseThrow());
    }

    private Optional<Path> generateDiSLClass(InstrumentationModel model) {
        var generator = new DiSLClassGenerator(generatedCodeOutputDirectory, model);
        return generator.generateCode();
    }

    private Optional<Path> compileDiSLClass(Path instrumentationSource) {
        var compiler = new DiSLCompiler(jarOutputPath, DiSLPathHelper.getDislClassPathRoot(runConfiguration),
                runConfiguration.getClasspathEntries());
        return compiler.compileDiSLClass(instrumentationSource);
    }

    /**
     * Ensures that the Collector.jt template file exists in the target location.
     * If it doesn't exist, copies it from the analyzer-disl module or from resources.
     */
    private void ensureCollectorTemplateExists(Path targetTemplate) {
        if (Files.exists(targetTemplate)) {
            return; // Template already exists
        }

        try {
            // First, try to copy from the analyzer-disl module (for production use)
            Path sourceTemplate = Path.of("analyzer-disl/src/main/java/cz/cuni/mff/d3s/autodebugger/analyzer/disl/Collector.jt");
            if (Files.exists(sourceTemplate)) {
                Files.createDirectories(targetTemplate.getParent());
                Files.copy(sourceTemplate, targetTemplate);
                log.info("Copied Collector.jt template from analyzer-disl module to: {}", targetTemplate);
                return;
            }

            // If not found, try to load from resources (for test use)
            try (var templateStream = getClass().getResourceAsStream("/Collector.jt")) {
                if (templateStream != null) {
                    Files.createDirectories(targetTemplate.getParent());
                    Files.copy(templateStream, targetTemplate);
                    log.info("Copied Collector.jt template from resources to: {}", targetTemplate);
                    return;
                }
            }

            // If still not found, create a minimal template
            log.warn("Collector.jt template not found, creating minimal template at: {}", targetTemplate);
            createMinimalCollectorTemplate(targetTemplate);

        } catch (IOException e) {
            log.error("Failed to ensure Collector.jt template exists", e);
            throw new RuntimeException("Failed to ensure Collector.jt template exists", e);
        }
    }

    /**
     * Ensures that the CollectorRE.java file exists in the target location.
     * If it doesn't exist, copies it from the analyzer-disl module.
     */
    private void ensureCollectorREExists(Path targetCollectorRE) {
        if (Files.exists(targetCollectorRE)) {
            return; // CollectorRE already exists
        }

        try {
            // First, try to copy from the analyzer-disl module
            Path sourceCollectorRE = Path.of("analyzer-disl/src/main/java/cz/cuni/mff/d3s/autodebugger/analyzer/disl/CollectorRE.java");
            if (Files.exists(sourceCollectorRE)) {
                Files.createDirectories(targetCollectorRE.getParent());
                Files.copy(sourceCollectorRE, targetCollectorRE);
                log.info("Copied CollectorRE.java from analyzer-disl module to: {}", targetCollectorRE);
                return;
            }

            // If not found, create a minimal CollectorRE for testing
            log.warn("CollectorRE.java not found, creating minimal CollectorRE at: {}", targetCollectorRE);
            createMinimalCollectorRE(targetCollectorRE);

        } catch (IOException e) {
            log.error("Failed to ensure CollectorRE.java exists", e);
            throw new RuntimeException("Failed to ensure CollectorRE.java exists", e);
        }
    }

    /**
     * Creates a minimal CollectorRE.java file for testing purposes.
     */
    private void createMinimalCollectorRE(Path targetCollectorRE) throws IOException {
        String minimalCollectorRE = """
            import ch.usi.dag.dislre.REDispatch;

            public class CollectorRE {
              private static final String messageFormat = "[%s]: %s";
              private static final String processName = "Sending process";

              private static short intId = REDispatch.registerMethod("Collector.collectInt");

              public static void collectInt(final int slot, final int i) {
                printPid();
                REDispatch.analysisStart(intId);
                REDispatch.sendInt(slot);
                REDispatch.sendInt(i);
                REDispatch.analysisEnd();
              }

              private static void printPid() {
                System.out.println(
                    String.format(messageFormat, processName, "PID: " + ProcessHandle.current().pid()));
              }
            }
            """;
        Files.createDirectories(targetCollectorRE.getParent());
        Files.writeString(targetCollectorRE, minimalCollectorRE);
    }

    /**
     * Creates a minimal Collector.jt template for testing purposes.
     */
    private void createMinimalCollectorTemplate(Path targetTemplate) throws IOException {
        String minimalTemplate = """
            import ch.usi.dag.dislreserver.remoteanalysis.RemoteAnalysis;
            import ch.usi.dag.dislreserver.shadow.ShadowObject;
            import cz.cuni.mff.d3s.autodebugger.model.java.Trace;
            import cz.cuni.mff.d3s.autodebugger.testgenerator.java.trace.TraceBasedUnitTestGenerator;
            import java.nio.file.Path;

            public class Collector extends RemoteAnalysis {
              private final String messageFormat = "[%s]: %s";
              private final String processName = "Receiving process";
              private final String identifierMappingFilePath = "${PATH}";

              private Trace trace = new Trace();

              public void collectInt(final int slot, final int i) {
                printPid();
                trace.addIntValue(slot, i);
                System.out.println("Collected int: " + i + " from slot: " + slot);
              }

              private void printPid() {
                System.out.println(
                    String.format(messageFormat, processName, "PID: " + ProcessHandle.current().pid()));
              }

              @Override
              public void atExit() {
                System.out.println(String.format(messageFormat, processName, "Exiting analysis..."));
                trace.printSlotValues();
                TraceBasedUnitTestGenerator generator = new TraceBasedUnitTestGenerator(Path.of(identifierMappingFilePath));
                generator.generateTests(trace);
              }

              @Override
              public void objectFree(final ShadowObject netRef) {
                System.out.println("Object free for id " + netRef.getId());
              }
            }
            """;
        Files.createDirectories(targetTemplate.getParent());
        Files.writeString(targetTemplate, minimalTemplate);
    }

    private Path serializeIdentifiers(Path outputDirectory) {
        Map<Integer, ExportableValue> identifierMapping = new HashMap<>();
        for (ExportableValue value : runConfiguration.getExportableValues()) {
            identifierMapping.put(value.getInternalId(), value);
        }
        try {
            if (!outputDirectory.toFile().exists()) {
                if (outputDirectory.toFile().mkdirs()) {
                    log.info("Created directory {}", outputDirectory);
                } else {
                    log.error("Failed to create directory {}", outputDirectory);
                    return null;
                }
            }
            File mappingFile =
                    File.createTempFile("identifierMapping", ".json", outputDirectory.toFile());

            try (FileOutputStream fileOutput = new FileOutputStream(mappingFile);
                 ObjectOutputStream objectStream = new ObjectOutputStream(fileOutput)) {
                objectStream.writeObject(identifierMapping);
            }
            return mappingFile.toPath();
        } catch (IOException e) {
            log.error("Failed to serialize identifier mapping", e);
            return null;
        }
    }
}
