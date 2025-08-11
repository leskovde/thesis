package cz.cuni.mff.d3s.autodebugger.runner.orchestrator;

import cz.cuni.mff.d3s.autodebugger.model.common.RunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.common.trace.Trace;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TestGenerationContext;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TestGenerator;
import cz.cuni.mff.d3s.autodebugger.testgenerator.java.trace.TraceBasedUnitTestGenerator;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.util.List;

/**
 * Adapter that bridges between the existing TraceBasedUnitTestGenerator
 * and the new TestGenerator interface. This allows the orchestrator to use
 * existing test generation implementations while providing a consistent interface.
 */
@Slf4j
public class TestGeneratorAdapter implements TestGenerator {
    
    private final TraceBasedUnitTestGenerator delegate;
    private RunConfiguration configuration;
    private final String generationTechnique;
    
    public TestGeneratorAdapter(TraceBasedUnitTestGenerator delegate, RunConfiguration configuration) {
        this.delegate = delegate;
        this.configuration = configuration;
        this.generationTechnique = "trace-based-basic";
    }
    
    public TestGeneratorAdapter(TraceBasedUnitTestGenerator delegate, RunConfiguration configuration, String generationTechnique) {
        this.delegate = delegate;
        this.configuration = configuration;
        this.generationTechnique = generationTechnique;
    }
    
    @Override
    public void configure(RunConfiguration configuration) {
        this.configuration = configuration;
        log.debug("Configured test generator adapter with run configuration");
    }
    
    @Override
    public List<Path> generateTests(Trace trace) {
        log.info("Generating tests using adapter with basic trace method");
        validateTrace(trace);
        return delegate.generateTests(trace);
    }
    
    @Override
    public List<Path> generateTests(Trace trace, TestGenerationContext context) {
        log.info("Generating tests using adapter with context");
        validateTrace(trace);
        return delegate.generateTests(trace, context);
    }

    @Override
    public List<Path> generateTests(Trace trace, RunConfiguration configuration) {
        log.info("Generating tests using adapter with RunConfiguration directly");
        validateTrace(trace);

        // Check if the delegate supports RunConfiguration directly
        if (delegate instanceof cz.cuni.mff.d3s.autodebugger.testgenerator.common.TraceBasedGenerator) {
            return delegate.generateTests(trace, configuration);
        } else {
            // Fallback to default implementation
            return TestGenerator.super.generateTests(trace, configuration);
        }
    }
    
    @Override
    public String getGenerationTechnique() {
        return generationTechnique;
    }

    @Override
    public void validateTrace(Trace trace) {
        if (trace == null) {
            throw new IllegalArgumentException("Trace cannot be null");
        }
        
        if (configuration == null) {
            throw new IllegalStateException("Test generator must be configured before validating trace");
        }
        
        log.debug("Trace validation passed for test generation");
    }
}
