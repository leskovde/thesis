package cz.cuni.mff.d3s.autodebugger.testgenerator.java.llm;

import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.ExportableValue;
import cz.cuni.mff.d3s.autodebugger.model.common.trace.TemporalTrace;
import cz.cuni.mff.d3s.autodebugger.model.common.trace.Trace;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.JavaValueIdentifier;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.*;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.exceptions.LLMConfigurationException;
import cz.cuni.mff.d3s.autodebugger.testgenerator.java.llm.exceptions.TestGenerationWorkflowException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * LLM-based test generator that uses Large Language Models to generate
 * semantically rich and comprehensive test suites based on runtime traces
 * and source code analysis.
 */
@Slf4j
@RequiredArgsConstructor
public class LLMBasedTestGenerator implements LLMBasedGenerator {

    private LLMConfiguration llmConfig;
    private final AnthropicClient anthropicClient;
    private final PromptBuilder promptBuilder;
    private final CodeValidator codeValidator;

    @Override
    public void configure(LLMConfiguration config) throws LLMConfigurationException {
        if (config == null) {
            throw new LLMConfigurationException("LLM configuration cannot be null");
        }
        this.llmConfig = config;

        // Configure the Anthropic client with the same configuration
        anthropicClient.configure(config);

        log.info("Configured LLM-based test generator with model: {}", config.getModelName());
    }
    
    @Override
    public List<Path> generateTests(Trace trace, Path sourceCodePath) {
        return generateTests(trace, sourceCodePath, createDefaultContext());
    }
    
    @Override
    public List<Path> generateTests(Trace trace, Path sourceCodePath, TestGenerationContext context) {
        // Validate parameters
        if (trace == null) {
            throw new TestGenerationWorkflowException("Trace cannot be null", "Parameter Validation", sourceCodePath != null ? sourceCodePath.toString() : null, null);
        }
        if (sourceCodePath == null) {
            throw new TestGenerationWorkflowException("Source code path cannot be null", "Parameter Validation", null, null);
        }
        if (context == null) {
            throw new TestGenerationWorkflowException("Test generation context cannot be null", "Parameter Validation", sourceCodePath.toString(), null);
        }
        if (!Files.exists(sourceCodePath)) {
            throw new TestGenerationWorkflowException("Source code path does not exist: " + sourceCodePath, "Parameter Validation", sourceCodePath.toString(), null);
        }
        if (!Files.isRegularFile(sourceCodePath)) {
            throw new TestGenerationWorkflowException("Source code path must be a file, not a directory: " + sourceCodePath, "Parameter Validation", sourceCodePath.toString(), null);
        }

        log.info("Generating LLM-based tests for method: {}", context.getTargetMethodSignature());

        if (llmConfig == null) {
            throw new IllegalStateException("LLM configuration must be set before generating tests");
        }

        try {
            // Read source code
            String sourceCode = Files.readString(sourceCodePath);

            // Build context for LLM prompt
            LLMPromptContext promptContext = buildPromptContext(trace, sourceCode, context);

            // Generate initial test suite
            String prompt = promptBuilder.buildTestGenerationPrompt(promptContext);
            String generatedCode = anthropicClient.generateCode(prompt);

            // Validate and refine if needed
            if (llmConfig.isEnableIterativeRefinement()) {
                generatedCode = refineGeneratedCode(generatedCode, promptContext);
            }

            // Validate final code
            if (llmConfig.isValidateGeneratedCode()) {
                CodeValidationResult validation = codeValidator.validate(generatedCode);
                if (!validation.isValid()) {
                    log.warn("Generated code validation failed: {}", validation.getErrors());
                    // Attempt one more refinement
                    generatedCode = fixValidationErrors(generatedCode, validation, promptContext);
                }
            }

            // Write test file
            Path testFile = writeTestFile(generatedCode, context);

            log.info("Successfully generated LLM-based test file: {}", testFile);
            return List.of(testFile);

        } catch (TestGenerationWorkflowException e) {
            // Re-throw workflow exceptions
            throw e;
        } catch (Exception e) {
            log.error("Failed to generate LLM-based tests", e);
            throw new TestGenerationWorkflowException(
                "LLM-based test generation failed: " + e.getMessage(),
                "Test Generation",
                sourceCodePath.toString(),
                context.getTargetMethodSignature(),
                e
            );
        }
    }

