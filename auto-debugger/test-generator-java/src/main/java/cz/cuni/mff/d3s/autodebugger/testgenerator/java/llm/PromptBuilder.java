package cz.cuni.mff.d3s.autodebugger.testgenerator.java.llm;

import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TestNamingStrategy;
import lombok.extern.slf4j.Slf4j;

/**
 * Builds prompts for LLM-based test generation.
 * Constructs context-aware prompts that guide the LLM to generate high-quality test code.
 */
@Slf4j
public class PromptBuilder {
    
    /**
     * Builds the main test generation prompt.
     */
    public String buildTestGenerationPrompt(LLMPromptContext context) {
        StringBuilder prompt = new StringBuilder();

        // Role-playing instruction (as specified in solution.tex)
        prompt.append("You are an expert Java developer who specializes in writing robust ")
              .append(context.getTestFramework()).append(" tests.\n\n");

        // Task definition (as specified in solution.tex)
        prompt.append("Generate a suite of ").append(context.getTestFramework())
              .append(" tests for the ").append(context.getTargetMethodSignature())
              .append(" method. Use the provided runtime data to create assertions that verify the observed outcomes. ");

        if (context.isGenerateEdgeCases()) {
            prompt.append("Also generate tests for potential edge cases not present in the runtime data. ");
        }

        prompt.append("Ensure test methods have descriptive names.\n\n");

        // Static context: Source code (as specified in solution.tex)
        prompt.append("// --- Static Context: Source Code ---\n");
        prompt.append(context.getSourceCode());
        prompt.append("\n");

        // Dynamic context: Runtime trace data (as specified in solution.tex)
        if (context.getTraceData() != null && !context.getTraceData().isEmpty()) {
            prompt.append("// --- Dynamic Context: Observed Runtime Traces ---\n");
            prompt.append(context.getTraceData());
            prompt.append("\n");
        }

        
        prompt.append("Do not include explanations or markdown formatting - just the Java code.\n");
        
        return prompt.toString();
    }
    
    /**
     * Builds a refinement prompt for improving generated code.
     */
    public String buildRefinementPrompt(String currentCode, CodeValidationResult validation, LLMPromptContext context) {
        StringBuilder prompt = new StringBuilder();
        
        prompt.append("The following Java test code has some issues that need to be fixed. ");
        prompt.append("Please improve the code to address the validation errors and make it more robust.\n\n");
        
        prompt.append("## Current Code\n");
        prompt.append("```java\n");
        prompt.append(currentCode);
        prompt.append("\n```\n\n");
        
        prompt.append("## Validation Issues\n");
        for (String error : validation.getErrors()) {
            prompt.append("- ").append(error).append("\n");
        }
        
        if (!validation.getWarnings().isEmpty()) {
            prompt.append("\n## Warnings\n");
            for (String warning : validation.getWarnings()) {
                prompt.append("- ").append(warning).append("\n");
            }
        }
        
        prompt.append("\n## Requirements\n");
        prompt.append("- Fix all compilation errors\n");
        prompt.append("- Ensure all imports are correct\n");
        prompt.append("- Use proper ").append(context.getTestFramework()).append(" annotations\n");
        prompt.append("- Maintain the original test logic and assertions\n");
        prompt.append("- Follow Java coding best practices\n\n");
        
        prompt.append("Provide the corrected Java code without explanations:\n");
        
        return prompt.toString();
    }
    
    /**
     * Builds a prompt for fixing specific validation errors.
     */
    public String buildErrorFixPrompt(String code, CodeValidationResult validation, LLMPromptContext context) {
        StringBuilder prompt = new StringBuilder();
        
        prompt.append("Fix the following compilation errors in this Java test code:\n\n");
        
        prompt.append("## Code with Errors\n");
        prompt.append("```java\n");
        prompt.append(code);
        prompt.append("\n```\n\n");
        
        prompt.append("## Compilation Errors\n");
        for (String error : validation.getErrors()) {
            prompt.append("- ").append(error).append("\n");
        }
        
        prompt.append("\n## Instructions\n");
        prompt.append("- Fix only the compilation errors\n");
        prompt.append("- Do not change the test logic unless necessary\n");
        prompt.append("- Ensure all required imports are included\n");
        prompt.append("- Use ").append(context.getTestFramework()).append(" framework correctly\n\n");
        
        prompt.append("Return only the corrected Java code:\n");
        
        return prompt.toString();
    }
    
    /**
     * Builds a prompt for generating additional edge case tests.
     */
    public String buildEdgeCasePrompt(LLMPromptContext context) {
        StringBuilder prompt = new StringBuilder();
        
        prompt.append("Generate additional edge case tests for the method `")
              .append(context.getTargetMethodSignature())
              .append("`. Focus on boundary conditions, null values, empty collections, ");
        prompt.append("extreme values, and other edge cases that might cause unexpected behavior.\n\n");
        
        prompt.append("## Source Code\n");
        prompt.append("```java\n");
        prompt.append(context.getSourceCode());
        prompt.append("\n```\n\n");
        
        prompt.append("Generate 5-10 additional test methods that cover edge cases not typically ");
        prompt.append("encountered during normal execution. Use ").append(context.getTestFramework());
        prompt.append(" framework and follow the same naming conventions.\n\n");
        
        prompt.append("Return only the test methods (not the full class):\n");
        
        return prompt.toString();
    }
}
