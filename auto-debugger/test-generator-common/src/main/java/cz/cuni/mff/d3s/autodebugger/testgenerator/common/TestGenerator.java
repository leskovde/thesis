package cz.cuni.mff.d3s.autodebugger.testgenerator.common;

import cz.cuni.mff.d3s.autodebugger.model.common.RunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.java.Trace;

import java.nio.file.Path;
import java.util.List;

/**
 * Interface for generating test cases from runtime traces.
 * Implementations should support different test generation strategies
 * and produce executable test files.
 */
public interface TestGenerator {
    
    /**
     * Configures the test generator with the given run configuration.
     * 
     * @param configuration The run configuration containing generation parameters
     */
    void configure(RunConfiguration configuration);
    
    /**
     * Generates tests from the provided trace data.
     * 
     * @param trace Runtime trace data collected during execution
     * @return List of paths to generated test files
     */
    List<Path> generateTests(Trace trace);
    
    /**
     * Generates tests with additional context information.
     * 
     * @param trace Runtime trace data
     * @param context Additional context for test generation
     * @return List of paths to generated test files
     */
    List<Path> generateTests(Trace trace, TestGenerationContext context);
    
    /**
     * Gets the test generation technique used by this generator.
     * 
     * @return Test generation technique identifier
     */
    String getGenerationTechnique();

    /**
     * Validates that the generator can process the given trace.
     * 
     * @param trace The trace to validate
     * @throws IllegalArgumentException if the trace is not compatible
     */
    void validateTrace(Trace trace);
}
