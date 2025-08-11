package cz.cuni.mff.d3s.autodebugger.testgenerator.common;

import cz.cuni.mff.d3s.autodebugger.model.common.RunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.common.trace.Trace;

import java.nio.file.Path;
import java.util.List;

/**
 * Interface for trace-based test generation.
 * Implementations generate test cases by analyzing runtime traces.
 */
public interface TraceBasedGenerator {

    /**
     * Generates tests from the provided trace data.
     *
     * @param trace Runtime trace data collected during execution
     * @return List of paths to generated test files
     */
    List<Path> generateTests(Trace trace);

    /**
     * Generates tests using RunConfiguration directly.
     * This method creates TestGenerationContext internally from the RunConfiguration,
     * eliminating the need for manual context construction.
     *
     * @param trace Runtime trace data
     * @param configuration Run configuration containing structured identifiers
     * @return List of paths to generated test files
     */
    default List<Path> generateTests(Trace trace, RunConfiguration configuration) {
        // Default implementation creates context from configuration and delegates
        TestGenerationContext context = TestGenerationContextFactory.createFromRunConfiguration(configuration);

        // Check if the implementation has a method that accepts TestGenerationContext
        if (this instanceof TestGenerationContextAware) {
            return ((TestGenerationContextAware) this).generateTests(trace, context);
        }

        // Fallback to basic method
        return generateTests(trace);
    }
}
