package cz.cuni.mff.d3s.autodebugger.testgenerator.common;

import cz.cuni.mff.d3s.autodebugger.model.common.RunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.common.TargetLanguage;
import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.MethodIdentifier;

/**
 * Factory class for creating TestGenerationContext instances from RunConfiguration.
 * This factory eliminates the need for manual string-based construction by leveraging
 * the structured identifiers already present in RunConfiguration.
 */
public class TestGenerationContextFactory {

    /**
     * Creates a TestGenerationContext from a RunConfiguration.
     * Uses the structured identifiers in the configuration to avoid manual string construction.
     *
     * @param runConfiguration The run configuration containing structured identifiers
     * @return A TestGenerationContext with information extracted from the configuration
     * @throws IllegalArgumentException if the configuration is not supported
     */
    public static TestGenerationContext createFromRunConfiguration(RunConfiguration runConfiguration) {
        return createFromRunConfiguration(runConfiguration, TestGenerationSettings.defaultSettings());
    }

    /**
     * Creates a TestGenerationContext from a RunConfiguration with custom settings.
     *
     * @param runConfiguration The run configuration containing structured identifiers
     * @param settings Custom test generation settings
     * @return A TestGenerationContext with the specified settings
     * @throws IllegalArgumentException if the configuration is not supported
     */
    public static TestGenerationContext createFromRunConfiguration(
            RunConfiguration runConfiguration,
            TestGenerationSettings settings) {

        if (runConfiguration == null) {
            throw new IllegalArgumentException("RunConfiguration cannot be null");
        }

        if (settings == null) {
            settings = TestGenerationSettings.defaultSettings();
        }

        MethodIdentifier targetMethod = runConfiguration.getTargetMethod();

        TestGenerationContext.TestGenerationContextBuilder builder = TestGenerationContext.builder()
                .outputDirectory(runConfiguration.getOutputDirectory())
                .testFramework(settings.getTestFramework())
                .maxTestCount(settings.getMaxTestCount())
                .generateEdgeCases(settings.isGenerateEdgeCases())
                .generateNegativeTests(settings.isGenerateNegativeTests())
                .namingStrategy(settings.getNamingStrategy())
                .includePerformanceAssertions(settings.isIncludePerformanceAssertions())
                .testTimeoutMs(settings.getTestTimeoutMs())
                .objectCreationStrategy(settings.getObjectCreationStrategy())
                .generateParameterizedTests(settings.isGenerateParameterizedTests());

        // Use structured identifiers when available, with string fallbacks for compatibility
        if (targetMethod != null) {
            builder.targetMethod(targetMethod);

            // Also set deprecated string fields for backward compatibility
            String methodSignature = targetMethod.toString();
            builder.targetMethodSignature(methodSignature)
                   .targetClassName("UnknownClass") // Will be overridden by language-specific factories
                   .packageName(""); // Will be overridden by language-specific factories
        } else {
            // Fallback values if method identifier is not available
            builder.targetMethodSignature("unknownMethod")
                   .targetClassName("UnknownClass")
                   .packageName("");
        }

        return builder.build();
    }



    /**
     * Creates a TestGenerationContext with minimal information for testing purposes.
     * 
     * @param targetMethodSignature The target method signature
     * @param outputDirectory The output directory for generated tests
     * @return A TestGenerationContext with minimal configuration
     */
    public static TestGenerationContext createMinimal(String targetMethodSignature, 
                                                     java.nio.file.Path outputDirectory) {
        return TestGenerationContext.builder()
                .targetMethodSignature(targetMethodSignature)
                .targetClassName("TestClass")
                .packageName("com.example.test")
                .outputDirectory(outputDirectory)
                .build();
    }
}
