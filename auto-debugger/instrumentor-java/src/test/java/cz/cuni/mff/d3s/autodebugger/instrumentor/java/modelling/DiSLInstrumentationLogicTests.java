package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import static cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling.Constants.normalizeVariableNames;
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
    String expectedCode = """
      @Before(marker = BodyMarker.class, scope = "Test.test")
      public static void generatedMethod1(DynamicContext di) {
      FileOutputStream fileOut;ObjectOutputStream out;
      try {
      } catch (IOException e) {
      e.printStackTrace();
      }
      }
      """;
    assertEquals(normalizeVariableNames(expectedCode), normalizeVariableNames(code));
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
    String expectedCode = """
      @Before(marker = BodyMarker.class, scope = "Test.test")
      public static void generatedMethod1(DynamicContext di) {
      System.out.println("[Instrumentation process] PID: " + ProcessHandle.current().pid());
      }
      """;
    assertEquals(normalizeVariableNames(expectedCode), normalizeVariableNames(code));
  }
}
