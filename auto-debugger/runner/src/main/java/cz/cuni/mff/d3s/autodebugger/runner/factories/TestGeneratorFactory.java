package cz.cuni.mff.d3s.autodebugger.runner.factories;

import cz.cuni.mff.d3s.autodebugger.model.common.RunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.common.TargetLanguage;
import cz.cuni.mff.d3s.autodebugger.model.java.JavaRunConfiguration;
import cz.cuni.mff.d3s.autodebugger.runner.orchestrator.LLMTestGeneratorAdapter;
import cz.cuni.mff.d3s.autodebugger.runner.orchestrator.TestGeneratorAdapter;
import cz.cuni.mff.d3s.autodebugger.runner.strategies.TestGenerationStrategyProvider;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.LLMConfiguration;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TestGenerator;
import cz.cuni.mff.d3s.autodebugger.testgenerator.java.llm.LLMBasedTestGenerator;
import cz.cuni.mff.d3s.autodebugger.testgenerator.java.trace.TraceBasedUnitTestGenerator;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;

@Slf4j
public class TestGeneratorFactory {

    public static TestGenerator createTestGenerator(RunConfiguration runConfiguration, String strategyId) {
        return createTestGenerator(runConfiguration, strategyId, null);
    }

    public static TestGenerator createTestGenerator(RunConfiguration runConfiguration, String strategyId, String apiKey) {
        TargetLanguage language = runConfiguration.getLanguage();
        if (language == TargetLanguage.JAVA) {
            return createJavaTestGenerator(runConfiguration, strategyId, apiKey);
        }

        throw new IllegalArgumentException("Unsupported language: " + language);
    }

    private static TestGenerator createJavaTestGenerator(RunConfiguration runConfiguration, String strategyId, String apiKey) {
        log.info("Creating Java test generator with strategy: {}", strategyId);

        // Validate that the strategy exists
        if (!TestGenerationStrategyProvider.hasStrategy(strategyId)) {
            throw new IllegalArgumentException("Unknown test generation strategy: " + strategyId);
        }

        if (runConfiguration instanceof JavaRunConfiguration javaRunConfiguration) {
            try {
                if ("ai-assisted".equals(strategyId)) {
                    // Create LLM-based test generator
                    LLMBasedTestGenerator llmGenerator = new LLMBasedTestGenerator();

                    // Configure with default settings for Claude 4
                    String resolvedApiKey = getApiKeyFromEnvironmentOrConfig(apiKey);
                    LLMConfiguration llmConfig = LLMConfiguration.builder()
                            .provider("anthropic")
                            .modelName("claude-sonnet-4-20250514")
                            .apiKey(resolvedApiKey)
                            .maxTokens(4000)
                            .temperature(0.3)
                            .enableIterativeRefinement(true)
                            .validateGeneratedCode(true)
                            .build();

                    llmGenerator.configure(llmConfig);

                    log.info("Successfully created LLM-based Java test generator with Claude");
                    return new LLMTestGeneratorAdapter(llmGenerator, runConfiguration, strategyId);

                } else if (strategyId.startsWith("trace-based")) {
                    // Create the trace-based test generator
                    Path identifiersPath = javaRunConfiguration.getSourceCodePath().resolve("identifiers");
                    TraceBasedUnitTestGenerator generator = new TraceBasedUnitTestGenerator(identifiersPath);

                    log.info("Successfully created Java test generator with strategy: {}", strategyId);
                    return new TestGeneratorAdapter(generator, runConfiguration, strategyId);
                } else {
                    throw new UnsupportedOperationException("Test generation technique not yet supported: " + strategyId);
                }
            } catch (Exception e) {
                log.error("Failed to create Java test generator", e);
                throw new RuntimeException("Failed to create test generator", e);
            }
        }

        throw new IllegalArgumentException("Expected JavaRunConfiguration, got: " + runConfiguration.getClass().getSimpleName());
    }

    private static String getApiKeyFromEnvironmentOrConfig(String providedApiKey) {
        // First try provided API key from command line
        if (providedApiKey != null && !providedApiKey.trim().isEmpty()) {
            return providedApiKey;
        }

        // Then try environment variable
        String envApiKey = System.getenv("ANTHROPIC_API_KEY");
        if (envApiKey != null && !envApiKey.trim().isEmpty()) {
            return envApiKey;
        }

        // TODO: Add support for reading from configuration file/settings
        // For now, throw an exception if no API key is found
        throw new IllegalStateException("Anthropic API key not found. Please set ANTHROPIC_API_KEY environment variable or provide via --api-key argument");
    }
}
