package cz.cuni.mff.d3s.autodebugger.runner.parsing;

import cz.cuni.mff.d3s.autodebugger.model.common.TargetLanguage;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory for creating language-specific method signature parsing strategies.
 * This allows the runner to support multiple programming languages.
 */
@Slf4j
public class MethodSignatureParsingStrategyFactory {
    
    private static final Map<TargetLanguage, MethodSignatureParsingStrategy> strategies = new HashMap<>();
    
    static {
        // Register available strategies
        registerStrategy(TargetLanguage.JAVA, new JavaMethodSignatureParsingStrategy());
    }
    
    /**
     * Registers a parsing strategy for a specific language.
     * 
     * @param strategy The strategy to register
     */
    public static void registerStrategy(TargetLanguage language, MethodSignatureParsingStrategy strategy) {
        strategies.put(language, strategy);
        log.debug("Registered parsing strategy for language: {}", language);
    }
    
    /**
     * Gets a parsing strategy for the specified language.
     * 
     * @param language The programming language name
     * @return The parsing strategy for the language
     * @throws IllegalArgumentException if the language is not supported
     */
    public static MethodSignatureParsingStrategy getStrategy(TargetLanguage language) {
        MethodSignatureParsingStrategy strategy = strategies.get(language);
        
        if (strategy == null) {
            throw new IllegalArgumentException(
                "Unsupported language: " + language + ". Supported languages: " + getSupportedLanguages());
        }
        
        return strategy;
    }

    /**
     * Gets a list of all supported languages.
     * 
     * @return Comma-separated list of supported languages
     */
    public static String getSupportedLanguages() {
        return String.join(", ", strategies.keySet()
                .stream()
                .map(Enum::name)
                .toArray(String[]::new));
    }
}
