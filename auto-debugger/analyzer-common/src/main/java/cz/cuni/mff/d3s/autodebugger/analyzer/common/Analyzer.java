package cz.cuni.mff.d3s.autodebugger.analyzer.common;

import cz.cuni.mff.d3s.autodebugger.model.java.RunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.java.Trace;

import java.nio.file.Path;

/**
 * Interface for analyzing instrumented applications and collecting runtime traces.
 * Implementations should handle the execution of instrumented applications and
 * process the collected data into structured traces.
 */
public interface Analyzer {
    
    /**
     * Configures the analyzer with the given run configuration.
     * 
     * @param configuration The run configuration containing analysis parameters
     */
    void configure(RunConfiguration configuration);
    
    /**
     * Runs analysis on the instrumented application and collects runtime traces.
     * 
     * @param instrumentationPath Path to the instrumented application or JAR file
     * @return Trace object containing the collected runtime data
     */
    Trace runAnalysis(Path instrumentationPath);
    
    /**
     * Runs analysis with additional runtime arguments.
     * 
     * @param instrumentationPath Path to the instrumented application
     * @param runtimeArguments Additional arguments to pass to the application
     * @return Trace object containing the collected runtime data
     */
    Trace runAnalysis(Path instrumentationPath, String[] runtimeArguments);
    
    /**
     * Validates that the analyzer can process the given instrumentation.
     * 
     * @param instrumentationPath Path to the instrumented application
     * @throws IllegalArgumentException if the instrumentation is not compatible
     */
    void validateInstrumentation(Path instrumentationPath);
}
