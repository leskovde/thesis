package cz.cuni.mff.d3s.autodebugger.testgenerator.common;

import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
import java.util.Map;

/**
 * Configuration for LLM-based test generation, including API settings,
 * model parameters, and generation preferences.
 */
@Builder
@Getter
public class LLMConfiguration {
    
    /**
     * LLM provider (e.g., "openai", "anthropic", "local").
     */
    private final String provider;
    
    /**
     * Model name to use (e.g., "gpt-4", "claude-3-sonnet").
     */
    private final String modelName;
    
    /**
     * API key for the LLM service.
     */
    private final String apiKey;
    
    /**
     * API endpoint URL (for custom or local deployments).
     */
    private final String apiEndpoint;
    
    /**
     * Maximum number of tokens in the response.
     */
    @Builder.Default
    private final int maxTokens = 4000;
    
    /**
     * Temperature for response generation (0.0 to 1.0).
     * Lower values make output more deterministic.
     */
    @Builder.Default
    private final double temperature = 0.3;
    
    /**
     * Top-p sampling parameter (0.0 to 1.0).
     */
    @Builder.Default
    private final double topP = 0.9;
    
    /**
     * Timeout for API requests.
     */
    @Builder.Default
    private final Duration requestTimeout = Duration.ofMinutes(2);
    
    /**
     * Maximum number of retry attempts for failed requests.
     */
    @Builder.Default
    private final int maxRetries = 3;
    
    /**
     * Whether to enable iterative refinement of generated tests.
     */
    @Builder.Default
    private final boolean enableIterativeRefinement = true;
    
    /**
     * Maximum number of refinement iterations.
     */
    @Builder.Default
    private final int maxRefinementIterations = 3;
    
    /**
     * Whether to validate generated code before returning.
     */
    @Builder.Default
    private final boolean validateGeneratedCode = true;
    
    /**
     * Custom prompt templates for different test generation scenarios.
     */
    private final Map<String, String> promptTemplates;
    
    /**
     * Additional model-specific parameters.
     */
    private final Map<String, Object> additionalParameters;
    
    /**
     * Whether to include source code context in prompts.
     */
    @Builder.Default
    private final boolean includeSourceContext = true;
    
    /**
     * Whether to include runtime trace data in prompts.
     */
    @Builder.Default
    private final boolean includeTraceContext = true;
    
    /**
     * Maximum size of source code context to include (in characters).
     */
    @Builder.Default
    private final int maxSourceContextSize = 10000;
    
    /**
     * Whether to use few-shot examples in prompts.
     */
    @Builder.Default
    private final boolean useFewShotExamples = true;
}
