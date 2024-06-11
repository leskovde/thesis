package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.factories.MethodIdentifierFactory;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.MethodIdentifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.MethodIdentifierParameters;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.PackageIdentifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling.enums.ActivationTime;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling.enums.MarkerType;

import java.util.List;

public class Constants {
    public static final String targetClassName = "Test";
    public static final String targetMethodName = "test";
    public static final MethodIdentifierParameters targetMethodIdentifierParameters =
            MethodIdentifierParameters.builder()
                    .className(targetClassName)
                    .methodName(targetMethodName)
                    .returnType("void")
                    .build();
    public static final MethodIdentifierParameters instrumentationLogicIdentifierParameters =
            MethodIdentifierParameters.builder()
                    .returnType("void")
                    .className("DiSLClass")
                    .parameterTypes(List.of("DynamicContext"))
                    .build();


    public static final MethodIdentifier targetMethodIdentifier = new MethodIdentifier(targetMethodIdentifierParameters);
    public static final MethodIdentifier instrumentationLogicMethodIdentifier = MethodIdentifierFactory.getInstance().generateIdentifier(instrumentationLogicIdentifierParameters);

    public static final DiSLMarker dislMarker = new DiSLMarker(MarkerType.BODY);

    public static final DiSLScope dislScope = new DiSLScope(targetMethodIdentifier);

    public static final DiSLAnnotation dislAnnotation = new DiSLAnnotation(ActivationTime.BEFORE, dislMarker, dislScope);

    public static final List<ExportableValue> instrumentationLogicExports = List.of();

    public static final JavaArgument javaArgument = new JavaArgument(0, "String");

    public static final JavaField javaField = new JavaField("String", "testField", "Test");

    public static final JavaVariable javaVariable = new JavaVariable(0, "String");

  public static final JavaPackageImport javaPackageImport =
      new JavaPackageImport(new PackageIdentifier("java.util.List"));

    public static final JavaPackage javaPackage = new JavaPackage(new PackageIdentifier("cz.cuni.mff.d3s.test"));
}
