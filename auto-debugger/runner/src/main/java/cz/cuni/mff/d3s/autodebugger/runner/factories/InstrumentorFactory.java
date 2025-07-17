package cz.cuni.mff.d3s.autodebugger.runner.factories;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.Instrumentor;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.DiSLInstrumentor;
import cz.cuni.mff.d3s.autodebugger.model.common.RunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.common.TargetLanguage;
import cz.cuni.mff.d3s.autodebugger.model.java.JavaRunConfiguration;
import lombok.extern.slf4j.Slf4j;

/**
 * Factory for creating language-specific Instrumentor instances.
 * Uses factory pattern to abstract instrumentor creation based on
 * target language, configuring appropriate instrumentor implementations.
 */
@Slf4j
public class InstrumentorFactory {

    /**
     * Creates a language-specific Instrumentor from run configuration.
     * Dispatches to appropriate language-specific factory method based on target language.
     */
    public static Instrumentor createInstrumentor(RunConfiguration runConfiguration) {
        TargetLanguage language = runConfiguration.getLanguage();
        if (language == TargetLanguage.JAVA) {
            return createJavaInstrumentor(runConfiguration);
        }

        throw new IllegalArgumentException("Unsupported language: " + language);
    }

    private static DiSLInstrumentor createJavaInstrumentor(RunConfiguration runConfiguration) {
        log.info("Building DiSL instrumentor");

        if (runConfiguration instanceof JavaRunConfiguration javaRunConfiguration) {
            DiSLInstrumentor instrumentor = DiSLInstrumentor.builder()
                .runConfiguration(javaRunConfiguration)
                .build();
            log.info("Successfully built DiSL instrumentor");
            return instrumentor;
        }

        throw new IllegalArgumentException("Expected JavaRunConfiguration, got: " + runConfiguration.getClass().getSimpleName());
    }
}
