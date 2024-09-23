package cz.cuni.mff.d3s.autodebugger.runner;

import cz.cuni.mff.d3s.autodebugger.analyzer.Trace;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.ArgumentIdentifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.ArgumentIdentifierParameters;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.MethodIdentifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.MethodIdentifierParameters;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.DiSLInstrumentor;
import java.nio.file.Path;
import java.util.List;
import picocli.CommandLine;

public class Main {
  public static void main(String[] args) {
    Arguments arguments = new Arguments();
    new CommandLine(arguments).parseArgs(args);

    // TODO: Select the implementation based on the language
    DiSLInstrumentor instrumentor =
        DiSLInstrumentor.builder()
            .applicationJarPath(Path.of("test.jar"))
            .method(
                new MethodIdentifier(
                    MethodIdentifierParameters.builder()
                        .className("Test")
                        .methodName("test")
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
    var resultPaths = instrumentor.runInstrumentation();
    new Trace(resultPaths);
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
