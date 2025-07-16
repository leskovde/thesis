package cz.cuni.mff.d3s.autodebugger.runner.orchestrator;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory for creating language-specific orchestrator instances.
 * This factory maintains a registry of available orchestrators and provides
 * them based on the target programming language.
 */
@Slf4j
public class OrchestratorFactory {
    
    private static final Map<String, Orchestrator> orchestrators = new HashMap<>();
    
    static {
        // Register available orchestrators
        registerOrchestrator(new JavaOrchestrator());
        // Future: registerOrchestrator(new PythonOrchestrator());
        // Future: registerOrchestrator(new JavaScriptOrchestrator());
    }
    
    /**
     * Registers an orchestrator for a specific language.
     * 
     * @param orchestrator The orchestrator to register
     */
    public static void registerOrchestrator(Orchestrator orchestrator) {
        String language = orchestrator.getSupportedLanguage().toLowerCase();
        orchestrators.put(language, orchestrator);
        log.debug("Registered orchestrator for language: {}", language);
    }
    
    /**
     * Creates an orchestrator for the specified language.
     * 
     * @param language The programming language name
     * @return The orchestrator for the language
     * @throws IllegalArgumentException if the language is not supported
     */
    public static Orchestrator create(String language) {
        if (language == null || language.trim().isEmpty()) {
            throw new IllegalArgumentException("Language cannot be null or empty");
        }
        
        String normalizedLanguage = language.toLowerCase().trim();
        Orchestrator orchestrator = orchestrators.get(normalizedLanguage);
        
        if (orchestrator == null) {
            throw new IllegalArgumentException(
                "Unsupported language: " + language + ". Supported languages: " + getSupportedLanguages());
        }
        
        log.info("Created orchestrator for language: {}", language);
        return orchestrator;
    }
    
    /**
     * Gets a list of all supported languages.
     * 
     * @return Comma-separated list of supported languages
     */
    public static String getSupportedLanguages() {
        return String.join(", ", orchestrators.keySet());
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
        return orchestrators.containsKey(language.toLowerCase().trim());
    }
    
    /**
     * Gets all registered orchestrators.
     * 
     * @return Map of language -> orchestrator
     */
    public static Map<String, Orchestrator> getAllOrchestrators() {
        return new HashMap<>(orchestrators);
    }
}
