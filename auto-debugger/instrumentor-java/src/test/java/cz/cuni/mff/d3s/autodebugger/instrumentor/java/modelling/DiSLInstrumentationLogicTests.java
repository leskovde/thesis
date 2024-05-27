package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiSLInstrumentationLogicTests {

    @Test
    public void givenValidInstrumentationLogic_whenGeneratingCode_thenCodeIsGenerated() {
        // given
        DiSLInstrumentationLogic logic = new DiSLInstrumentationLogic(Constants.instrumentationLogicMethodIdentifier, Constants.dislAnnotation, Constants.instrumentationLogicExports);

        // when
        String code = logic.emitCode();

        // then
        assertEquals("", code);
    }
}
