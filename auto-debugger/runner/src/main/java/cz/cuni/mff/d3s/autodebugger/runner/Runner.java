package cz.cuni.mff.d3s.autodebugger.runner;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.Instrumentor;
import cz.cuni.mff.d3s.autodebugger.runner.args.Arguments;
import cz.cuni.mff.d3s.autodebugger.runner.model.ModelBuilder;
import cz.cuni.mff.d3s.autodebugger.runner.orchestrator.OrchestratorFactory;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

@Slf4j
public class Runner {

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

      run(arguments);

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

  public static void run(Arguments arguments) {
    log.info("Starting auto-debugger with arguments:");
    log.info("  Application JAR: {}", arguments.applicationJarPath);
    log.info("  Source code path: {}", arguments.sourceCodePath);
    log.info("  Target method: {}", arguments.targetMethodReference);
    log.info("  Target parameters: {}", arguments.targetParameters);
    log.info("  Target fields: {}", arguments.targetFields);
    log.info("  Language: {}", arguments.language.getDisplayName());
    log.info("  Classpath: {}", arguments.classpath);

    // New orchestrator-based API
    var orchestrator = OrchestratorFactory.create(arguments.language);
    log.info("Created orchestrator for language: {}", arguments.language.getDisplayName());

    var runConfiguration = orchestrator.createRunConfiguration(arguments);
    log.info(
        "Created run configuration for method: {}", runConfiguration.getTargetMethod().getName());

    var instrumentationModel = orchestrator.buildInstrumentationModel(runConfiguration);
    log.info("Built instrumentation model");

    // For now, use the existing ModelBuilder approach for instrumentor creation
    // TODO: Complete the orchestrator.createInstrumentor implementation
    Instrumentor instrumentor = ModelBuilder.buildInstrumentor(arguments);
    log.info("Created instrumentor using ModelBuilder (fallback)");

    var analyzer = orchestrator.createAnalyzer(runConfiguration);
    log.info("Created analyzer");

    var testGenerator = orchestrator.createTestGenerator(runConfiguration);
    log.info("Created test generator with technique: {}", testGenerator.getGenerationTechnique());

    // New API
    //       var orchestrator = OrchestratorFactory.create(args.language);
    //          this adds certain strategies for the language
    //       TODO: Add the language argument
    //       var runConfiguration = orchestrator.createRunConfiguration(args);
    //          run configuration is going to contain the model-java stuff (or model-common if there
    // is any)
    //          ... MethodIdentifier, FieldIdentifier, etc.
    //       var instrumentationModel = orchestrator.buildInstrumentationModel(runConfiguration);
    //          ... can happen inside orchestrator.createInstrumentor transparently
    //       var instrumentor = orchestrator.createInstrumentor(instrumentationModel);
    //          the model is going to be specific to just the instrumentor - everything else is
    // still using run config
    //       var analyzer = orchestrator.createAnalyzer(runConfiguration);
    //       var testGenerator = orchestrator.createTestGenerator(runConfiguration);
    //       var testRunner = orchestrator.createTestRunner(runConfiguration);
    //
    //          main workflow: instrumentor -> analyzer -> test-generator
    //       var instrumentationPath = instrumentor.generateInstrumentation(instrumentationModel);
    //       var trace = analyzer.runAnalysis(instrumentationPath);
    //       var tests = testGenerator.generateTests(trace);
    //
    //          secondary workflow: test-runner
    //       var testResults = testRunner.runTests(tests);

    // Main workflow: instrumentor -> analyzer -> test-generator
    log.info("Running instrumentation...");
    var instrumentationPath = instrumentor.runInstrumentation();
    log.info("Instrumentation completed. Result paths: {}", instrumentationPath);

    if (instrumentationPath != null && !instrumentationPath.isEmpty()) {
      log.info("Running analysis...");
      var trace = analyzer.runAnalysis(instrumentationPath.get(0));
      log.info("Analysis completed. Trace collected.");

      log.info("Generating tests...");
      var tests = testGenerator.generateTests(trace);
      log.info("Test generation completed. Generated {} test files: {}", tests.size(), tests);

      // Secondary workflow: test-runner (optional)
      // var testRunner = orchestrator.createTestRunner(runConfiguration);
      // var testResults = testRunner.runTests(tests);
      // log.info("Test execution completed. Results: {}", testResults);
    } else {
      log.warn("No instrumentation paths returned, skipping analysis and test generation");
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
