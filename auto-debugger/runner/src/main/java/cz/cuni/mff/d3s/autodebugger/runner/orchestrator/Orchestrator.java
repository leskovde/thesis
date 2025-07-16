package cz.cuni.mff.d3s.autodebugger.runner.orchestrator;

import cz.cuni.mff.d3s.autodebugger.analyzer.common.Analyzer;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.Instrumentor;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Model;
import cz.cuni.mff.d3s.autodebugger.model.common.RunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.common.TargetLanguage;
import cz.cuni.mff.d3s.autodebugger.runner.args.Arguments;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TestGenerator;
import cz.cuni.mff.d3s.autodebugger.testrunner.common.TestRunner;

/**
 * Interface for orchestrating the creation and coordination of core components
 * (instrumentor, analyzer, test-generator, test-runner) based on language-specific strategies.
 */
public interface Orchestrator {
    
    /**
     * Creates a run configuration from command line arguments.
     * 
     * @param args Command line arguments
     * @return Language-specific run configuration
     */
    RunConfiguration createRunConfiguration(Arguments args);
    
    /**
     * Builds an instrumentation model from the run configuration.
     * This model contains all the information needed for instrumentation.
     * 
     * @param runConfiguration The run configuration
     * @return Instrumentation model specific to the instrumentor
     */
    Model buildInstrumentationModel(RunConfiguration runConfiguration);
    
    /**
     * Creates an instrumentor instance configured for the target language.
     * 
     * @param instrumentationModel The instrumentation model
     * @return Configured instrumentor instance
     */
    Instrumentor createInstrumentor(Model instrumentationModel);
    
    /**
     * Creates an analyzer instance configured for the target language.
     * 
     * @param runConfiguration The run configuration
     * @return Configured analyzer instance
     */
    Analyzer createAnalyzer(RunConfiguration runConfiguration);
    
    /**
     * Creates a test generator instance configured for the target language and technique.
     * 
     * @param runConfiguration The run configuration
     * @param generationTechnique The test generation technique to use
     * @return Configured test generator instance
     */
    TestGenerator createTestGenerator(RunConfiguration runConfiguration, String generationTechnique);
    
    /**
     * Creates a test generator instance with the default technique for this language.
     * 
     * @param runConfiguration The run configuration
     * @return Configured test generator instance
     */
    TestGenerator createTestGenerator(RunConfiguration runConfiguration);
    
    /**
     * Creates a test runner instance configured for the target language.
     * 
     * @param runConfiguration The run configuration
     * @return Configured test runner instance
     */
    TestRunner createTestRunner(RunConfiguration runConfiguration);
    
    /**
     * Gets the language supported by this orchestrator.
     *
     * @return Target language enum value
     */
    TargetLanguage getSupportedLanguage();
    
    /**
     * Gets the available test generation techniques for this language.
     * 
     * @return Array of available test generation technique identifiers
     */
    String[] getAvailableTestGenerationTechniques();
    
    /**
     * Gets the default test generation technique for this language.
     * 
     * @return Default test generation technique identifier
     */
    String getDefaultTestGenerationTechnique();
}
