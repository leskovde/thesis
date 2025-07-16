package cz.cuni.mff.d3s.autodebugger.model.common;

import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.ExportableValue;
import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.Identifier;

import java.nio.file.Path;
import java.util.List;

/**
 * Interface representing a run configuration that contains all the necessary
 * information for instrumenting, analyzing, and generating tests for a target application.
 * This interface provides a language-agnostic contract that can be implemented
 * for different programming languages.
 */
public interface RunConfiguration {
    
    /**
     * Gets the path to the application JAR or executable file.
     * 
     * @return Path to the application file
     */
    Path getApplicationPath();
    
    /**
     * Gets the path to the source code directory.
     * 
     * @return Path to the source code
     */
    Path getSourceCodePath();
    
    /**
     * Gets the target method identifier that should be instrumented and analyzed.
     * 
     * @return Method identifier for the target method
     */
    Identifier getTargetMethod();
    
    /**
     * Gets the list of exportable values (parameters, fields, etc.) that should be tracked.
     * 
     * @return List of exportable values to track during execution
     */
    List<ExportableValue> getExportableValues();
    
    /**
     * Gets additional classpath entries needed for compilation and execution.
     * 
     * @return List of classpath entries
     */
    List<Path> getClasspathEntries();
    
    /**
     * Gets the output directory where generated files should be placed.
     * 
     * @return Output directory path
     */
    Path getOutputDirectory();
    
    /**
     * Validates that this configuration is complete and valid.
     * 
     * @throws IllegalStateException if the configuration is invalid
     */
    void validate();
}
