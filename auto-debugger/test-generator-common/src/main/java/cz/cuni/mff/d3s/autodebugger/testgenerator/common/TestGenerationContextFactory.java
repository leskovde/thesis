package cz.cuni.mff.d3s.autodebugger.testgenerator.common;

import cz.cuni.mff.d3s.autodebugger.model.common.RunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.MethodIdentifier;

/**
 * Factory class for creating TestGenerationContext instances from RunConfiguration.
 * This factory eliminates the need for manual string-based construction by leveraging
 * the structured identifiers already present in RunConfiguration.
 */
public class TestGenerationContextFactory {

    protected TestGenerationContextFactory() { }

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

        // Use structured identifiers only; do not set deprecated string fields
        if (targetMethod != null) {
            builder.targetMethod(targetMethod);
        }
        return builder.build();
    }



    /**
     * Creates a TestGenerationContext with minimal information for testing purposes.
     * 
     * @param targetMethod The target method signature
     * @param outputDirectory The output directory for generated tests
     * @return A TestGenerationContext with minimal configuration
     */
    public static TestGenerationContext createMinimal(MethodIdentifier targetMethod,
                                                     java.nio.file.Path outputDirectory) {
        return TestGenerationContext.builder()
                .targetMethod(targetMethod)
                .outputDirectory(outputDirectory)
                .build();
    }
}
