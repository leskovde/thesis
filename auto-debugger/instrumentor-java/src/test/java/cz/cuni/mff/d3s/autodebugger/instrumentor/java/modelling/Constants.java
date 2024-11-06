package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.factories.MethodIdentifierFactory;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.*;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling.enums.ActivationTime;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling.enums.MarkerType;

import java.nio.file.Path;
import java.util.List;

public class Constants {
    public static final String targetClassName = "Test";
    public static final String targetMethodName = "test";
    public static final Path targetJarPath = Path.of("src/test/resources/targets/extraction/Test.jar");
    public static final PackageIdentifier packageIdentifier = new PackageIdentifier("targets.extraction");
    public static final ClassIdentifier mainClassIdentifier =
            new ClassIdentifier(
                    ClassIdentifierParameters.builder()
                            .className("Main")
                            .packageIdentifier(packageIdentifier)
                            .build());
    public static final ClassIdentifier testClassIdentifier =
            new ClassIdentifier(
                    ClassIdentifierParameters.builder()
                            .className(targetClassName)
                            .packageIdentifier(packageIdentifier)
                            .build());

    public static final MethodIdentifierParameters targetMethodIdentifierParameters =
            MethodIdentifierParameters.builder()
                    .ownerClassIdentifier(mainClassIdentifier)
                    .methodName(targetMethodName)
                    .returnType("void")
                    .build();
  public static final MethodIdentifierParameters instrumentationLogicIdentifierParameters =
      MethodIdentifierParameters.builder()
          .returnType("void")
          .ownerClassIdentifier(
                  new ClassIdentifier(
                          ClassIdentifierParameters
                                  .builder()
                                  .className("DiSLClass")
                                  .packageIdentifier(PackageIdentifier.DEFAULT_PACKAGE)
                                  .build()))
          .parameterTypes(List.of("DynamicContext"))
          .build();

    public static final MethodIdentifier targetMethodIdentifier = new MethodIdentifier(targetMethodIdentifierParameters);
    public static final MethodIdentifier instrumentationLogicMethodIdentifier = MethodIdentifierFactory.getInstance().generateIdentifier(instrumentationLogicIdentifierParameters);

    public static final DiSLMarker dislMarker = new DiSLMarker(MarkerType.BODY);

    public static final DiSLScope dislScope = new DiSLScope(targetMethodIdentifier);

    public static final DiSLAnnotation dislAnnotation = new DiSLAnnotation(ActivationTime.BEFORE, dislMarker, dislScope);

    public static final List<JavaValue> instrumentationLogicExports = List.of();

    public static final JavaArgument javaArgument = new JavaArgument(0, "String");

    public static final JavaField javaField = new JavaField("String", "testField", "Test");

    // TODO
    //public static final JavaVariable javaVariable = new JavaVariable(0, "String");

  public static final JavaPackageImport javaPackageImport =
      new JavaPackageImport(new PackageIdentifier("java.util.List"));

    public static final JavaPackage javaPackage = new JavaPackage(new PackageIdentifier("cz.cuni.mff.d3s.test"));
}
