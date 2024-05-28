package cz.cuni.mff.d3s.autodebugger.runner;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.enums.ExportableValueType;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.factories.IdentifierFactory;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.IdentifierParameters;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.MethodIdentifierParameters;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.VariableIdentifierParameters;
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
    var resultPath = instrumentor.runInstrumentation();

  }
}

/*
// Mozny format vstupu
test.jar
void Test.test()
int a
int b
 */

/*
// Mozne spousteni z prikazove radky
./runner.exe test.jar Test.test()
 */


/*
// Oznacim, ze chci sledovat foo
// + vsechny parametry, navratovou hodnotu
// + fieldy pro setup objektu
String foo(int bar) {
  ...
  api = Repository.getInstance()
  ...
}

knownValuesForBar = {0, 1, 2}
knownFieldsForThis = {3, 4, 5}

// Priklad UT
void generatedTest_1_3() {
  // Arrange
  Object o = new TestSomething();
  setup(o, 3);

  // Act
  result = o.foo(bar: 0);

  // Assert
  assert()
}

// Priklad testu vnitrku - mozna dalsi faze pro padajici IT
// Foo upraveno pomoci Delta Debugging nebo jineho rezani
void generatedTestReduced_1_3() {
  bar = 0;
  ...
  api = Repository.getInstance()
  ...
}

 */