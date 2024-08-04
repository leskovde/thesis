package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DiSLInstrumentationLogicTests {

  @Test
  public void givenValidSerializationInstrumentationLogic_whenGeneratingCode_thenCodeIsGenerated() {
    // given
    DiSLInstrumentationLogic logic =
        new SerializationDiSLInstrumentationLogic(
            Constants.instrumentationLogicMethodIdentifier,
            Constants.dislAnnotation,
            Constants.instrumentationLogicExports);

    // when
    String code = logic.emitCode();

    // then
    assertEquals("", code);
  }

  @Test
  public void givenValidShadowInstrumentationLogic_whenGeneratingCode_thenCodeIsGenerated() {
    // given
    DiSLInstrumentationLogic logic =
        new ShadowDiSLInstrumentationLogic(
            Constants.instrumentationLogicMethodIdentifier,
            Constants.dislAnnotation,
            Constants.instrumentationLogicExports);

    // when
    String code = logic.emitCode();

    // then
    assertEquals("", code);
  }
}
