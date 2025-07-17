package cz.cuni.mff.d3s.autodebugger.analyzer.common;

import cz.cuni.mff.d3s.autodebugger.model.common.trace.Trace;

import java.nio.file.Path;
import java.util.List;

/**
 * Interface for analyzing instrumented applications and collecting runtime traces.
 * Implementations should handle the execution of instrumented applications and
 * process the collected data into structured traces.
 */
public interface Analyzer {
    
    /**
     * Runs analysis on the instrumented application and collects runtime traces.
     * 
     * @param instrumentationPaths Path to the instrumented application's components
     * @return Trace object containing the collected runtime data
     */
    Trace runAnalysis(List<Path> instrumentationPaths);
    
    /**
     * Validates that the analyzer can process the given instrumentation.
     * 
     * @param instrumentationPaths Path to the instrumented application's components
     * @throws IllegalArgumentException if the instrumentation is not compatible
     */
    void validateInstrumentation(List<Path> instrumentationPaths);
}
