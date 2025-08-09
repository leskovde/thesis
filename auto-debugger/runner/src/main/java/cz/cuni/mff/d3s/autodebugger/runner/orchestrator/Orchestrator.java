package cz.cuni.mff.d3s.autodebugger.runner.orchestrator;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.InstrumentationModel;
import cz.cuni.mff.d3s.autodebugger.model.common.RunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.common.TargetLanguage;
import cz.cuni.mff.d3s.autodebugger.model.common.trace.Trace;
import cz.cuni.mff.d3s.autodebugger.runner.args.Arguments;
import cz.cuni.mff.d3s.autodebugger.runner.factories.AnalyzerFactory;
import cz.cuni.mff.d3s.autodebugger.runner.factories.InstrumentationModelFactory;
import cz.cuni.mff.d3s.autodebugger.runner.factories.InstrumentorFactory;
import cz.cuni.mff.d3s.autodebugger.runner.factories.RunConfigurationFactory;
import cz.cuni.mff.d3s.autodebugger.runner.factories.TestGeneratorFactory;
import cz.cuni.mff.d3s.autodebugger.runner.factories.TestRunnerFactory;
import cz.cuni.mff.d3s.autodebugger.runner.strategies.TestGenerationStrategy;
import cz.cuni.mff.d3s.autodebugger.runner.strategies.TestGenerationStrategyProvider;
import cz.cuni.mff.d3s.autodebugger.testrunner.common.TestExecutionResult;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.util.List;

/**
 * Central orchestrator that coordinates the complete auto-debugger workflow.
 * Uses factory pattern to create language-specific components and manages
 * the sequential execution of instrumentation, analysis, test generation, and test execution.
 */
@Slf4j
public class Orchestrator {

    private final RunConfiguration runConfiguration;
    private final String testGenerationStrategy;
    private final String apiKey;

    public Orchestrator(Arguments arguments) {
        runConfiguration = RunConfigurationFactory.createRunConfiguration(arguments);
        testGenerationStrategy = arguments.testGenerationStrategy;
        apiKey = arguments.apiKey;
    }

    /**
     * Builds an instrumentation model from the run configuration.
     * This model contains all the information needed for instrumentation.
     *
     * @return Instrumentation model specific to the instrumentor
     */
    public InstrumentationModel buildInstrumentationModel() {
        return InstrumentationModelFactory.buildInstrumentationModel(runConfiguration);
    }

    /**
     * Creates instrumentation artifacts using language-specific instrumentor.
     * Delegates to factory-created instrumentor to generate instrumentation files.
     */
    public List<Path> createInstrumentation(InstrumentationModel instrumentationModel) {
        var instrumentor = InstrumentorFactory.createInstrumentor(runConfiguration);
        return instrumentor.generateInstrumentation(instrumentationModel);
    }

    /**
     * Executes the instrumented application and collects runtime traces.
     * Uses factory-created analyzer to run the instrumented application and capture data.
     */
    public Trace runAnalysis(List<Path> instrumentationPaths) {
        var analyzer = AnalyzerFactory.createAnalyzer(runConfiguration);
        return analyzer.runAnalysis(instrumentationPaths);
    }

    /**
     * Generates test files from collected runtime traces.
     * Uses strategy pattern to select appropriate test generation technique.
     */
    public List<Path> generateTests(Trace trace) {
        var testGenerator = TestGeneratorFactory.createTestGenerator(runConfiguration, testGenerationStrategy, apiKey);
        return testGenerator.generateTests(trace);
    }

    /**
     * Executes generated test files and returns results.
     * Uses language-specific test runner to compile and execute tests.
     */
    public TestExecutionResult runTests(List<Path> testFiles) {
        var testRunner = TestRunnerFactory.createTestRunner(runConfiguration);
        return testRunner.executeTests(testFiles);
    }

    /**
     * Returns the target language for this orchestrator instance.
     */
    public TargetLanguage getSupportedLanguage() {
        return runConfiguration.getLanguage();
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

    /**
     * Gets the test generation strategy configured for this orchestrator.
     *
     * @return The test generation strategy identifier
     */
    public String getTestGenerationStrategy() {
        return testGenerationStrategy;
    }
}
