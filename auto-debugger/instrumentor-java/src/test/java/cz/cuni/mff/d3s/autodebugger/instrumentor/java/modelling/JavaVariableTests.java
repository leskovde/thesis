package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class JavaVariableTests {

    @Test
    @Disabled
    public void givenValidJavaVariable_whenGeneratingCode_thenCodeIsGenerated() {
        // given
        JavaVariable javaVariable = Constants.javaVariable;

        // when
        String code = javaVariable.emitCode();

        // then
        assertEquals("String generatedVariable2 = di.getLocalVariableValue(0, String.class);", code);
    }
}
