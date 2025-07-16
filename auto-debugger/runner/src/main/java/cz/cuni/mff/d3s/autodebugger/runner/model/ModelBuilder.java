package cz.cuni.mff.d3s.autodebugger.runner.model;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.Instrumentor;
import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.MethodIdentifier;
import cz.cuni.mff.d3s.autodebugger.runner.args.Arguments;
import cz.cuni.mff.d3s.autodebugger.runner.parsing.MethodSignatureParsingStrategy;
import cz.cuni.mff.d3s.autodebugger.runner.parsing.MethodSignatureParsingStrategyFactory;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Main pipeline class that converts CLI/plugin arguments into a DiSLInstrumentor instance.
 * This class orchestrates the parsing and conversion of all input arguments into the
 * appropriate model objects required by the DiSL instrumentation framework.
 */
@Slf4j
public class ModelBuilder {
    
    /**
     * Builds an Instrumentor from the provided arguments using language-specific strategies.
     *
     * @param arguments The parsed command line arguments
     * @return Configured Instrumentor instance
     * @throws IllegalArgumentException if any argument is invalid
     */
    public static Instrumentor buildInstrumentor(Arguments arguments) {
        log.info("Building Instrumentor from arguments");

        // Validate required arguments
        validateArguments(arguments);

        // Get paths
        Path applicationPath = Path.of(arguments.applicationJarPath);
        Path sourceCodePath = Path.of(arguments.sourceCodePath);

        // Get the appropriate parsing strategy based on the application type
        MethodSignatureParsingStrategy strategy = MethodSignatureParsingStrategyFactory.getStrategyForApplication(applicationPath);

        // Validate configuration for this language
        strategy.validateConfiguration(applicationPath, sourceCodePath);

        // Parse the method reference
        MethodIdentifier methodIdentifier = strategy.parseMethodReference(arguments.targetMethodReference);

        // Convert target parameters and fields to ExportableValues
        List<ExportableValue> parameterValues = strategy.parseTargetParameters(arguments.targetParameters, methodIdentifier);
        List<ExportableValue> fieldValues = strategy.parseTargetFields(arguments.targetFields, methodIdentifier);

        // Combine all exportable values
        List<ExportableValue> exportableValues = new ArrayList<>();
        exportableValues.addAll(parameterValues);
        exportableValues.addAll(fieldValues);

        // Create the instrumentor using the strategy
        Instrumentor instrumentor = strategy.createInstrumentor(applicationPath, sourceCodePath, methodIdentifier, exportableValues);

        log.debug("Instrumentor configuration: app={}, method={}, exportedValues={}",
                 applicationPath, methodIdentifier.getName(), exportableValues.size());

        return instrumentor;
    }
    
    /**
     * Builds an Instrumentor from individual parameters.
     * This method provides a more direct API for programmatic usage.
     *
     * @param applicationPath Path to the target application
     * @param sourceCodePath Path to the target application's source code
     * @param targetMethodReference Method reference in format "package.Class.method(param1,param2,...)"
     * @param targetParameters List of target parameters in format "slot:type" or "type:name"
     * @param targetFields List of target fields in format "type:name"
     * @return Configured Instrumentor instance
     */
    public static Instrumentor buildInstrumentor(
            String applicationPath,
            String sourceCodePath,
            String targetMethodReference,
            List<String> targetParameters,
            List<String> targetFields) {

        // Create Arguments object
        Arguments arguments = new Arguments();
        arguments.applicationJarPath = applicationPath;
        arguments.sourceCodePath = sourceCodePath;
        arguments.targetMethodReference = targetMethodReference;
        arguments.targetParameters = targetParameters != null ? targetParameters : new ArrayList<>();
        arguments.targetFields = targetFields != null ? targetFields : new ArrayList<>();

        return buildInstrumentor(arguments);
    }
    
    /**
     * Validates that all required arguments are present and valid.
     * 
     * @param arguments The arguments to validate
     * @throws IllegalArgumentException if any required argument is missing or invalid
     */
    private static void validateArguments(Arguments arguments) {
        if (arguments == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        
        if (arguments.applicationJarPath == null || arguments.applicationJarPath.trim().isEmpty()) {
            throw new IllegalArgumentException("Application JAR path is required");
        }
        
        if (arguments.sourceCodePath == null || arguments.sourceCodePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Source code path is required");
        }
        
        if (arguments.targetMethodReference == null || arguments.targetMethodReference.trim().isEmpty()) {
            throw new IllegalArgumentException("Target method reference is required");
        }
        
        // Validate that the JAR file exists
        Path jarPath = Path.of(arguments.applicationJarPath);
        if (!jarPath.toFile().exists()) {
            throw new IllegalArgumentException("Application JAR file does not exist: " + arguments.applicationJarPath);
        }
        
        // Validate that the source code path exists
        Path sourcePath = Path.of(arguments.sourceCodePath);
        if (!sourcePath.toFile().exists()) {
            throw new IllegalArgumentException("Source code path does not exist: " + arguments.sourceCodePath);
        }
        
        log.debug("Arguments validation passed");
    }
    
    /**
     * Creates a sample Instrumentor for testing purposes.
     * This method demonstrates the usage of the ModelBuilder with concrete examples.
     *
     * @return Sample Instrumentor instance
     */
    public static Instrumentor createSampleInstrumentor() {
        return buildInstrumentor(
            "test.jar",
            "src/main/java",
            "org.example.Test.testMethod(int,java.lang.String)",
            List.of("0:int", "1:java.lang.String"),
            List.of("int:counter", "java.lang.String:name")
        );
    }
}
