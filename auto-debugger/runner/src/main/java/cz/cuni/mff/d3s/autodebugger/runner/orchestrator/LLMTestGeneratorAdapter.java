package cz.cuni.mff.d3s.autodebugger.runner.orchestrator;

import cz.cuni.mff.d3s.autodebugger.model.common.RunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.common.trace.Trace;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TestGenerationContext;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TestGenerator;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TestNamingStrategy;
import cz.cuni.mff.d3s.autodebugger.testgenerator.java.llm.LLMBasedTestGenerator;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.util.List;

/**
 * Adapter for LLM-based test generators to work with the orchestrator framework.
 * This adapter bridges the gap between the orchestrator's TestGenerator interface
 * and the LLMBasedTestGenerator implementation.
 */
@Slf4j
public class LLMTestGeneratorAdapter implements TestGenerator {
    
    private final LLMBasedTestGenerator delegate;
    private RunConfiguration configuration;
    private final String generationTechnique;
    
    public LLMTestGeneratorAdapter(LLMBasedTestGenerator delegate, RunConfiguration configuration, String generationTechnique) {
        this.delegate = delegate;
        this.configuration = configuration;
        this.generationTechnique = generationTechnique;
    }
    
    @Override
    public void configure(RunConfiguration configuration) {
        this.configuration = configuration;
        log.debug("Configured LLM test generator adapter with run configuration");
    }
    
    @Override
    public List<Path> generateTests(Trace trace) {
        log.info("Generating LLM-based tests using adapter");
        validateTrace(trace);
        
        // Create context for LLM test generation
        TestGenerationContext context = createTestGenerationContext();
        
        // Use the source code path from configuration
        Path sourceCodePath = configuration.getSourceCodePath();
        
        return delegate.generateTests(trace, sourceCodePath, context);
    }
    
    @Override
    public List<Path> generateTests(Trace trace, TestGenerationContext context) {
        log.info("Generating LLM-based tests with custom context using adapter");
        validateTrace(trace);
        
        // Use the source code path from configuration
        Path sourceCodePath = configuration.getSourceCodePath();
        
        return delegate.generateTests(trace, sourceCodePath, context);
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

        // Note: The Trace class doesn't have getInvocations() method
        // This is a basic validation - more specific validation can be added later
        log.debug("Trace validation passed for LLM-based test generation");
    }
    
    private TestGenerationContext createTestGenerationContext() {
        return TestGenerationContext.builder()
                .targetMethodSignature(extractMethodSignature())
                .targetClassName(extractClassName())
                .packageName(extractPackageName())
                .outputDirectory(configuration.getOutputDirectory())
                .testFramework("JUnit5")
                .maxTestCount(20)
                .generateEdgeCases(true)
                .generateNegativeTests(true)
                .namingStrategy(TestNamingStrategy.DESCRIPTIVE)
                .build();
    }
    
    private String extractMethodSignature() {
        // Extract method signature from configuration
        if (configuration.getTargetMethod() != null) {
            return configuration.getTargetMethod().toString();
        }
        return "unknownMethod";
    }
    
    private String extractClassName() {
        // Extract class name from method signature or configuration
        String methodSignature = extractMethodSignature();
        if (methodSignature.contains(".")) {
            String[] parts = methodSignature.split("\\.");
            if (parts.length >= 2) {
                return parts[parts.length - 2]; // Get class name (second to last part)
            }
        }
        return "UnknownClass";
    }
    
    private String extractPackageName() {
        // Extract package name from method signature or configuration
        String methodSignature = extractMethodSignature();
        if (methodSignature.contains(".")) {
            String[] parts = methodSignature.split("\\.");
            if (parts.length >= 3) {
                // Join all parts except the last two (class and method)
                StringBuilder packageName = new StringBuilder();
                for (int i = 0; i < parts.length - 2; i++) {
                    if (i > 0) packageName.append(".");
                    packageName.append(parts[i]);
                }
                return packageName.toString();
            }
        }
        return "";
    }
}
