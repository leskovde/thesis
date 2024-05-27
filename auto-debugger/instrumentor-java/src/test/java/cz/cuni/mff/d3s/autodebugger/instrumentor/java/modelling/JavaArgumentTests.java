package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaArgumentTests {

    @Test
    public void givenValidJavaArgument_whenGeneratingCode_thenCodeIsGenerated() {
        // given
        JavaArgument javaArgument = Constants.javaArgument;

        // when
        String code = javaArgument.emitCode();

        // then
        assertEquals("String generatedVariable0 = di.getMethodArgumentValue(0, String.class);", code);
    }
}
