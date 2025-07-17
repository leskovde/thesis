package cz.cuni.mff.d3s.autodebugger.intellijplugin.model;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a run configuration for the auto-debugger.
 * This class encapsulates all the necessary information required to execute
 * a complete analysis and test generation cycle for a specific debugging target.
 */
public class ApplicationRunConfiguration implements Serializable {
    private static final long serialVersionUID = 1L;

    // Configuration metadata
    private String name;
    private String description;

    // Target application
    private Path applicationJarPath;

    // Execution parameters
    private String mainClass;
    private List<String> programArguments;
    private Path workingDirectory;
    private Map<String, String> environmentVariables;

    // Analysis targets
    private String targetMethodReference;
    private List<TargetValue> targetValues;

    /**
     * Creates a new run configuration with the specified name.
     *
     * @param name The name of the configuration
     */
    public ApplicationRunConfiguration(String name) {
        this.name = name;
        this.description = "";
        this.programArguments = new ArrayList<>();
        this.environmentVariables = new HashMap<>();
        this.targetValues = new ArrayList<>();
    }

    /**
     * Gets the name of this configuration.
     *
     * @return The configuration name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this configuration.
     *
     * @param name The new configuration name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of this configuration.
     *
     * @return The configuration description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of this configuration.
     *
     * @param description The new configuration description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the path to the target application JAR.
     *
     * @return The path to the application JAR
     */
    public Path getApplicationJarPath() {
        return applicationJarPath;
    }

    /**
     * Sets the path to the target application JAR.
     *
     * @param applicationJarPath The path to the application JAR
     */
    public void setApplicationJarPath(Path applicationJarPath) {
        this.applicationJarPath = applicationJarPath;
    }

    /**
     * Gets the main class of the target application.
     *
     * @return The main class name
     */
    public String getMainClass() {
        return mainClass;
    }

    /**
     * Sets the main class of the target application.
     *
     * @param mainClass The main class name
     */
    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    /**
     * Gets the program arguments for the target application.
     *
     * @return The list of program arguments
     */
    public List<String> getProgramArguments() {
        return programArguments;
    }

    /**
     * Sets the program arguments for the target application.
     *
     * @param programArguments The list of program arguments
     */
    public void setProgramArguments(List<String> programArguments) {
        this.programArguments = programArguments;
    }

    /**
     * Gets the working directory for the target application.
     *
     * @return The working directory path
     */
    public Path getWorkingDirectory() {
        return workingDirectory;
    }

    /**
     * Sets the working directory for the target application.
     *
     * @param workingDirectory The working directory path
     */
    public void setWorkingDirectory(Path workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    /**
     * Gets the environment variables for the target application.
     *
     * @return The map of environment variables
     */
    public Map<String, String> getEnvironmentVariables() {
        return environmentVariables;
    }

    /**
     * Sets the environment variables for the target application.
     *
     * @param environmentVariables The map of environment variables
     */
    public void setEnvironmentVariables(Map<String, String> environmentVariables) {
        this.environmentVariables = environmentVariables;
    }

    /**
     * Gets the target method reference.
     *
     * @return The target method reference
     */
    public String getTargetMethodReference() {
        return targetMethodReference;
    }

    /**
     * Sets the target method reference.
     *
     * @param targetMethodReference The target method reference
     */
    public void setTargetMethodReference(String targetMethodReference) {
        this.targetMethodReference = targetMethodReference;
    }

    /**
     * Gets the list of target values to be monitored.
     *
     * @return The list of target values
     */
    public List<TargetValue> getTargetValues() {
        return targetValues;
    }

    /**
     * Sets the list of target values to be monitored.
     *
     * @param targetValues The list of target values
     */
    public void setTargetValues(List<TargetValue> targetValues) {
        this.targetValues = targetValues;
    }

    /**
     * Adds a target value to be monitored.
     *
     * @param targetValue The target value to add
     */
    public void addTargetValue(TargetValue targetValue) {
        this.targetValues.add(targetValue);
    }

    /**
     * Validates this configuration.
     *
     * @return true if the configuration is valid, false otherwise
     */
    public boolean isValid() {
        return applicationJarPath != null && 
               mainClass != null && !mainClass.isEmpty() &&
               targetMethodReference != null && !targetMethodReference.isEmpty();
    }

    /**
     * Returns a string representation of this configuration.
     *
     * @return A string representation of this configuration
     */
    @Override
    public String toString() {
        return name;
    }
}