    /**
     * Enhanced method that generates tests from an TemporalTrace with temporal information.
     * This provides richer context to the LLM for generating more sophisticated tests.
     */
    public List<Path> generateTests(TemporalTrace enhancedTrace, Path sourceCodePath, TestGenerationContext context) {
        // Validate parameters
        if (enhancedTrace == null) {
            throw new IllegalArgumentException("Enhanced trace cannot be null");
        }
        if (sourceCodePath == null) {
            throw new IllegalArgumentException("Source code path cannot be null");
        }
        if (context == null) {
            throw new IllegalArgumentException("Test generation context cannot be null");
        }
        if (!Files.exists(sourceCodePath)) {
            throw new IllegalArgumentException("Source code path does not exist: " + sourceCodePath);
        }
        if (!Files.isRegularFile(sourceCodePath)) {
            throw new IllegalArgumentException("Source code path must be a file, not a directory: " + sourceCodePath);
        }

        log.info("Generating LLM-based tests from enhanced trace for method: {}", context.getTargetMethodSignature());

        if (llmConfig == null) {
            throw new IllegalStateException("LLM configuration must be set before generating tests");
        }

        try {
            // Read source code
            String sourceCode = Files.readString(sourceCodePath);

            // Build enhanced context for LLM prompt with temporal data
            LLMPromptContext promptContext = buildEnhancedPromptContext(enhancedTrace, sourceCode, context);

            // Generate initial test suite
            String prompt = promptBuilder.buildTestGenerationPrompt(promptContext);
            String generatedCode = anthropicClient.generateCode(prompt);

            // Validate and refine if needed
            if (llmConfig.isEnableIterativeRefinement()) {
                generatedCode = refineGeneratedCode(generatedCode, promptContext);
            }

            // Validate final code
            if (llmConfig.isValidateGeneratedCode()) {
                CodeValidationResult validation = codeValidator.validate(generatedCode);
                if (!validation.isValid()) {
                    log.warn("Generated code validation failed: {}", validation.getErrors());
                    // Attempt one more refinement
                    generatedCode = fixValidationErrors(generatedCode, validation, promptContext);
                }
            }

            // Write test file
            Path testFile = writeTestFile(generatedCode, context);

            log.info("Successfully generated enhanced LLM-based test file: {}", testFile);
            return List.of(testFile);

        } catch (IllegalArgumentException | IllegalStateException e) {
            // Re-throw validation exceptions
            throw e;
        } catch (Exception e) {
            log.error("Failed to generate enhanced LLM-based tests", e);
            return List.of();
        }
    }
    
    private LLMPromptContext buildPromptContext(Trace trace, String sourceCode, TestGenerationContext context) {
        return LLMPromptContext.builder()
                .sourceCode(sourceCode)
                .targetMethodSignature(context.getTargetMethodSignature())
                .targetClassName(context.getTargetClassName())
                .packageName(context.getPackageName())
                .testFramework(context.getTestFramework())
                .traceData(formatTraceData(trace))
                .maxTestCount(context.getMaxTestCount())
                .generateEdgeCases(context.isGenerateEdgeCases())
                .generateNegativeTests(context.isGenerateNegativeTests())
                .namingStrategy(context.getNamingStrategy())
                .build();
    }
    
    /**
     * Builds enhanced prompt context from an TemporalTrace with temporal information.
     */
    private LLMPromptContext buildEnhancedPromptContext(TemporalTrace enhancedTrace, String sourceCode, TestGenerationContext context) {
        return LLMPromptContext.builder()
                .sourceCode(sourceCode)
                .targetMethodSignature(context.getTargetMethodSignature())
                .targetClassName(context.getTargetClassName())
                .packageName(context.getPackageName())
                .testFramework(context.getTestFramework())
                .traceData(formatTemporalTraceData(enhancedTrace))
                .maxTestCount(context.getMaxTestCount())
                .generateEdgeCases(context.isGenerateEdgeCases())
                .generateNegativeTests(context.isGenerateNegativeTests())
                .namingStrategy(context.getNamingStrategy())
                .additionalInstructions("This trace contains temporal execution data with precise event ordering. " +
                                       "Use this information to generate tests that capture the evolution of state over time.")
                .build();
    }

    private String formatTraceData(Trace trace) {
        StringBuilder sb = new StringBuilder();
        sb.append("Runtime Trace Data:\n");

        // Format trace data for LLM consumption
        sb.append("// Observed runtime values during execution:\n");

        // Add byte values
        trace.getByteValues(0).forEach(value ->
            sb.append("// Byte value observed: ").append(value).append("\n"));

        // Add int values
        trace.getIntValues(0).forEach(value ->
            sb.append("// Int value observed: ").append(value).append("\n"));

        // Add boolean values
        trace.getBooleanValues(0).forEach(value ->
            sb.append("// Boolean value observed: ").append(value).append("\n"));

        // TODO: Add more comprehensive trace formatting based on identifier mapping

        return sb.toString();
    }

