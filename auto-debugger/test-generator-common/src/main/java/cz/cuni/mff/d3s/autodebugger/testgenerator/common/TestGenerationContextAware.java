package cz.cuni.mff.d3s.autodebugger.testgenerator.common;

import cz.cuni.mff.d3s.autodebugger.model.common.trace.Trace;

import java.nio.file.Path;
import java.util.List;

/**
 * Marker interface for test generators that can accept TestGenerationContext.
 * This interface allows the default RunConfiguration-based methods to detect
 * whether a generator supports context-aware test generation.
 */
public interface TestGenerationContextAware {
    
    /**
     * Generates tests with additional context information.
     * 
     * @param trace Runtime trace data
     * @param context Additional context for test generation
     * @return List of paths to generated test files
     */
    List<Path> generateTests(Trace trace, TestGenerationContext context);
}
