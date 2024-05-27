package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class JavaFieldTests {

  @Test
  public void givenValidJavaField_whenGeneratingCode_thenCodeIsGenerated() {
    // given
    JavaField javaField = Constants.javaField;

    // when
    String code = javaField.emitCode();

    // then
    assertEquals(
        "String generatedVariable1 = di.getInstanceFieldValue(di.getThis(), Test.class, \"testField\", String.class);",
        code);
  }
}
