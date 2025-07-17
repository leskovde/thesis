package cz.cuni.mff.d3s.autodebugger.runner;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.Instrumentor;
import cz.cuni.mff.d3s.autodebugger.runner.args.Arguments;
import cz.cuni.mff.d3s.autodebugger.runner.model.ModelBuilder;
import cz.cuni.mff.d3s.autodebugger.runner.orchestrator.Orchestrator;
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

    var orchestrator = new Orchestrator(arguments);
    log.info("Created orchestrator for language: {}", arguments.language.getDisplayName());

    var runConfiguration = orchestrator.createRunConfiguration(arguments);
    log.info(
        "Created run configuration for method: {}", runConfiguration.getTargetMethod().getName());

    var instrumentationModel = orchestrator.buildInstrumentationModel();
    log.info("Built instrumentation model");

    var instrumentationPaths = orchestrator.createInstrumentation(instrumentationModel);
    log.info("Created instrumentation: {}", instrumentationPaths);

    if (instrumentationPaths.isEmpty()) {
      log.warn("No instrumentation paths returned, skipping analysis and test generation");
      return;
    }

    log.info("Running analysis...");
    var trace = orchestrator.runAnalysis(instrumentationPaths);
    log.info("Analysis completed. Trace collected.");

    var tests = orchestrator.generateTests(trace);
    log.info("Test generation completed. Generated {} test files: {}", tests.size(), tests);

    var testResults = orchestrator.runTests(tests);
    log.info("Test execution completed. Results: {}", testResults);
  }
}
