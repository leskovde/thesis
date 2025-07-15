package cz.cuni.mff.d3s.autodebugger.testgenerator.common;

import cz.cuni.mff.d3s.autodebugger.model.java.Trace;

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
     */
    void configure(LLMConfiguration config);
}