    /**
     * Formats TemporalTrace data for LLM consumption with temporal context.
     */
    private String formatTemporalTraceData(TemporalTrace trace) {
        StringBuilder sb = new StringBuilder();
        sb.append("Enhanced Runtime Trace Data with Temporal Information:\n");
        sb.append("// ").append(trace.getSummary().replace("\n", "\n// ")).append("\n\n");

        // Get event range for context
        Optional<int[]> eventRange = trace.getEventIndexRange();
        eventRange.ifPresent(range -> sb.append("// Execution timeline: events ").append(range[0]).append(" to ").append(range[1]).append("\n\n"));

        // Format variable histories
        for (ExportableValue identifier : trace.getTrackedIdentifiers()) {
            sb.append("// Variable: ").append(((JavaValueIdentifier)identifier).getType()).append("\n");

            var values = trace.getValues(identifier);
            if (values.size() <= 10) {
                // Show all values for small histories
                for (Map.Entry<Integer, Object> entry : values.entrySet()) {
                    sb.append("//   Event ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                }
            } else {
                // Show first few, last few, and summary for large histories
                var entryList = new ArrayList<>(values.entrySet());
                for (int i = 0; i < 3; i++) {
                    var entry = entryList.get(i);
                    sb.append("//   Event ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                }
                sb.append("//   ... (").append(values.size() - 6).append(" more values) ...\n");
                for (int i = entryList.size() - 3; i < entryList.size(); i++) {
                    var entry = entryList.get(i);
                    sb.append("//   Event ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                }
            }
            sb.append("\n");
        }

        // Add execution scenarios based on state snapshots
        if (eventRange.isPresent()) {
            int[] range = eventRange.get();
            int sampleCount = Math.min(5, range[1] - range[0] + 1);
            sb.append("// Key execution scenarios:\n");

            for (int i = 0; i < sampleCount; i++) {
                int sampleEvent = range[0] + (i * (range[1] - range[0]) / Math.max(1, sampleCount - 1));
                Map<ExportableValue, Object> snapshot = trace.getStateSnapshotAt(sampleEvent);

                sb.append("//   Scenario at event ").append(sampleEvent).append(":\n");
                for (Map.Entry<ExportableValue, Object> entry : snapshot.entrySet()) {
                    String type = ((JavaValueIdentifier)entry.getKey()).getType();
                    sb.append("//     ").append(type).append(" = ").append(entry.getValue()).append("\n");
                }
                sb.append("\n");
            }
        }

        return sb.toString();
    }
    
    private String refineGeneratedCode(String initialCode, LLMPromptContext context) {
        log.debug("Refining generated code through iterative improvement");
        
        String currentCode = initialCode;
        
        for (int iteration = 0; iteration < llmConfig.getMaxRefinementIterations(); iteration++) {
            // Validate current code
            CodeValidationResult validation = codeValidator.validate(currentCode);
            
            if (validation.isValid()) {
                log.debug("Code validation passed after {} iterations", iteration);
                break;
            }
            
            // Build refinement prompt
            String refinementPrompt = promptBuilder.buildRefinementPrompt(currentCode, validation, context);
            
            try {
                String refinedCode = anthropicClient.generateCode(refinementPrompt);
                currentCode = refinedCode;
                log.debug("Completed refinement iteration {}", iteration + 1);
            } catch (Exception e) {
                log.warn("Refinement iteration {} failed: {}", iteration + 1, e.getMessage());
                break;
            }
        }
        
        return currentCode;
    }
    
    private String fixValidationErrors(String code, CodeValidationResult validation, LLMPromptContext context) {
        log.debug("Attempting to fix validation errors");
        
        String fixPrompt = promptBuilder.buildErrorFixPrompt(code, validation, context);
        
        try {
            return anthropicClient.generateCode(fixPrompt);
        } catch (Exception e) {
            log.warn("Failed to fix validation errors: {}", e.getMessage());
            return code; // Return original code if fixing fails
        }
    }
    
    private Path writeTestFile(String testCode, TestGenerationContext context) throws TestGenerationWorkflowException {
        try {
            // Extract class name from generated code
            String className = extractClassNameFromCode(testCode);
            if (className == null) {
                className = extractClassNameFromMethod(context.getTargetMethodSignature()) + "Test";
            }

            Path testFile = context.getOutputDirectory().resolve(className + ".java");
            Files.createDirectories(testFile.getParent());
            Files.write(testFile, testCode.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            return testFile;
        } catch (Exception e) {
            throw new TestGenerationWorkflowException(
                "Failed to write test file: " + e.getMessage(),
                "Test File Writing",
                null,
                context.getTargetMethodSignature(),
                e
            );
        }
    }
    
    private String extractClassNameFromCode(String code) {
        // Simple regex to extract class name from "public class ClassName"
        String[] lines = code.split("\n");
        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("public class ") && line.contains("{")) {
                String[] parts = line.split("\\s+");
                if (parts.length >= 3) {
                    String className = parts[2];
                    if (className.endsWith("{")) {
                        className = className.substring(0, className.length() - 1).trim();
                    }
                    return className;
                }
            }
        }
        return null;
    }
    
    private String extractClassNameFromMethod(String methodSignature) {
        if (methodSignature.contains(".")) {
            return methodSignature.substring(0, methodSignature.lastIndexOf('.'));
        }
        return "UnknownClass";
    }
    
    private TestGenerationContext createDefaultContext() {
        return TestGenerationContext.builder()
                .targetMethodSignature("unknownMethod")
                .targetClassName("UnknownClass")
                .packageName("")
                .outputDirectory(Path.of("generated-tests"))
                .testFramework("junit5")
                .maxTestCount(20)
                .generateEdgeCases(true)
                .generateNegativeTests(true)
                .namingStrategy(TestNamingStrategy.DESCRIPTIVE)
                .build();
    }
}
