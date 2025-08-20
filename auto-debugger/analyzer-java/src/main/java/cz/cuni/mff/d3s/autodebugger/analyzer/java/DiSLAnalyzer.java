package cz.cuni.mff.d3s.autodebugger.analyzer.java;

import cz.cuni.mff.d3s.autodebugger.analyzer.common.Analyzer;
import cz.cuni.mff.d3s.autodebugger.model.common.artifacts.InstrumentationResult;
import cz.cuni.mff.d3s.autodebugger.model.common.tests.TestSuite;
import cz.cuni.mff.d3s.autodebugger.model.java.JavaRunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.java.helper.DiSLPathHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Java-specific analyzer implementation that executes instrumented Java applications
 * and collects runtime traces through DiSL instrumentation.
 */
@Slf4j
@RequiredArgsConstructor
public class DiSLAnalyzer implements Analyzer {

    private static final int DEFAULT_TIMEOUT_SECONDS = 300; // 5 minutes

    private final JavaRunConfiguration runConfiguration;

    /**
     * Gets the timeout in seconds for process execution.
     * Protected to allow overriding in tests.
     */
    protected long getTimeoutSeconds() {
        return DEFAULT_TIMEOUT_SECONDS;
    }

    /**
     * Gets the run configuration.
     * Protected to allow access in tests.
     */
    protected JavaRunConfiguration getRunConfiguration() {
        return runConfiguration;
    }

    /**
     * Executes the instrumented Java application and collects runtime traces.
     * Runs the application as a separate process with DiSL instrumentation,
     * captures output streams, and deserializes the collected trace data.
     */
    @Override
    public TestSuite runAnalysis(InstrumentationResult instrumentation) {
        log.info("Starting Java analysis on instrumented application: {}", instrumentation);

        validateInstrumentation(instrumentation);
        Path instrumentationJarPath = instrumentation.getPrimaryArtifact();
        try {
            // Build the command to execute the instrumented JAR
            List<String> command = buildExecutionCommand(instrumentationJarPath);

            // Execute the instrumented application
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.directory(runConfiguration.getOutputDirectory().toFile());

            log.info("Executing command: {}", String.join(" ", command));
            Process process = processBuilder.start();

            // Capture output and error streams
            StringBuilder output = new StringBuilder();
            StringBuilder errorOutput = new StringBuilder();

            // Read output in separate threads
            Thread outputReader = new Thread(() -> readStream(process.getInputStream(), output));
            Thread errorReader = new Thread(() -> readStream(process.getErrorStream(), errorOutput));

            outputReader.start();
            errorReader.start();

            // Wait for process completion with timeout
            boolean finished = process.waitFor(getTimeoutSeconds(), TimeUnit.SECONDS);

            if (!finished) {
                log.warn("Analysis process timed out after {} seconds, terminating", getTimeoutSeconds());
                process.destroyForcibly();
                throw new RuntimeException("Analysis process timed out");
            }

            // Wait for output readers to finish
            outputReader.join(5000);
            errorReader.join(5000);

            int exitCode = process.exitValue();
            log.info("Analysis process completed with exit code: {}", exitCode);

            if (exitCode != 0) {
                log.error("Analysis process failed with exit code: {}", exitCode);
                log.error("Error output: {}", errorOutput);
            }

            log.info("Analysis output: {}", output);
            // Read generated test paths from results file created by Collector
            List<Path> generatedTests = new ArrayList<>();
            try {
                Path resultsFile = instrumentation.getResultsListPath();
                if (resultsFile == null) {
                    // Backward compatibility: fall back to scanning output dir for generated-tests-*.lst
                    Path resultsDir = runConfiguration.getOutputDirectory() != null
                            ? runConfiguration.getOutputDirectory()
                            : Path.of(System.getProperty("java.io.tmpdir"), "autodebugger-output");
                    try {
                        var candidates = java.nio.file.Files.list(resultsDir)
                            .filter(p -> p.getFileName().toString().startsWith("generated-tests-") && p.getFileName().toString().endsWith(".lst"))
                            .sorted((a,b) -> b.getFileName().toString().compareTo(a.getFileName().toString()))
                            .toList();
                        if (!candidates.isEmpty()) { resultsFile = candidates.get(0); }
                    } catch (Exception ignore) {}
                }
                if (resultsFile != null) {
                    log.info("Reading generated test list from {}", resultsFile);
                    if (java.nio.file.Files.exists(resultsFile)) {
                        var lines = java.nio.file.Files.readAllLines(resultsFile);
                        lines.forEach(l -> {
                            log.info("Generated test: {}", l);
                            generatedTests.add(Path.of(l));
                        });
                    } else {
                        log.warn("No generated test list file found at {} — falling back to scan output dir", resultsFile);
                        Path resultsDir = runConfiguration.getOutputDirectory();
                        if (resultsDir != null && java.nio.file.Files.exists(resultsDir)) {
                            try {
                                var candidates = java.nio.file.Files.list(resultsDir)
                                    .filter(p -> p.getFileName().toString().startsWith("generated-tests-") && p.getFileName().toString().endsWith(".lst"))
                                    .sorted((a,b) -> b.getFileName().toString().compareTo(a.getFileName().toString()))
                                    .toList();
                                if (!candidates.isEmpty()) {
                                    resultsFile = candidates.get(0);
                                } else {
                                    resultsFile = resultsDir.resolve("generated-tests.lst");
                                }
                                if (java.nio.file.Files.exists(resultsFile)) {
                                    var lines = java.nio.file.Files.readAllLines(resultsFile);
                                    lines.forEach(l -> {
                                        log.info("Generated test (fallback): {}", l);
                                        generatedTests.add(Path.of(l));
                                    });
                                }
                            } catch (Exception ignore) {}
                        }
                    }
                } else {
                    log.warn("No results list path available; no tests will be returned");
                }
            } catch (Exception ex) {
                log.warn("Failed to read generated test list", ex);
            }
            return TestSuite.builder()
                    .baseDirectory(runConfiguration.getOutputDirectory())
                    .testFiles(generatedTests)
                    .build();
        } catch (IOException | InterruptedException e) {
            log.error("Failed to execute instrumented application", e);
            throw new RuntimeException("Analysis execution failed", e);
        }
    }

