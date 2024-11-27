package cz.cuni.mff.d3s.autodebugger.instrumentor.java;

import cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling.Constants;
import java.nio.file.Path;
import java.util.List;

import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.*;
import org.junit.jupiter.api.Test;

public class DiSLInstrumentorTests {

  @Test
  public void givenStaticMethod_whenInstrumentingArguments_thenValuesAreExtracted() {
    // given
    DiSLInstrumentor instrumentor =
        DiSLInstrumentor.builder()
            .applicationJarPath(Path.of("src/test/resources/targets/extraction/Test.jar"))
            .classpath(List.of(Constants.targetJarPath))
            .generatedCodeOutputDirectory(
                Path.of(
                    "../analyzer-disl/src/main/java/cz/cuni/mff/d3s/autodebugger/analyzer/disl/"))
            .jarOutputPath(Path.of("../analyzer-disl/build/libs/instrumentation.jar"))
            .dislRepositoryPath(Path.of("../../../disl"))
            .method(
                new MethodIdentifier(
                    MethodIdentifierParameters.builder()
                        .ownerClassIdentifier(Constants.testClassIdentifier)
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

  @Test
  public void givenInstanceMethod_whenInstrumentingInstanceFields_thenValuesAreExtracted() {
    // given
    DiSLInstrumentor instrumentor =
        DiSLInstrumentor.builder()
            .applicationJarPath(Constants.targetJarPath)
            .classpath(List.of(Constants.targetJarPath))
            .generatedCodeOutputDirectory(
                Path.of(
                    "../analyzer-disl/src/main/java/cz/cuni/mff/d3s/autodebugger/analyzer/disl/"))
            .jarOutputPath(Path.of("../analyzer-disl/build/libs/instrumentation.jar"))
            .dislRepositoryPath(Path.of("../../../disl"))
            .method(
                new MethodIdentifier(
                    MethodIdentifierParameters.builder()
                        .ownerClassIdentifier(Constants.testClassIdentifier)
                        .methodName("testAdd")
                        .returnType("void")
                        .build()))
            .exportedValues(
                List.of(
                    new FieldIdentifier(
                        FieldIdentifierParameters.builder()
                            .variableName("f")
                            .ownerClassIdentifier(Constants.testClassIdentifier)
                            // TODO:
                            //  Create a better abstraction for classes (types)
                            //  and use it for return types and parameter types
                            .variableType("int")
                            .build()),
                    new FieldIdentifier(
                        FieldIdentifierParameters.builder()
                            .variableName("g")
                            .ownerClassIdentifier(Constants.testClassIdentifier)
                            .variableType("int")
                            .build())))
            .build();

    // when
    var resultPaths = instrumentor.runInstrumentation();
  }

  @Test
  public void givenStaticMethod_whenInstrumentingReturnValue_thenValuesAreExtracted() {
    // given
    MethodIdentifier methodIdentifier = new MethodIdentifier(
            MethodIdentifierParameters.builder()
                    .ownerClassIdentifier(Constants.testClassIdentifier)
                    .methodName("testMul")
                    .returnType("void")
                    .build());
    DiSLInstrumentor instrumentor =
            DiSLInstrumentor.builder()
                    .applicationJarPath(Path.of("src/test/resources/targets/extraction/Test.jar"))
                    .classpath(List.of(Constants.targetJarPath))
                    .generatedCodeOutputDirectory(
                            Path.of(
                                    "../analyzer-disl/src/main/java/cz/cuni/mff/d3s/autodebugger/analyzer/disl/"))
                    .jarOutputPath(Path.of("../analyzer-disl/build/libs/instrumentation.jar"))
                    .dislRepositoryPath(Path.of("../../../disl"))
                    .method(methodIdentifier)
                    .exportedValues(
                            List.of(new ReturnValueIdentifier(new ReturnValueIdentifierParameters(methodIdentifier))))
                    .build();

    // when
    var resultPaths = instrumentor.runInstrumentation();
  }
}
