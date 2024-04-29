package cz.cuni.mff.d3s.autodebugger.runner;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.enums.ExportableValueType;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.factories.IdentifierFactory;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.IdentifierParameters;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.MethodIdentifierParameters;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.VariableIdentifierParameters;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.DiSLInstrumentor;

import java.util.List;


public class Main {
  public static void main(String[] args) {
//    if (args.length != 2) {
//      System.out.println("Please provide program path and procedure as arguments.");
//      return;
//    }
//    Arguments arguments = new Arguments(args[0], args[1]);

    // TODO: Select the implementation based on the language
    DiSLInstrumentor instrumentor = DiSLInstrumentor.builder()
            .applicationPath("test.jar")
            .methods(List.of(IdentifierFactory.createFrom(new IdentifierParameters(
                            MethodIdentifierParameters.builder()
                                    .className("Test")
                                    .methodName("test")
                                    .returnType("void")
                                    .build()))))
            .variables(List.of(IdentifierFactory.createFrom(new IdentifierParameters(
                                    VariableIdentifierParameters.builder()
                                            .variableType("int")
                                            .variableName("a")
                                            .exportableType(ExportableValueType.VARIABLE)
                                            .build()))))
            .build();
    instrumentor.runInstrumentation();
  }
}
