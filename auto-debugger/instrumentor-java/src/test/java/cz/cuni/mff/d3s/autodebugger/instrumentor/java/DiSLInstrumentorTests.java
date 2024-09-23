package cz.cuni.mff.d3s.autodebugger.instrumentor.java;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.ArgumentIdentifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.ArgumentIdentifierParameters;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.MethodIdentifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.MethodIdentifierParameters;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DiSLInstrumentorTests {

  @Test
  public void givenSingleMethodWithLocalVariables_whenInstrumentingCode_thenValuesAreExtracted() {
    // given
    DiSLInstrumentor instrumentor =
        DiSLInstrumentor.builder()
            .applicationJarPath(Path.of("src/test/resources/targets/localvariables/Test.jar"))
            .generatedCodeOutputDirectory(
                Path.of(
                    "../analyzer-disl/src/main/java/cz/cuni/mff/d3s/autodebugger/analyzer/disl/"))
            .jarOutputPath(Path.of("../analyzer-disl/build/libs/instrumentation.jar"))
            .dislRepositoryPath(Path.of("../../../disl"))
            .method(
                new MethodIdentifier(
                    MethodIdentifierParameters.builder()
                        .className("Main")
                        .methodName("testMul")
                        .returnType("void")
                        .build()))
            .exportedValues(
                List.of(
                    new ArgumentIdentifier(
                        ArgumentIdentifierParameters.builder()
                            .argumentSlot(0)
                            .variableType("int")
                            .build()),
                    new ArgumentIdentifier(
                        ArgumentIdentifierParameters.builder()
                            .argumentSlot(1)
                            .variableType("int")
                            .build())))
            .build();

    // when
    var resultPaths = instrumentor.runInstrumentation();
  }
}
