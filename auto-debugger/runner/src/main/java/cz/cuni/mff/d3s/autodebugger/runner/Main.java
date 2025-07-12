package cz.cuni.mff.d3s.autodebugger.runner;

import cz.cuni.mff.d3s.autodebugger.model.java.Trace;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.Instrumentor;
import cz.cuni.mff.d3s.autodebugger.runner.model.ModelBuilder;
import picocli.CommandLine;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
  public static void main(String[] args) {
    try {
      // Parse command line arguments
      Arguments arguments = new Arguments();
      CommandLine commandLine = new CommandLine(arguments);
      commandLine.parseArgs(args);

      // Check if help was requested
      if (commandLine.isUsageHelpRequested()) {
        commandLine.usage(System.out);
        return;
      }

      log.info("Starting auto-debugger with arguments:");
      log.info("  Application JAR: {}", arguments.applicationJarPath);
      log.info("  Source code path: {}", arguments.sourceCodePath);
      log.info("  Target method: {}", arguments.targetMethodReference);
      log.info("  Target parameters: {}", arguments.targetParameters);
      log.info("  Target fields: {}", arguments.targetFields);

      // Build the instrumentor using the new pipeline
      Instrumentor instrumentor = ModelBuilder.buildInstrumentor(arguments);

      // Run instrumentation
      log.info("Running instrumentation...");
      var resultPaths = instrumentor.runInstrumentation();

      log.info("Instrumentation completed. Result paths: {}", resultPaths);

      // Initialize trace (placeholder for future trace processing)
      new Trace();

    } catch (CommandLine.ParameterException e) {
      log.error("Invalid command line arguments: {}", e.getMessage());
      System.err.println("Error: " + e.getMessage());
      new CommandLine(new Arguments()).usage(System.err);
      System.exit(1);
    } catch (Exception e) {
      log.error("Failed to run auto-debugger", e);
      System.err.println("Error: " + e.getMessage());
      System.exit(1);
    }
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
