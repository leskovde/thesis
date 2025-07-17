package cz.cuni.mff.d3s.autodebugger.instrumentor.java;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.Instrumentor;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.InstrumentationModel;

import java.io.*;
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
        templateHandler.transformFile(
                generatedCodeOutputDirectory.resolve("Collector.jt"),
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
