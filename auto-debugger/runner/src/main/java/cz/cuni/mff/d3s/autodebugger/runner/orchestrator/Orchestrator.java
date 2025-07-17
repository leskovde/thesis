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
 * Interface for orchestrating the creation and coordination of core components
 * (instrumentor, analyzer, test-generator, test-runner) based on language-specific strategies.
 */
@Slf4j
public class Orchestrator {

    private final RunConfiguration runConfiguration;
    private final String testGenerationStrategy;

    public Orchestrator(Arguments arguments) {
        runConfiguration = RunConfigurationFactory.createRunConfiguration(arguments);
        testGenerationStrategy = arguments.testGenerationStrategy;
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

    public List<Path> createInstrumentation(InstrumentationModel instrumentationModel) {
        var instrumentor = InstrumentorFactory.createInstrumentor(runConfiguration);
        return instrumentor.generateInstrumentation(instrumentationModel);
    }

    public Trace runAnalysis(List<Path> instrumentationPaths) {
        var analyzer = AnalyzerFactory.createAnalyzer(runConfiguration);
        return analyzer.runAnalysis(instrumentationPaths);
    }

    public List<Path> generateTests(Trace trace) {
        var testGenerator = TestGeneratorFactory.createTestGenerator(runConfiguration, testGenerationStrategy);
        return testGenerator.generateTests(trace);
    }

    public TestExecutionResult runTests(List<Path> testFiles) {
        var testRunner = TestRunnerFactory.createTestRunner(runConfiguration);
        return testRunner.executeTests(testFiles);
    }

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
}
