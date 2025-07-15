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
        
        // Role and task definition
        prompt.append("You are an expert Java developer who specializes in writing robust ")
              .append(context.getTestFramework()).append(" tests.\n\n");
        
        prompt.append("Generate a comprehensive test suite for the method `")
              .append(context.getTargetMethodSignature())
              .append("` in the following Java class. ");
        
        prompt.append("Use the provided runtime data to create assertions that verify the observed outcomes. ");
        
        if (context.isGenerateEdgeCases()) {
            prompt.append("Also generate tests for potential edge cases not present in the runtime data. ");
        }
        
        if (context.isGenerateNegativeTests()) {
            prompt.append("Include negative test cases that test error conditions and boundary violations. ");
        }
        
        prompt.append("Ensure test methods have descriptive names");
        if (context.getNamingStrategy() == TestNamingStrategy.BDD_STYLE) {
            prompt.append(" using given/when/then format");
        } else if (context.getNamingStrategy() == TestNamingStrategy.DESCRIPTIVE) {
            prompt.append(" that clearly describe what is being tested");
        }
        prompt.append(".\n\n");
        
        // Source code context
        prompt.append("## Source Code\n");
        prompt.append("```java\n");
        prompt.append(context.getSourceCode());
        prompt.append("\n```\n\n");
        
        // Runtime trace data
        if (context.getTraceData() != null && !context.getTraceData().isEmpty()) {
            prompt.append("## Runtime Trace Data\n");
            prompt.append("```\n");
            prompt.append(context.getTraceData());
            prompt.append("\n```\n\n");
        }
        
        // Test generation requirements
        prompt.append("## Test Generation Requirements\n");
        prompt.append("- Use ").append(context.getTestFramework()).append(" framework\n");
        prompt.append("- Generate up to ").append(context.getMaxTestCount()).append(" test methods\n");
        prompt.append("- Include appropriate setup and teardown methods if needed\n");
        prompt.append("- Use meaningful assertions that verify both expected outcomes and error conditions\n");
        prompt.append("- Follow Java naming conventions and best practices\n");
        
        if (context.getPackageName() != null && !context.getPackageName().isEmpty()) {
            prompt.append("- Use package: ").append(context.getPackageName()).append("\n");
        }
        
        if (context.isGenerateParameterizedTests()) {
            prompt.append("- Consider using parameterized tests for testing multiple input combinations\n");
        }
        
        prompt.append("\n");
        
        // Additional instructions
        if (context.getAdditionalInstructions() != null && !context.getAdditionalInstructions().isEmpty()) {
            prompt.append("## Additional Instructions\n");
            prompt.append(context.getAdditionalInstructions());
            prompt.append("\n\n");
        }
        
        // Output format
        prompt.append("## Output Format\n");
        prompt.append("Provide only the complete Java test class code, including:\n");
        prompt.append("- Package declaration (if applicable)\n");
        prompt.append("- All necessary imports\n");
        prompt.append("- Class declaration with appropriate name\n");
        prompt.append("- Setup/teardown methods if needed\n");
        prompt.append("- All test methods with proper annotations\n");
        prompt.append("- Meaningful assertions and error handling\n\n");
        
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
