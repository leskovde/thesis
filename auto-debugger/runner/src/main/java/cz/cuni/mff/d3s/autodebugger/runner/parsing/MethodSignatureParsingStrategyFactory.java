package cz.cuni.mff.d3s.autodebugger.runner.parsing;

import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory for creating language-specific method signature parsing strategies.
 * This allows the runner to support multiple programming languages.
 */
@Slf4j
public class MethodSignatureParsingStrategyFactory {
    
    private static final Map<String, MethodSignatureParsingStrategy> strategies = new HashMap<>();
    
    static {
        // Register available strategies
        registerStrategy(new JavaMethodSignatureParsingStrategy());
        // Future: registerStrategy(new PythonMethodSignatureParsingStrategy());
        // Future: registerStrategy(new JavaScriptMethodSignatureParsingStrategy());
    }
    
    /**
     * Registers a parsing strategy for a specific language.
     * 
     * @param strategy The strategy to register
     */
    public static void registerStrategy(MethodSignatureParsingStrategy strategy) {
        strategies.put(strategy.getLanguage().toLowerCase(), strategy);
        log.debug("Registered parsing strategy for language: {}", strategy.getLanguage());
    }
    
    /**
     * Gets a parsing strategy for the specified language.
     * 
     * @param language The programming language name
     * @return The parsing strategy for the language
     * @throws IllegalArgumentException if the language is not supported
     */
    public static MethodSignatureParsingStrategy getStrategy(String language) {
        if (language == null || language.trim().isEmpty()) {
            throw new IllegalArgumentException("Language cannot be null or empty");
        }
        
        String normalizedLanguage = language.toLowerCase().trim();
        MethodSignatureParsingStrategy strategy = strategies.get(normalizedLanguage);
        
        if (strategy == null) {
            throw new IllegalArgumentException(
                "Unsupported language: " + language + ". Supported languages: " + getSupportedLanguages());
        }
        
        return strategy;
    }
    
    /**
     * Automatically detects the language based on the application file extension.
     * 
     * @param applicationPath Path to the application file
     * @return The detected language
     * @throws IllegalArgumentException if the language cannot be detected
     */
    public static String detectLanguage(Path applicationPath) {
        if (applicationPath == null) {
            throw new IllegalArgumentException("Application path cannot be null");
        }
        
        String fileName = applicationPath.getFileName().toString().toLowerCase();
        
        if (fileName.endsWith(".jar")) {
            return "java";
        } else if (fileName.endsWith(".py") || fileName.endsWith(".pyc")) {
            return "python";
        } else if (fileName.endsWith(".js") || fileName.endsWith(".mjs")) {
            return "javascript";
        } else if (fileName.endsWith(".exe") || fileName.endsWith(".dll")) {
            return "csharp"; // or "cpp" - would need more sophisticated detection
        }
        
        // Default to Java for now
        log.warn("Could not detect language from file extension: {}. Defaulting to Java.", fileName);
        return "java";
    }
    
    /**
     * Gets a parsing strategy by automatically detecting the language from the application path.
     * 
     * @param applicationPath Path to the application file
     * @return The appropriate parsing strategy
     * @throws IllegalArgumentException if the language cannot be detected or is not supported
     */
    public static MethodSignatureParsingStrategy getStrategyForApplication(Path applicationPath) {
        String language = detectLanguage(applicationPath);
        return getStrategy(language);
    }
    
    /**
     * Gets a list of all supported languages.
     * 
     * @return Comma-separated list of supported languages
     */
    public static String getSupportedLanguages() {
        return String.join(", ", strategies.keySet());
    }
    
    /**
     * Checks if a language is supported.
     * 
     * @param language The language to check
     * @return true if the language is supported, false otherwise
     */
    public static boolean isLanguageSupported(String language) {
        if (language == null || language.trim().isEmpty()) {
            return false;
        }
        return strategies.containsKey(language.toLowerCase().trim());
    }
}
