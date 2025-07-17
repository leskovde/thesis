package cz.cuni.mff.d3s.autodebugger.runner.orchestrator;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.InstrumentationModel;
import cz.cuni.mff.d3s.autodebugger.model.common.RunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.common.TargetLanguage;
import cz.cuni.mff.d3s.autodebugger.model.common.trace.Trace;
import cz.cuni.mff.d3s.autodebugger.runner.args.Arguments;
import cz.cuni.mff.d3s.autodebugger.runner.parsing.MethodSignatureParsingStrategy;
import cz.cuni.mff.d3s.autodebugger.runner.parsing.MethodSignatureParsingStrategyFactory;
import cz.cuni.mff.d3s.autodebugger.runner.strategies.InstrumentationStrategy;
import cz.cuni.mff.d3s.autodebugger.runner.strategies.TestGenerationStrategy;
import cz.cuni.mff.d3s.autodebugger.runner.strategies.TestGenerationStrategyProvider;
import cz.cuni.mff.d3s.autodebugger.testrunner.common.TestExecutionResult;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.util.List;

/**
 * Interface for orchestrating the creation and coordination of core components
 * (instrumentor, analyzer, test-generator, test-runner) based on language-specific strategies.
 */
@Slf4j
public class Orchestrator {

    private final TargetLanguage language;
    private final RunConfiguration runConfiguration;

    public Orchestrator(Arguments arguments) {
        this.language = arguments.language;
        runConfiguration = createRunConfiguration(arguments);
    }

    /**
     * Creates a run configuration from command line arguments.
     *
     * @param args Command line arguments
     * @return Language-specific run configuration
     */
    public RunConfiguration createRunConfiguration(Arguments arguments) {

    }

    /**
     * Builds an instrumentation model from the run configuration.
     * This model contains all the information needed for instrumentation.
     *
     * @return Instrumentation model specific to the instrumentor
     */
    public InstrumentationModel buildInstrumentationModel() {

//        log.info("Building DiSL instrumentation model");
//
//        if (!language.getIdentifier().equals(runConfiguration.getLanguage())) {
//            throw new IllegalArgumentException("Expected Java run configuration, got: " + runConfiguration.getLanguage());
//        }
//
//        // Create a temporary instrumentor to build the model
//        // This is a bit of a circular dependency, but the DiSLModel needs an Instrumentor
//        Instrumentor tempInstrumentor = DiSLInstrumentor.builder()
//                .applicationJarPath(runConfiguration.getApplicationPath())
//                .className(runConfiguration.getTargetMethod().getOwnerClassIdentifier())
//                .method(runConfiguration.getTargetMethod())
//                .exportedValues(runConfiguration.getExportableValues())
//                .classpath(runConfiguration.getClasspathEntries())
//                .build();
//
//        DiSLModel model = new DiSLModel(tempInstrumentor);
//        log.info("Successfully built DiSL instrumentation model");
//        return model;
    }

    public List<Path> createInstrumentation(InstrumentationModel instrumentationModel) {
//        log.info("Creating DiSL instrumentor");
//
//        if (!(instrumentationModel instanceof DiSLModel)) {
//            throw new IllegalArgumentException("Expected DiSLModel, got: " + instrumentationModel.getClass().getSimpleName());
//        }
//
//        // Extract information from the model to create the instrumentor
//        // Note: This is a simplified approach. In practice, the model should contain
//        // all the necessary information to reconstruct the instrumentor
//        DiSLModel dislModel = (DiSLModel) instrumentationModel;
//
//        // For now, we'll need to get the information from somewhere else
//        // This is a limitation of the current DiSLModel design
//        throw new UnsupportedOperationException("Creating instrumentor from model not yet fully implemented");
    }

    public Trace runAnalysis(List<Path> instrumentationPaths) {
//        log.info("Creating Java analyzer");
//
//        JavaAnalyzer analyzer = new JavaAnalyzer();
//        analyzer.configure(runConfiguration);
//
//        log.info("Successfully created and configured Java analyzer");
//        return analyzer;
    }

    public List<Path> generateTests(Trace trace) {
//        log.info("Creating Java test generator with technique: {}", generationTechnique);
//
//        try {
//            // For now, we only support trace-based generation
//            // In the future, this would use a factory pattern based on the technique
//            if (!generationTechnique.startsWith("trace-based")) {
//                throw new UnsupportedOperationException("Test generation technique not yet supported: " + generationTechnique);
//            }
//
//            // Create the trace-based test generator
//            // Note: This would need to be enhanced to support different techniques
//            TraceBasedUnitTestGenerator generator = new TraceBasedUnitTestGenerator(Path.of("identifiers"));
//
//            log.info("Successfully created Java test generator with technique: {}", generationTechnique);
//            return new TestGeneratorAdapter(generator, runConfiguration, generationTechnique);
//        } catch (Exception e) {
//            log.error("Failed to create Java test generator", e);
//            throw new RuntimeException("Failed to create test generator", e);
//        }
    }

    public TestExecutionResult runTests(List<Path> testFiles) {
//        log.info("Creating Java test runner");
//
//        try {
//            JUnitTestRunner testRunner = new JUnitTestRunner();
//            testRunner.configure(runConfiguration);
//
//            log.info("Successfully created Java test runner");
//            return testRunner;
//        } catch (Exception e) {
//            log.error("Failed to create Java test runner", e);
//            throw new RuntimeException("Failed to create test runner", e);
//        }
    }

    public TargetLanguage getSupportedLanguage() {
        return language;
    }

    /**
     * Gets the available test generation techniques for this language.
     *
     * @return Array of available test generation technique identifiers
     */
    public String[] getAvailableTestGenerationTechniques() {
        return TestGenerationStrategyProvider.getAvailableStrategies()
                .stream()
                .map(TestGenerationStrategy::getId)
                .toArray(String[]::new);
    }
}
