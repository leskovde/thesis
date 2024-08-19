package cz.cuni.mff.d3s.autodebugger.instrumentor.java;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.enums.ExportableValueType;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.factories.IdentifierFactory;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.IdentifierParameters;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.MethodIdentifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.MethodIdentifierParameters;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.VariableIdentifierParameters;
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
            .methods(
                List.of(
                    new MethodIdentifier(
                        MethodIdentifierParameters.builder()
                            .className("Main")
                            .methodName("testMul")
                            .returnType("void")
                            .build())))
            .variables(
                List.of(
                    IdentifierFactory.createFrom(
                        new IdentifierParameters(
                            VariableIdentifierParameters.builder()
                                .variableType("int")
                                .variableName("a")
                                .exportableType(ExportableValueType.VARIABLE)
                                .build())),
                        IdentifierFactory.createFrom(
                                new IdentifierParameters(
                                        VariableIdentifierParameters.builder()
                                                .variableType("int")
                                                .variableName("b")
                                                .exportableType(ExportableValueType.VARIABLE)
                                                .build()))
                        ))
            .build();

    // when
    var resultPaths = instrumentor.runInstrumentation();
  }
}
