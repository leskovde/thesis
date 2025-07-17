package cz.cuni.mff.d3s.autodebugger.analyzer.java;

import cz.cuni.mff.d3s.autodebugger.analyzer.common.Analyzer;
import cz.cuni.mff.d3s.autodebugger.model.common.trace.Trace;
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
     * Executes the instrumented Java application and collects runtime traces.
     * Runs the application as a separate process with DiSL instrumentation,
     * captures output streams, and deserializes the collected trace data.
     */
    @Override
    public Trace runAnalysis(List<Path> instrumentationPaths) {
        log.info("Starting Java analysis on instrumented application: {}", instrumentationPaths);
        
        validateInstrumentation(instrumentationPaths);
        Path instrumentationJarPath = instrumentationPaths.getFirst();
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
            boolean finished = process.waitFor(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            
            if (!finished) {
                log.warn("Analysis process timed out after {} seconds, terminating", DEFAULT_TIMEOUT_SECONDS);
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
        } catch (IOException | InterruptedException e) {
            log.error("Failed to execute instrumented application", e);
            throw new RuntimeException("Analysis execution failed", e);
        }

        // TODO
        return new Trace();
    }

    @Override
    public void validateInstrumentation(List<Path> instrumentationPaths) {
        if (instrumentationPaths == null) {
            throw new IllegalArgumentException("Instrumentation path cannot be null");
        }

        if (instrumentationPaths.size() != 1) {
            throw new IllegalArgumentException("Expected exactly one instrumentation path, got: " + instrumentationPaths);
        }

        var instrumentationPath = instrumentationPaths.getFirst();
        
        if (!Files.exists(instrumentationPath)) {
            throw new IllegalArgumentException("Instrumentation file does not exist: " + instrumentationPath);
        }
        
        if (!instrumentationPath.endsWith(".jar")) {
            throw new IllegalArgumentException("Expected JAR file for DiSL instrumentation, got: " + instrumentationPath);
        }
        
        log.debug("Instrumentation validation passed for: {}", instrumentationPaths);
    }


//    private List<Path> instrumentApplication(Path instrumentationJarPath) {
//        log.info("Running DiSL instrumentation");
//
//        try {
//            var scriptProcess =
//                    new ProcessBuilder(
//                            "python3",
//                            DiSLPathHelper.getDislRunnerPath(runConfiguration).toString(),
//                            "-d",
//                            DiSLPathHelper.getDislHomePath(runConfiguration).toString(),
//                            "-cse",
//                            "-e_cp",
//                            "../test-generator-java/build/libs/*:../test-generator-common/build/libs/*:../model-java/build/libs/*",
//                            "--",
//                            instrumentationJarPath.toString(),
//                            "-jar",
//                            runConfiguration.getApplicationPath().toString())
//                            .start();
//            // Print stdout
//            try (var stdoutReader =
//                         new BufferedReader(new InputStreamReader(scriptProcess.getInputStream()))) {
//                String line;
//                while ((line = stdoutReader.readLine()) != null) {
//                    log.info(line);
//                }
//            }
//            // Print stderr
//            try (var stderrReader =
//                         new BufferedReader(new InputStreamReader(scriptProcess.getErrorStream()))) {
//                String line;
//                while ((line = stderrReader.readLine()) != null) {
//                    log.error(line);
//                }
//            }
//            scriptProcess.waitFor();
//            log.info("DiSL instrumentation finished");
//        } catch (Exception e) {
//            log.error("Failed to run DiSL instrumentation", e);
//        }
//        return List.of();
//    }

    private List<String> buildExecutionCommand(Path instrumentationJarPath) {
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
        command.add("../test-generator-java/build/libs/*:../test-generator-common/build/libs/*:../model-java/build/libs/*");

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
