package cz.cuni.mff.d3s.autodebugger.testgenerator.java.llm;

import cz.cuni.mff.d3s.autodebugger.model.java.Trace;
import cz.cuni.mff.d3s.autodebugger.model.java.EnhancedTrace;
import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.ExportableValue;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.*;
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
public class LLMBasedTestGenerator implements LLMBasedGenerator {
    
    private LLMConfiguration llmConfig;
    private final LLMClient llmClient;
    private final PromptBuilder promptBuilder;
    private final CodeValidator codeValidator;
    
    public LLMBasedTestGenerator() {
        this.llmClient = new LLMClient();
        this.promptBuilder = new PromptBuilder();
        this.codeValidator = new CodeValidator();
    }
    
    @Override
    public void configure(LLMConfiguration config) {
        this.llmConfig = config;
        this.llmClient.configure(config);
        log.info("Configured LLM-based test generator with provider: {}, model: {}", 
                config.getProvider(), config.getModelName());
    }
    
    @Override
    public List<Path> generateTests(Trace trace, Path sourceCodePath) {
        return generateTests(trace, sourceCodePath, createDefaultContext());
    }
    
    @Override
    public List<Path> generateTests(Trace trace, Path sourceCodePath, TestGenerationContext context) {
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
            String generatedCode = llmClient.generateCode(prompt);

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

        } catch (Exception e) {
            log.error("Failed to generate LLM-based tests", e);
            return List.of();
        }
    }

    /**
     * Enhanced method that generates tests from an EnhancedTrace with temporal information.
     * This provides richer context to the LLM for generating more sophisticated tests.
     */
    public List<Path> generateTests(EnhancedTrace enhancedTrace, Path sourceCodePath, TestGenerationContext context) {
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
            String generatedCode = llmClient.generateCode(prompt);

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
     * Builds enhanced prompt context from an EnhancedTrace with temporal information.
     */
    private LLMPromptContext buildEnhancedPromptContext(EnhancedTrace enhancedTrace, String sourceCode, TestGenerationContext context) {
        return LLMPromptContext.builder()
                .sourceCode(sourceCode)
                .targetMethodSignature(context.getTargetMethodSignature())
                .targetClassName(context.getTargetClassName())
                .packageName(context.getPackageName())
                .testFramework(context.getTestFramework())
                .traceData(formatEnhancedTraceData(enhancedTrace))
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
     * Formats EnhancedTrace data for LLM consumption with temporal context.
     */
    private String formatEnhancedTraceData(EnhancedTrace trace) {
        StringBuilder sb = new StringBuilder();
        sb.append("Enhanced Runtime Trace Data with Temporal Information:\n");
        sb.append("// ").append(trace.getSummary().replace("\n", "\n// ")).append("\n\n");

        // Get event range for context
        Optional<int[]> eventRange = trace.getEventIndexRange();
        if (eventRange.isPresent()) {
            int[] range = eventRange.get();
            sb.append("// Execution timeline: events ").append(range[0]).append(" to ").append(range[1]).append("\n\n");
        }

        // Format variable histories
        for (ExportableValue identifier : trace.getTrackedIdentifiers()) {
            sb.append("// Variable: ").append(identifier.getType()).append("\n");

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
                    sb.append("//     ").append(entry.getKey().getType()).append(" = ").append(entry.getValue()).append("\n");
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
                String refinedCode = llmClient.generateCode(refinementPrompt);
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
            return llmClient.generateCode(fixPrompt);
        } catch (Exception e) {
            log.warn("Failed to fix validation errors: {}", e.getMessage());
            return code; // Return original code if fixing fails
        }
    }
    
    private Path writeTestFile(String testCode, TestGenerationContext context) throws IOException {
        // Extract class name from generated code
        String className = extractClassNameFromCode(testCode);
        if (className == null) {
            className = extractClassNameFromMethod(context.getTargetMethodSignature()) + "Test";
        }
        
        Path testFile = context.getOutputDirectory().resolve(className + ".java");
        Files.createDirectories(testFile.getParent());
        Files.write(testFile, testCode.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        
        return testFile;
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
