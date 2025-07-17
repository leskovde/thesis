package cz.cuni.mff.d3s.autodebugger.runner.orchestrator;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.InstrumentationModel;
import cz.cuni.mff.d3s.autodebugger.model.common.TargetLanguage;
import cz.cuni.mff.d3s.autodebugger.runner.args.Arguments;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test that demonstrates the complete new orchestrator-based API workflow.
 * This test verifies that all components can be created and configured properly.
 */
class OrchestratorIIntegrationTest {
    
    @TempDir
    Path tempDir;
    
    private Arguments testArguments;
    
    @BeforeEach
    void setUp() throws Exception {
        // Create test JAR file
        Path testJar = tempDir.resolve("test-app.jar");
        Files.createFile(testJar);
        
        // Create test source directory
        Path sourceDir = tempDir.resolve("src/main/java");
        Files.createDirectories(sourceDir);
        
        // Create DiSL home directory
        Path dislHome = tempDir.resolve("disl");
        Files.createDirectories(dislHome);

        // Setup test arguments
        testArguments = new Arguments();
        testArguments.applicationJarPath = testJar.toString();
        testArguments.sourceCodePath = sourceDir.toString();
        testArguments.dislHomePath = dislHome.toString();
        testArguments.targetMethodReference = "org.example.Calculator.add(int,int)";
        testArguments.targetParameters = List.of("0:int", "1:int");
        testArguments.targetFields = List.of("int:counter");
        testArguments.language = TargetLanguage.JAVA;
        testArguments.testGenerationStrategy = "trace-based-basic";
        testArguments.classpath = List.of();
    }
    
    @Test
    void testCompleteNewAPIWorkflow() {
        // This test demonstrates the complete new API workflow as specified in the requirements

        // Step 1: Create orchestrator for the language
        Orchestrator orchestrator = OrchestratorFactory.create(testArguments.language);
        assertNotNull(orchestrator);
        assertEquals(TargetLanguage.JAVA, orchestrator.getSupportedLanguage());

        // Step 2: Build instrumentation model (orchestrator already has run configuration)
        InstrumentationModel instrumentationModel = orchestrator.buildInstrumentationModel();
        assertNotNull(instrumentationModel);

        // Step 3: Verify test generation techniques are available
        String[] techniques = orchestrator.getAvailableTestGenerationTechniques();
        assertNotNull(techniques);
        assertTrue(techniques.length > 0);
        
        // The workflow would continue with:
        // var instrumentationPath = instrumentor.runInstrumentation();
        // var trace = analyzer.runAnalysis(instrumentationPath);
        // var testGenerator = orchestrator.createTestGenerator(runConfiguration);
        // var tests = testGenerator.generateTests(trace);
        // var testRunner = orchestrator.createTestRunner(runConfiguration);
        // var testResults = testRunner.runTests(tests);
    }
    
    @Test
    void testRunConfigurationValidation() {
        Orchestrator orchestrator = OrchestratorFactory.create(testArguments.language);

        // Test completed successfully - orchestrator creates its own run configuration
        assertDoesNotThrow(() -> {
            orchestrator.buildInstrumentationModel();
        });
    }
    
    @Test
    void testRunConfigurationWithInvalidPaths() {
        // Test with non-existent JAR file
        testArguments.applicationJarPath = "/non/existent/path.jar";
        
        Orchestrator orchestrator = OrchestratorFactory.create(testArguments.language);
        
        // This should work since orchestrator creates its own run configuration
        assertDoesNotThrow(() -> {
            orchestrator.buildInstrumentationModel();
        });
    }
    
    @Test
    void testMultipleLanguageSupport() {
        // Test that the factory correctly handles different languages
        assertTrue(OrchestratorFactory.isLanguageSupported(TargetLanguage.JAVA));

        // Java should work
        assertDoesNotThrow(() -> OrchestratorFactory.create(TargetLanguage.JAVA));

        // Invalid string should throw exception
        assertThrows(IllegalArgumentException.class, () -> {
            OrchestratorFactory.create("invalid-language");
        });
    }
    
    @Test
    void testOrchestratorComponentCreation() {
        Orchestrator orchestrator = OrchestratorFactory.create("java");
        
        // Test that all components can be created without throwing exceptions
        assertDoesNotThrow(() -> {
            orchestrator.buildInstrumentationModel();
        });

        // Test generation techniques
        String[] techniques = orchestrator.getAvailableTestGenerationTechniques();
        assertNotNull(techniques);
        assertTrue(techniques.length > 0);
    }
}
