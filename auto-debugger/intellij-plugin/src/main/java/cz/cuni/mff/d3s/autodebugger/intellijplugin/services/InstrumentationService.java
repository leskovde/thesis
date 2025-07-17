package cz.cuni.mff.d3s.autodebugger.intellijplugin.services;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import cz.cuni.mff.d3s.autodebugger.intellijplugin.model.ApplicationRunConfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Service for running instrumentation and analysis on target applications.
 * This service integrates with the DiSLInstrumentor to perform dynamic analysis.
 */
@Service(Service.Level.PROJECT)
public final class InstrumentationService {
    private static final Logger LOG = Logger.getInstance(InstrumentationService.class);
    
    private final Project project;
    
    /**
     * Creates a new instrumentation service for the specified project.
     *
     * @param project The project
     */
    public InstrumentationService(Project project) {
        this.project = project;
    }
    
    /**
     * Gets the instrumentation service for the specified project.
     *
     * @param project The project
     * @return The instrumentation service
     */
    public static InstrumentationService getInstance(Project project) {
        return project.getService(InstrumentationService.class);
    }
    
    /**
     * Runs instrumentation on the target application specified in the run configuration.
     * This method executes the instrumentation asynchronously and returns a future that
     * completes when the instrumentation is finished.
     *
     * @param configuration The run configuration
     * @param outputConsumer A consumer for process output
     * @return A future that completes with the paths to the generated files
     */
    public CompletableFuture<List<Path>> runInstrumentation(
            ApplicationRunConfiguration configuration,
            OutputConsumer outputConsumer) {
        
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Build the command to run the DiSLInstrumentor
                List<String> command = buildInstrumentationCommand(configuration);
                
                // Log the command for debugging
                LOG.info("Running instrumentation command: " + String.join(" ", command));
                outputConsumer.consumeOutput("Running instrumentation: " + String.join(" ", command));
                
                // Start the process
                ProcessBuilder processBuilder = new ProcessBuilder(command);
                processBuilder.redirectErrorStream(true); // Merge stderr into stdout
                
                // Set environment variables
                Map<String, String> env = processBuilder.environment();
                env.putAll(configuration.getEnvironmentVariables());
                
                // Set working directory if specified
                if (configuration.getWorkingDirectory() != null) {
                    processBuilder.directory(configuration.getWorkingDirectory().toFile());
                }
                
                Process process = processBuilder.start();
                
                // Read the output
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        outputConsumer.consumeOutput(line);
                    }
                }
                
                // Wait for the process to complete
                int exitCode = process.waitFor();
                
                if (exitCode != 0) {
                    outputConsumer.consumeOutput("Instrumentation failed with exit code: " + exitCode);
                    throw new RuntimeException("Instrumentation failed with exit code: " + exitCode);
                }
                
                outputConsumer.consumeOutput("Instrumentation completed successfully.");
                
                // In a real implementation, parse the output to get the paths to the generated files
                // For now, return an empty list
                return new ArrayList<>();
                
            } catch (IOException | InterruptedException e) {
                LOG.error("Error running instrumentation", e);
                outputConsumer.consumeOutput("Error running instrumentation: " + e.getMessage());
                throw new RuntimeException("Error running instrumentation", e);
            }
        });
    }
    
    /**
     * Builds the command to run the DiSLInstrumentor.
     *
     * @param configuration The run configuration
     * @return The command as a list of strings
     */
    private List<String> buildInstrumentationCommand(ApplicationRunConfiguration configuration) {
        List<String> command = new ArrayList<>();
        
        // Add the Java command
        command.add("java");
        
        // Add classpath for the runner module
        command.add("-cp");
        command.add("../runner/build/libs/*");
        
        // Add the main class
        command.add("cz.cuni.mff.d3s.autodebugger.runner.Runner");
        
        // Add the application JAR path
        command.add("--jar");
        command.add(configuration.getApplicationJarPath().toString());
        
        // Add the main class
        command.add("--class");
        command.add(configuration.getMainClass());
        
        // Parse the target method reference to extract method name and return type
        String methodRef = configuration.getTargetMethodReference();
        int lastDot = methodRef.lastIndexOf('.');
        int openParen = methodRef.indexOf('(');
        
        if (lastDot > 0 && openParen > lastDot) {
            String methodName = methodRef.substring(lastDot + 1, openParen);
            
            // Add the method name
            command.add("--method");
            command.add(methodName);
            
            // For simplicity, assume void return type
            // In a real implementation, parse the method signature
            command.add("--return");
            command.add("void");
        } else {
            throw new IllegalArgumentException("Invalid method reference: " + methodRef);
        }
        
        // Add program arguments if any
        if (!configuration.getProgramArguments().isEmpty()) {
            command.add("--");
            command.addAll(configuration.getProgramArguments());
        }
        
        return command;
    }
    
    /**
     * Interface for consuming output from the instrumentation process.
     */
    public interface OutputConsumer {
        /**
         * Consumes a line of output from the instrumentation process.
         *
         * @param line The line of output
         */
        void consumeOutput(String line);
    }
}