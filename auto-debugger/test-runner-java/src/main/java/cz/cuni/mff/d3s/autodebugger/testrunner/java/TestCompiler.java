package cz.cuni.mff.d3s.autodebugger.testrunner.java;

import cz.cuni.mff.d3s.autodebugger.testrunner.common.TestRunnerConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jdt.core.compiler.batch.BatchCompiler;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Compiles Java test files using the Eclipse JDT compiler.
 * Handles classpath setup and compilation of generated test sources.
 */
@Slf4j
public class TestCompiler {
    
    /**
     * Compiles a single test file and returns the path to the compiled class.
     * 
     * @param testFile Path to the Java source file to compile
     * @param configuration Test runner configuration containing classpath and other settings
     * @return Path to the compiled .class file
     */
    public Path compileTest(Path testFile, TestRunnerConfiguration configuration) {
        log.info("Compiling test file: {}", testFile);
        
        try {
            // Create output directory for compiled classes
            Path outputDir = createOutputDirectory(testFile);
            
            // Build compilation arguments
            List<String> compilerArgs = buildCompilerArguments(testFile, outputDir, configuration);
            
            // Compile using Eclipse JDT compiler
            ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
            PrintWriter errorWriter = new PrintWriter(errorStream);
            
            boolean success = BatchCompiler.compile(
                compilerArgs.toArray(new String[0]),
                new PrintWriter(System.out),
                errorWriter,
                null
            );
            
            errorWriter.close();
            
            if (!success) {
                String errors = errorStream.toString();
                log.error("Compilation failed for {}: {}", testFile, errors);
                throw new RuntimeException("Compilation failed: " + errors);
            }
            
            // Return path to compiled class file
            String className = getClassNameFromFile(testFile);
            Path compiledClass = outputDir.resolve(className + ".class");
            
            if (!Files.exists(compiledClass)) {
                throw new RuntimeException("Compiled class file not found: " + compiledClass);
            }
            
            log.info("Successfully compiled test to: {}", compiledClass);
            return compiledClass;
            
        } catch (Exception e) {
            log.error("Failed to compile test file: {}", testFile, e);
            throw new RuntimeException("Test compilation failed", e);
        }
    }
    
    /**
     * Compiles multiple test files in batch.
     * 
     * @param testFiles List of test files to compile
     * @param configuration Test runner configuration
     * @return List of paths to compiled class files
     */
    public List<Path> compileTests(List<Path> testFiles, TestRunnerConfiguration configuration) {
        log.info("Compiling {} test files", testFiles.size());
        
        List<Path> compiledClasses = new ArrayList<>();
        for (Path testFile : testFiles) {
            Path compiledClass = compileTest(testFile, configuration);
            compiledClasses.add(compiledClass);
        }
        
        return compiledClasses;
    }
    
    private Path createOutputDirectory(Path testFile) throws Exception {
        Path outputDir = testFile.getParent().resolve("compiled");
        Files.createDirectories(outputDir);
        return outputDir;
    }
    
    private List<String> buildCompilerArguments(Path testFile, Path outputDir, TestRunnerConfiguration configuration) {
        List<String> args = new ArrayList<>();
        
        // Source file
        args.add(testFile.toString());
        
        // Output directory
        args.add("-d");
        args.add(outputDir.toString());
        
        // Classpath
        String classpath = buildClasspath(configuration);
        if (!classpath.isEmpty()) {
            args.add("-cp");
            args.add(classpath);
        }
        
        // Java version compatibility
        args.add("-source");
        args.add("11");
        args.add("-target");
        args.add("11");
        
        // Enable all warnings
        args.add("-warn:+all");
        
        // Encoding
        args.add("-encoding");
        args.add("UTF-8");
        
        log.debug("Compiler arguments: {}", args);
        return args;
    }
    
    private String buildClasspath(TestRunnerConfiguration configuration) {
        List<String> classpathEntries = new ArrayList<>();
        
        // Add configuration classpath entries
        for (Path entry : configuration.getClasspathEntries()) {
            classpathEntries.add(entry.toString());
        }
        
        // Add JUnit dependencies (these should be in the classpath already)
        // This is a fallback in case they're not included
        String junitClasspath = System.getProperty("java.class.path");
        if (junitClasspath != null && !junitClasspath.isEmpty()) {
            classpathEntries.add(junitClasspath);
        }
        
        return classpathEntries.stream()
                .distinct()
                .collect(Collectors.joining(System.getProperty("path.separator")));
    }
    
    private String getClassNameFromFile(Path testFile) {
        String fileName = testFile.getFileName().toString();
        if (fileName.endsWith(".java")) {
            return fileName.substring(0, fileName.length() - 5);
        }
        return fileName;
    }
}