    @Override
    public void validateInstrumentation(InstrumentationResult instrumentation) {
        if (instrumentation == null || instrumentation.getPrimaryArtifact() == null) {
            throw new IllegalArgumentException("Instrumentation primary artifact cannot be null");
        }
        var instrumentationPath = instrumentation.getPrimaryArtifact();
        if (!Files.exists(instrumentationPath)) {
            throw new IllegalArgumentException("Instrumentation file does not exist: " + instrumentationPath);
        }
        if (!instrumentationPath.toString().endsWith(".jar")) {
            throw new IllegalArgumentException("Expected JAR file for DiSL instrumentation, got: " + instrumentationPath);
        }
        log.debug("Instrumentation validation passed for: {}", instrumentationPath);
    }

   public List<String> buildExecutionCommand(Path instrumentationJarPath) {
        List<String> command = new ArrayList<>();

        // Run the disl.py script
        command.add("python3");
        command.add(DiSLPathHelper.getDislRunnerPath(runConfiguration).toString());

        // Add the DiSL home path
        command.add("-d");
        command.add(DiSLPathHelper.getDislHomePath(runConfiguration).toString());

        // Run with the client (target app), server (DiSL instrumentation server), and evaluation (ShadowVM)
        command.add("-cse");

        // Add the evaluation classpath
        // Note that this is not present in the basic distribution of DiSL - and might potentially be unnecessary
        // Subject to further testing...
        command.add("-e_cp");
        command.add("../test-generator-java/build/libs/*:../test-generator-common/build/libs/*:../model-common/build/libs/*:../model-java/build/libs/*");

        command.add("--");

        // Add the generated DiSL instrumentation JAR
        command.add(instrumentationJarPath.toString());

        // Add the target application JAR
        command.add("-jar");
        command.add(runConfiguration.getApplicationPath().toString());

        // Add runtime arguments for the target application
        if (!runConfiguration.getRuntimeArguments().isEmpty()) {
            command.addAll(runConfiguration.getRuntimeArguments());
        }

        return command;
    }

    private void readStream(java.io.InputStream inputStream, StringBuilder output) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            log.error("Error reading process stream", e);
        }
    }
}
