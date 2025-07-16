package cz.cuni.mff.d3s.autodebugger.analyzer.java;

import cz.cuni.mff.d3s.autodebugger.analyzer.common.Analyzer;
import cz.cuni.mff.d3s.autodebugger.model.common.RunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.java.Trace;
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
public class JavaAnalyzer implements Analyzer {
    
    private RunConfiguration configuration;
    private static final int DEFAULT_TIMEOUT_SECONDS = 300; // 5 minutes
    
    @Override
    public void configure(RunConfiguration configuration) {
        this.configuration = configuration;
        log.info("Configured Java analyzer for application: {}", configuration.getApplicationPath());
    }
    
    @Override
    public Trace runAnalysis(Path instrumentationPath) {
        return runAnalysis(instrumentationPath, new String[0]);
    }
    
    @Override
    public Trace runAnalysis(Path instrumentationPath, String[] runtimeArguments) {
        log.info("Starting Java analysis on instrumented application: {}", instrumentationPath);
        
        if (configuration == null) {
            throw new IllegalStateException("Analyzer must be configured before running analysis");
        }
        
        validateInstrumentation(instrumentationPath);
        
        try {
            // Build the command to execute the instrumented JAR
            List<String> command = buildExecutionCommand(instrumentationPath, runtimeArguments);
            
            // Execute the instrumented application
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.directory(configuration.getOutputDirectory().toFile());
            
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
                log.error("Error output: {}", errorOutput.toString());
            }
            
            log.debug("Analysis output: {}", output.toString());
            
            // Parse the collected trace data
            return parseTraceFromOutput(output.toString());
            
        } catch (IOException | InterruptedException e) {
            log.error("Failed to execute instrumented application", e);
            throw new RuntimeException("Analysis execution failed", e);
        }
    }

    @Override
    public void validateInstrumentation(Path instrumentationPath) {
        if (instrumentationPath == null) {
            throw new IllegalArgumentException("Instrumentation path cannot be null");
        }
        
        if (!Files.exists(instrumentationPath)) {
            throw new IllegalArgumentException("Instrumentation file does not exist: " + instrumentationPath);
        }
        
        String fileName = instrumentationPath.getFileName().toString().toLowerCase();
        if (!fileName.endsWith(".jar")) {
            throw new IllegalArgumentException("Expected JAR file for Java analysis, got: " + fileName);
        }
        
        log.debug("Instrumentation validation passed for: {}", instrumentationPath);
    }
    
    private List<String> buildExecutionCommand(Path instrumentationPath, String[] runtimeArguments) {
        List<String> command = new ArrayList<>();
        
        // Add Java executable
        command.add("java");
        
        // Add classpath if specified
        if (configuration.getClasspathEntries() != null && !configuration.getClasspathEntries().isEmpty()) {
            command.add("-cp");
            StringBuilder classpath = new StringBuilder();
            for (Path entry : configuration.getClasspathEntries()) {
                if (classpath.length() > 0) {
                    classpath.append(System.getProperty("path.separator"));
                }
                classpath.append(entry.toString());
            }
            // Add the instrumentation JAR to classpath
            classpath.append(System.getProperty("path.separator")).append(instrumentationPath.toString());
            command.add(classpath.toString());
        } else {
            // Just use the instrumentation JAR
            command.add("-jar");
            command.add(instrumentationPath.toString());
        }
        
        // Add runtime arguments
        if (runtimeArguments != null) {
            for (String arg : runtimeArguments) {
                command.add(arg);
            }
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
    
    private Trace parseTraceFromOutput(String output) {
        // For now, create a basic trace
        // In a real implementation, this would parse the DiSL collector output
        // and reconstruct the trace with all collected values
        log.info("Parsing trace from analysis output ({} characters)", output.length());
        
        Trace trace = new Trace();
        
        // TODO: Implement proper trace parsing from DiSL collector output
        // This would involve:
        // 1. Parsing the serialized trace data
        // 2. Reconstructing ExportableValue mappings
        // 3. Building the complete trace structure
        
        log.info("Created trace with basic structure (parsing not yet implemented)");
        return trace;
    }
}
