package cz.cuni.mff.d3s.autodebugger.instrumentor.java;

import cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling.Constants;
import java.nio.file.Path;
import java.util.List;

import cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling.DiSLModel;
import cz.cuni.mff.d3s.autodebugger.model.java.JavaRunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiSLInstrumentorTests {

  @Test
  public void givenStaticMethod_whenInstrumentingArguments_thenValuesAreExtracted() {
    JavaRunConfiguration runConfiguration = JavaRunConfiguration.builder()
            .applicationPath(Path.of("src/test/resources/targets/extraction/Test.jar"))
            .classpathEntry(Constants.targetJarPath)
            .dislHomePath(Path.of("../../../disl"))
            .sourceCodePath(Path.of("src/test/resources/targets/extraction"))
            .targetMethod(new JavaMethodIdentifier(
                    MethodIdentifierParameters.builder()
                        .ownerClassIdentifier(Constants.testClassIdentifier)
                        .methodName("testMul")
                        .returnType("void")
                        .build()))
            .exportableValues(
                    List.of(
                            new JavaArgumentIdentifier(
                                    ArgumentIdentifierParameters.builder()
                                            .argumentSlot(0)
                                            .variableType("int")
                                            .build()),
                            new JavaArgumentIdentifier(
                                    ArgumentIdentifierParameters.builder()
                                            .argumentSlot(1)
                                            .variableType("int")
                                            .build())))
            .build();

    DiSLModel model = new DiSLModel(
            new JavaMethodIdentifier(Constants.targetMethodIdentifierParameters),
            List.of(
                    new JavaArgumentIdentifier(
                            ArgumentIdentifierParameters.builder()
                                    .argumentSlot(0)
                                    .variableType("int")
                                    .build()),
                    new JavaArgumentIdentifier(
                            ArgumentIdentifierParameters.builder()
                                    .argumentSlot(1)
                                    .variableType("int")
                                    .build())));

    Path instrumentationJarPath = Path.of("../analyzer-disl/build/libs/instrumentation.jar");
    DiSLInstrumentor instrumentor =
        DiSLInstrumentor.builder()
            .instrumentationClassName(new JavaClassIdentifier(
                    ClassIdentifierParameters.builder()
                            .packageIdentifier(JavaPackageIdentifier.DEFAULT_PACKAGE)
                            .className("DiSLClass")
                            .build()))
            .runConfiguration(runConfiguration)
            .generatedCodeOutputDirectory(
                Path.of(
                    "../analyzer-disl/src/main/java/cz/cuni/mff/d3s/autodebugger/analyzer/disl/"))
            .jarOutputPath(instrumentationJarPath)
            .build();

    // when
    var resultPaths = instrumentor.generateInstrumentation(model);
    
    // then
    assertEquals(1, resultPaths.size());
    assertEquals(instrumentationJarPath, resultPaths.getFirst());
  }

  @Test
  public void givenInstanceMethod_whenInstrumentingInstanceFields_thenValuesAreExtracted() {
    // given
    JavaRunConfiguration runConfiguration = JavaRunConfiguration.builder()
            .applicationPath(Constants.targetJarPath)
            .classpathEntry(Constants.targetJarPath)
            .dislHomePath(Path.of("../../../disl"))
            .sourceCodePath(Path.of("src/test/resources/targets/extraction"))
            .targetMethod(new JavaMethodIdentifier(
                    MethodIdentifierParameters.builder()
                        .ownerClassIdentifier(Constants.testClassIdentifier)
                        .methodName("testAdd")
                        .returnType("void")
                        .build()))
            .exportableValues(
                    List.of(
                            new JavaFieldIdentifier(
                                    FieldIdentifierParameters.builder()
                                            .variableName("f")
                                            .ownerClassIdentifier(Constants.testClassIdentifier)
                                            // TODO:
                                            //  Create a better abstraction for classes (types)
                                            //  and use it for return types and parameter types
                                            .variableType("int")
                                            .build()),
                            new JavaFieldIdentifier(
                                    FieldIdentifierParameters.builder()
                                            .variableName("g")
                                            .ownerClassIdentifier(Constants.testClassIdentifier)
                                            .variableType("int")
                                            .build())))
            .build();

    DiSLModel model = new DiSLModel(
            new JavaMethodIdentifier(
                    MethodIdentifierParameters.builder()
                        .ownerClassIdentifier(Constants.testClassIdentifier)
                        .methodName("testAdd")
                        .returnType("void")
                        .build()),
            List.of(
                    new JavaFieldIdentifier(
                            FieldIdentifierParameters.builder()
                                    .variableName("f")
                                    .ownerClassIdentifier(Constants.testClassIdentifier)
                                    .variableType("int")
                                    .build()),
                    new JavaFieldIdentifier(
                            FieldIdentifierParameters.builder()
                                    .variableName("g")
                                    .ownerClassIdentifier(Constants.testClassIdentifier)
                                    .variableType("int")
                                    .build())));

    Path instrumentationJarPath = Path.of("../analyzer-disl/build/libs/instrumentation.jar");
    DiSLInstrumentor instrumentor =
        DiSLInstrumentor.builder()
            .instrumentationClassName(new JavaClassIdentifier(
                    ClassIdentifierParameters.builder()
                            .packageIdentifier(JavaPackageIdentifier.DEFAULT_PACKAGE)
                            .className("DiSLClass")
                            .build()))
            .runConfiguration(runConfiguration)
            .generatedCodeOutputDirectory(
                Path.of(
                    "../analyzer-disl/src/main/java/cz/cuni/mff/d3s/autodebugger/analyzer/disl/"))
            .jarOutputPath(instrumentationJarPath)
            .build();

    // when
    var resultPaths = instrumentor.generateInstrumentation(model);

    // then
    assertEquals(1, resultPaths.size());
    assertEquals(instrumentationJarPath, resultPaths.getFirst());
  }

  @Test
  @Disabled
  public void givenStaticMethod_whenInstrumentingReturnValue_thenValuesAreExtracted() {
    // given
    JavaMethodIdentifier methodIdentifier = new JavaMethodIdentifier(
            MethodIdentifierParameters.builder()
                    .ownerClassIdentifier(Constants.testClassIdentifier)
                    .methodName("testMul")
                    .returnType("void")
                    .build());

    JavaRunConfiguration runConfiguration = JavaRunConfiguration.builder()
            .applicationPath(Path.of("src/test/resources/targets/extraction/Test.jar"))
            .classpathEntry(Constants.targetJarPath)
            .dislHomePath(Path.of("../../../disl"))
            .sourceCodePath(Path.of("src/test/resources/targets/extraction"))
            .targetMethod(methodIdentifier)
            .exportableValues(
                    List.of(new JavaReturnValueIdentifier(new ReturnValueIdentifierParameters(methodIdentifier))))
            .build();

    DiSLModel model = new DiSLModel(
            methodIdentifier,
            List.of(new JavaReturnValueIdentifier(new ReturnValueIdentifierParameters(methodIdentifier))));

    Path instrumentationJarPath = Path.of("../analyzer-disl/build/libs/instrumentation.jar");
    DiSLInstrumentor instrumentor =
            DiSLInstrumentor.builder()
                    .instrumentationClassName(new JavaClassIdentifier(
                            ClassIdentifierParameters.builder()
                                    .packageIdentifier(JavaPackageIdentifier.DEFAULT_PACKAGE)
                                    .className("DiSLClass")
                                    .build()))
                    .runConfiguration(runConfiguration)
                    .generatedCodeOutputDirectory(
                            Path.of(
                                    "../analyzer-disl/src/main/java/cz/cuni/mff/d3s/autodebugger/analyzer/disl/"))
                    .jarOutputPath(instrumentationJarPath)
                    .build();

    // when
    var resultPaths = instrumentor.generateInstrumentation(model);

    // then
    assertEquals(1, resultPaths.size());
    assertEquals(instrumentationJarPath, resultPaths.getFirst());
  }
}
