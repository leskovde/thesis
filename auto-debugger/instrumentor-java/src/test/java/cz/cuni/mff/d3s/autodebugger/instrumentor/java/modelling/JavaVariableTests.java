package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaVariableTests {

    @Test
    public void givenValidJavaVariable_whenGeneratingCode_thenCodeIsGenerated() {
        // given
        JavaVariable javaVariable = Constants.javaVariable;

        // when
        String code = javaVariable.emitCode();

        // then
        assertEquals("String generatedVariable2 = di.getLocalVariableValue(0, String.class);", code);
    }
}
