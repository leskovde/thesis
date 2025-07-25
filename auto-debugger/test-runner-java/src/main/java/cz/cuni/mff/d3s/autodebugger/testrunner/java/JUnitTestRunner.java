package cz.cuni.mff.d3s.autodebugger.testrunner.java;

import cz.cuni.mff.d3s.autodebugger.model.common.RunConfiguration;
import cz.cuni.mff.d3s.autodebugger.testrunner.common.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * JUnit-based test runner implementation for executing generated Java tests.
 * Supports JUnit 5 test execution with instrumentation and trace collection.
 */
@Slf4j
public class JUnitTestRunner implements TestRunner {
    
    private TestRunnerConfiguration configuration;
    private final TestCompiler testCompiler;
    private final InstrumentationManager instrumentationManager;
    
    public JUnitTestRunner() {
        this.testCompiler = new TestCompiler();
        this.instrumentationManager = new InstrumentationManager();
    }
    
    @Override
    public void configure(RunConfiguration configuration) {
        // Convert RunConfiguration to TestRunnerConfiguration
        // This is a temporary adapter until we fully migrate to the new API
        this.configuration = TestRunnerConfiguration.builder()
                .workingDirectory(configuration.getOutputDirectory())
                .build();
        log.info("Configured test runner with working directory: {}", configuration.getOutputDirectory());
    }

    public void configure(TestRunnerConfiguration config) {
        this.configuration = config;
        log.info("Configured test runner with working directory: {}", config.getWorkingDirectory());
    }
    
    @Override
    public TestExecutionResult executeTests(List<Path> testFiles) {
        log.info("Executing {} test files", testFiles.size());
        
        if (configuration == null) {
            throw new IllegalStateException("TestRunner must be configured before executing tests");
        }
        
        LocalDateTime startTime = LocalDateTime.now();
        List<TestResult> testResults = new ArrayList<>();
        List<ExecutionTrace> executionTraces = new ArrayList<>();
        
        try {
            // Compile test files
            List<Path> compiledClasses = compileTestFiles(testFiles);
            
            // Set up instrumentation if enabled
            if (configuration.isEnableInstrumentation()) {
                instrumentationManager.setupInstrumentation(configuration);
            }
            
            // Execute tests
            for (Path testFile : testFiles) {
                TestExecutionResult singleResult = executeTest(testFile);
                testResults.addAll(singleResult.getTestResults());
                executionTraces.addAll(singleResult.getExecutionTraces());
            }
            
            LocalDateTime endTime = LocalDateTime.now();
            Duration totalTime = Duration.between(startTime, endTime);
            
            // Calculate statistics
            int passed = (int) testResults.stream().mapToLong(r -> r.isPassed() ? 1 : 0).sum();
            int failed = (int) testResults.stream().mapToLong(r -> r.isFailed() ? 1 : 0).sum();
            int skipped = (int) testResults.stream().mapToLong(r -> r.isSkipped() ? 1 : 0).sum();
            
            TestSuiteStatus overallStatus = failed > 0 ? TestSuiteStatus.FAILED : TestSuiteStatus.PASSED;
            
            return TestExecutionResult.builder()
                    .overallStatus(overallStatus)
                    .testResults(testResults)
                    .executionTraces(executionTraces)
                    .totalExecutionTime(totalTime)
                    .executionStartTime(startTime)
                    .executionEndTime(endTime)
                    .passedCount(passed)
                    .failedCount(failed)
                    .skippedCount(skipped)
                    .rawOutput("Test execution completed")
                    .build();
                    
        } catch (Exception e) {
            log.error("Failed to execute tests", e);
            LocalDateTime endTime = LocalDateTime.now();
            Duration totalTime = Duration.between(startTime, endTime);
            
            return TestExecutionResult.builder()
                    .overallStatus(TestSuiteStatus.ERROR)
                    .testResults(testResults)
                    .executionTraces(executionTraces)
                    .totalExecutionTime(totalTime)
                    .executionStartTime(startTime)
                    .executionEndTime(endTime)
                    .passedCount(0)
                    .failedCount(0)
                    .skippedCount(0)
                    .rawOutput("Error: " + e.getMessage())
                    .build();
        }
    }
    
