package cz.cuni.mff.d3s.autodebugger.runner.parsing;

import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.ExportableValue;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.MethodIdentifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.Instrumentor;

import java.nio.file.Path;
import java.util.List;

/**
 * Strategy interface for parsing method signatures and creating instrumentors
 * for different programming languages.
 */
public interface MethodSignatureParsingStrategy {
    
    /**
     * Gets the language this strategy supports.
     * 
     * @return The programming language name (e.g., "java", "python", "javascript")
     */
    String getLanguage();
    
    /**
     * Parses a method reference string and creates a MethodIdentifier.
     * 
     * @param methodReference The method reference string
     * @return Parsed MethodIdentifier
     * @throws IllegalArgumentException if the method reference is invalid
     */
    MethodIdentifier parseMethodReference(String methodReference);
    
    /**
     * Converts target parameter strings to ExportableValue objects.
     * 
     * @param targetParameters List of parameter strings
     * @param methodIdentifier The method these parameters belong to
     * @return List of ExportableValue objects representing parameters
     */
    List<ExportableValue> parseTargetParameters(List<String> targetParameters, MethodIdentifier methodIdentifier);
    
    /**
     * Converts target field strings to ExportableValue objects.
     * 
     * @param targetFields List of field strings
     * @param methodIdentifier The method whose class contains these fields
     * @return List of ExportableValue objects representing fields
     */
    List<ExportableValue> parseTargetFields(List<String> targetFields, MethodIdentifier methodIdentifier);
    
    /**
     * Creates an instrumentor instance for this language.
     * 
     * @param applicationPath Path to the application (JAR, executable, etc.)
     * @param sourceCodePath Path to the source code
     * @param methodIdentifier The target method
     * @param exportableValues The values to export during instrumentation
     * @return Configured instrumentor instance
     */
    Instrumentor createInstrumentor(
        Path applicationPath,
        Path sourceCodePath,
        MethodIdentifier methodIdentifier,
        List<ExportableValue> exportableValues
    );
    
    /**
     * Validates that the provided paths and configuration are valid for this language.
     * 
     * @param applicationPath Path to the application
     * @param sourceCodePath Path to the source code
     * @throws IllegalArgumentException if the configuration is invalid
     */
    void validateConfiguration(Path applicationPath, Path sourceCodePath);
}
