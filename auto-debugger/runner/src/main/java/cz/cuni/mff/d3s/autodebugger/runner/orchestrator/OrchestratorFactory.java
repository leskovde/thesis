package cz.cuni.mff.d3s.autodebugger.runner.orchestrator;

import cz.cuni.mff.d3s.autodebugger.runner.args.TargetLanguage;
import lombok.extern.slf4j.Slf4j;

import java.util.EnumMap;
import java.util.Map;

/**
 * Factory for creating language-specific orchestrator instances.
 * This factory maintains a registry of available orchestrators and provides
 * them based on the target programming language.
 */
@Slf4j
public class OrchestratorFactory {

    private static final Map<TargetLanguage, Orchestrator> orchestrators = new EnumMap<>(TargetLanguage.class);

    static {
        // Register available orchestrators
        registerOrchestrator(TargetLanguage.JAVA, new JavaOrchestrator());
    }
    
    /**
     * Registers an orchestrator for a specific language.
     *
     * @param language The target language
     * @param orchestrator The orchestrator to register
     */
    public static void registerOrchestrator(TargetLanguage language, Orchestrator orchestrator) {
        orchestrators.put(language, orchestrator);
        log.debug("Registered orchestrator for language: {}", language.getDisplayName());
    }
    
    /**
     * Creates an orchestrator for the specified language.
     *
     * @param language The target language enum
     * @return The orchestrator for the language
     * @throws IllegalArgumentException if the language is not supported
     */
    public static Orchestrator create(TargetLanguage language) {
        if (language == null) {
            throw new IllegalArgumentException("Language cannot be null");
        }

        Orchestrator orchestrator = orchestrators.get(language);

        if (orchestrator == null) {
            throw new IllegalArgumentException(
                "No orchestrator registered for language: " + language.getDisplayName() +
                ". Supported languages: " + getSupportedLanguages());
        }

        log.info("Created orchestrator for language: {}", language.getDisplayName());
        return orchestrator;
    }

    /**
     * Creates an orchestrator for the specified language string.
     * This is a convenience method that parses the string into a TargetLanguage enum.
     *
     * @param languageString The programming language name as string
     * @return The orchestrator for the language
     * @throws IllegalArgumentException if the language is not supported
     */
    public static Orchestrator create(String languageString) {
        TargetLanguage language = TargetLanguage.fromIdentifier(languageString);
        return create(language);
    }
    
    /**
     * Gets a list of all supported languages.
     *
     * @return Comma-separated list of supported languages
     */
    public static String getSupportedLanguages() {
        return TargetLanguage.getSupportedLanguagesString();
    }

    /**
     * Checks if a language is supported.
     *
     * @param language The target language enum
     * @return true if the language is supported, false otherwise
     */
    public static boolean isLanguageSupported(TargetLanguage language) {
        return language != null && orchestrators.containsKey(language);
    }

    /**
     * Checks if a language string is supported.
     *
     * @param languageString The language string to check
     * @return true if the language is supported, false otherwise
     */
    public static boolean isLanguageSupported(String languageString) {
        try {
            TargetLanguage language = TargetLanguage.fromIdentifier(languageString);
            return isLanguageSupported(language);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Gets all registered orchestrators.
     *
     * @return Map of language -> orchestrator
     */
    public static Map<TargetLanguage, Orchestrator> getAllOrchestrators() {
        return new EnumMap<>(orchestrators);
    }
}
