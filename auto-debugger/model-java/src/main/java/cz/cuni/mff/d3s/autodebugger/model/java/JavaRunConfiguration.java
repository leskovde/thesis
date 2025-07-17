package cz.cuni.mff.d3s.autodebugger.model.java;

import cz.cuni.mff.d3s.autodebugger.model.common.RunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.common.TargetLanguage;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.JavaMethodIdentifier;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.JavaValueIdentifier;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Java-specific implementation of RunConfiguration.
 * Contains all the necessary information for instrumenting, analyzing, and generating tests
 * for Java applications.
 */
@Builder
@Getter
@Slf4j
public class JavaRunConfiguration implements RunConfiguration {

    private final Path applicationPath;
    private final Path sourceCodePath;
    private final JavaMethodIdentifier targetMethod;
    
    @Singular
    private final List<JavaValueIdentifier> exportableValues;
    
    @Singular("classpathEntry")
    private final List<Path> classpathEntries;

    @Singular("runtimeArgument")
    public final List<String> runtimeArguments;

    @Builder.Default
    private final Path outputDirectory = Path.of("auto-debugger-output");


    @Builder.Default
    private final TargetLanguage language = TargetLanguage.JAVA;

    @Builder.Default
    private final Path dislHomePath = Path.of("../../disl/");

    @Override
    public void validate() {
        log.debug("Validating Java run configuration");
        
        if (applicationPath == null) {
            throw new IllegalStateException("Application path cannot be null");
        }
        
        if (!Files.exists(applicationPath)) {
            throw new IllegalStateException("Application file does not exist: " + applicationPath);
        }
        
        if (sourceCodePath == null) {
            throw new IllegalStateException("Source code path cannot be null");
        }
        
        if (!Files.exists(sourceCodePath)) {
            throw new IllegalStateException("Source code directory does not exist: " + sourceCodePath);
        }
        
        if (targetMethod == null) {
            throw new IllegalStateException("Target method cannot be null");
        }
        
        if (exportableValues == null || exportableValues.isEmpty()) {
            log.warn("No exportable values specified - analysis may not capture any data");
        }
        
        // Validate that application is a JAR file for Java
        String fileName = applicationPath.getFileName().toString().toLowerCase();
        if (!fileName.endsWith(".jar")) {
            log.warn("Application file does not appear to be a JAR file: {}", fileName);
        }
        
        log.debug("Java run configuration validation completed successfully");
    }
    
    /**
     * Creates a JavaRunConfiguration from command line arguments.
     * 
     * @param applicationPath Path to the JAR file
     * @param sourceCodePath Path to source code
     * @param targetMethod Target method identifier
     * @param exportableValues List of values to export
     * @return Configured JavaRunConfiguration
     */
    public static JavaRunConfiguration fromArguments(
            Path applicationPath,
            Path sourceCodePath, 
            JavaMethodIdentifier targetMethod,
            List<JavaValueIdentifier> exportableValues) {
        
        return JavaRunConfiguration.builder()
                .applicationPath(applicationPath)
                .sourceCodePath(sourceCodePath)
                .targetMethod(targetMethod)
                .exportableValues(exportableValues)
                .build();
    }
    
    /**
     * Creates a JavaRunConfiguration with additional classpath entries.
     * 
     * @param applicationPath Path to the JAR file
     * @param sourceCodePath Path to source code
     * @param targetMethod Target method identifier
     * @param exportableValues List of values to export
     * @param classpathEntries Additional classpath entries
     * @return Configured JavaRunConfiguration
     */
    public static JavaRunConfiguration fromArgumentsWithClasspath(
            Path applicationPath,
            Path sourceCodePath,
            JavaMethodIdentifier targetMethod,
            List<JavaValueIdentifier> exportableValues,
            List<Path> classpathEntries) {
        
        JavaRunConfigurationBuilder builder = JavaRunConfiguration.builder()
                .applicationPath(applicationPath)
                .sourceCodePath(sourceCodePath)
                .targetMethod(targetMethod)
                .exportableValues(exportableValues);
        
        if (classpathEntries != null) {
            classpathEntries.forEach(builder::classpathEntry);
        }
        
        return builder.build();
    }
}
