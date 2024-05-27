package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiSLScopeTests {

    @Test
    public void givenValidDiSLScope_whenGeneratingCode_thenCodeIsGenerated() {
        // given
        DiSLScope dislScope = Constants.dislScope;

        // when
        String code = dislScope.emitCode();

        // then
        assertEquals(String.format("scope = \"%s\"", dislScope.getMethodIdentifier().getName()), code);
    }
}
