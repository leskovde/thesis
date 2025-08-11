package cz.cuni.mff.d3s.autodebugger.testgenerator.common;

import cz.cuni.mff.d3s.autodebugger.model.common.RunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.common.trace.Trace;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.exceptions.LLMConfigurationException;

import java.nio.file.Path;
import java.util.List;

/**
 * Interface for LLM-based test generation.
 * Implementations use Large Language Models to generate semantically rich test cases
 * based on runtime traces and source code analysis.
 */
public interface LLMBasedGenerator {
    
    /**
     * Generates tests using LLM-based analysis of the trace and source code.
     * 
     * @param trace Runtime trace data collected during execution
     * @param sourceCodePath Path to the source code file containing the target method
     * @return List of paths to generated test files
     */
    List<Path> generateTests(Trace trace, Path sourceCodePath);
    
    /**
     * Generates tests with additional context information.
     * 
     * @param trace Runtime trace data
     * @param sourceCodePath Path to source code
     * @param context Additional context for test generation
     * @return List of paths to generated test files
     */
    List<Path> generateTests(Trace trace, Path sourceCodePath, TestGenerationContext context);
    
    /**
     * Configures the LLM provider and settings.
     *
     * @param config LLM configuration including API keys, model settings, etc.
     * @throws LLMConfigurationException if the configuration is invalid
     */
    void configure(LLMConfiguration config) throws LLMConfigurationException;

    /**
     * Generates tests using RunConfiguration directly.
     * This method creates TestGenerationContext internally from the RunConfiguration,
     * eliminating the need for manual context construction.
     *
     * @param trace Runtime trace data
     * @param sourceCodePath Path to the source code file containing the target method
     * @param configuration Run configuration containing structured identifiers
     * @return List of paths to generated test files
     */
    default List<Path> generateTests(Trace trace, Path sourceCodePath, RunConfiguration configuration) {
        // Default implementation creates context from configuration and delegates
        TestGenerationContext context = TestGenerationContextFactory.createFromRunConfiguration(configuration);
        return generateTests(trace, sourceCodePath, context);
    }
}