    @Override
    public TestExecutionResult executeTest(Path testFile) {
        log.info("Executing single test file: {}", testFile);
        
        LocalDateTime startTime = LocalDateTime.now();
        List<TestResult> testResults = new ArrayList<>();
        List<ExecutionTrace> executionTraces = new ArrayList<>();
        
        try {
            // Compile the test file
            Path compiledClass = testCompiler.compileTest(testFile, configuration);
            
            // Create custom classloader with test classpath
            URLClassLoader testClassLoader = createTestClassLoader(compiledClass);
            
            // Execute the test using JUnit Platform
            AutoDebuggerTestExecutionListener listener = new AutoDebuggerTestExecutionListener();
            
            Launcher launcher = LauncherFactory.create();
            LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                    .selectors(DiscoverySelectors.selectClass(getTestClassName(testFile)))
                    .build();
                    
            launcher.execute(request, listener);
            
            testResults.addAll(listener.getTestResults());
            
            // Collect execution traces if instrumentation is enabled
            if (configuration.isEnableInstrumentation() && configuration.isCaptureExecutionTraces()) {
                executionTraces.addAll(collectExecutionTraces(testFile, listener));
            }
            
            LocalDateTime endTime = LocalDateTime.now();
            Duration totalTime = Duration.between(startTime, endTime);
            
            int passed = (int) testResults.stream().mapToLong(r -> r.isPassed() ? 1 : 0).sum();
            int failed = (int) testResults.stream().mapToLong(r -> r.isFailed() ? 1 : 0).sum();
            int skipped = (int) testResults.stream().mapToLong(r -> r.isSkipped() ? 1 : 0).sum();
            
            TestSuiteStatus overallStatus = failed > 0 ? TestSuiteStatus.FAILED : TestSuiteStatus.PASSED;
            
            return TestExecutionResult.builder()
                    .overallStatus(overallStatus)
                    .testResults(testResults)
                    .executionTraces(executionTraces)
                    .totalExecutionTime(totalTime)
                    .executionStartTime(startTime)
                    .executionEndTime(endTime)
                    .passedCount(passed)
                    .failedCount(failed)
                    .skippedCount(skipped)
                    .rawOutput("Single test execution completed")
                    .build();
                    
        } catch (Exception e) {
            log.error("Failed to execute test: {}", testFile, e);
            LocalDateTime endTime = LocalDateTime.now();
            Duration totalTime = Duration.between(startTime, endTime);
            
            return TestExecutionResult.builder()
                    .overallStatus(TestSuiteStatus.ERROR)
                    .totalExecutionTime(totalTime)
                    .executionStartTime(startTime)
                    .executionEndTime(endTime)
                    .passedCount(0)
                    .failedCount(0)
                    .skippedCount(0)
                    .rawOutput("Error: " + e.getMessage())
                    .build();
        }
    }
    
    private List<Path> compileTestFiles(List<Path> testFiles) {
        List<Path> compiledClasses = new ArrayList<>();
        for (Path testFile : testFiles) {
            Path compiledClass = testCompiler.compileTest(testFile, configuration);
            compiledClasses.add(compiledClass);
        }
        return compiledClasses;
    }
    
    private URLClassLoader createTestClassLoader(Path compiledClass) throws Exception {
        List<URL> urls = new ArrayList<>();
        
        // Add compiled test class directory
        urls.add(compiledClass.getParent().toUri().toURL());
        
        // Add classpath entries from configuration
        for (Path classpathEntry : configuration.getClasspathEntries()) {
            urls.add(classpathEntry.toUri().toURL());
        }
        
        return new URLClassLoader(urls.toArray(new URL[0]), getClass().getClassLoader());
    }
    
    private String getTestClassName(Path testFile) {
        // Extract class name from file path
        String fileName = testFile.getFileName().toString();
        return fileName.substring(0, fileName.lastIndexOf('.'));
    }
    
    private List<ExecutionTrace> collectExecutionTraces(Path testFile, AutoDebuggerTestExecutionListener listener) {
        // Implementation would collect traces from instrumentation
        // This is a placeholder for the actual trace collection logic
        log.info("Collecting execution traces for test: {}", testFile);
        return new ArrayList<>();
    }
}
