package cz.cuni.mff.d3s.autodebugger.runner.factories;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.Instrumentor;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.DiSLInstrumentor;
import cz.cuni.mff.d3s.autodebugger.model.common.TargetLanguage;

public class InstrumentorFactory {
    public static Instrumentor getInstrumentor(TargetLanguage language) {
        if (language == TargetLanguage.JAVA) {
            return new DiSLInstrumentor();
        };

        throw new IllegalArgumentException("Unsupported language: " + language);
    }
}
