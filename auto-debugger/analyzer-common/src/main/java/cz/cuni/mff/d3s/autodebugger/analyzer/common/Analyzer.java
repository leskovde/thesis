package cz.cuni.mff.d3s.autodebugger.analyzer.common;

import java.nio.file.Path;
import java.util.List;

/**
 * Interface for analyzing instrumented applications and collecting runtime traces.
 * Implementations should handle the execution of instrumented applications and
 * process the collected data into structured traces.
 */
public interface Analyzer {
    
    /**
     * Runs analysis on the instrumented application and generates tests inside the analysis process.
     *
     * @param instrumentationPaths Path to the instrumented application's components
     * @return List of generated test file paths produced by the analysis process
     */
    List<Path> runAnalysis(List<Path> instrumentationPaths);
    
    /**
     * Validates that the analyzer can process the given instrumentation.
     * 
     * @param instrumentationPaths Path to the instrumented application's components
     * @throws IllegalArgumentException if the instrumentation is not compatible
     */
    void validateInstrumentation(List<Path> instrumentationPaths);
}
