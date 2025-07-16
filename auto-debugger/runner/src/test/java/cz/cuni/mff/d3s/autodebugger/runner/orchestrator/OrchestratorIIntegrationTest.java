package cz.cuni.mff.d3s.autodebugger.runner.orchestrator;

import cz.cuni.mff.d3s.autodebugger.analyzer.common.Analyzer;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.Instrumentor;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Model;
import cz.cuni.mff.d3s.autodebugger.model.common.RunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.common.TargetLanguage;
import cz.cuni.mff.d3s.autodebugger.runner.args.Arguments;
import cz.cuni.mff.d3s.autodebugger.runner.model.ModelBuilder;
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
        
        // Setup test arguments
        testArguments = new Arguments();
        testArguments.applicationJarPath = testJar.toString();
        testArguments.sourceCodePath = sourceDir.toString();
        testArguments.targetMethodReference = "org.example.Calculator.add(int,int)";
        testArguments.targetParameters = List.of("0:int", "1:int");
        testArguments.targetFields = List.of("int:counter");
        testArguments.language = TargetLanguage.JAVA;
    }
    
    @Test
    void testCompleteNewAPIWorkflow() {
        // This test demonstrates the complete new API workflow as specified in the requirements
        
        // Step 1: Create orchestrator for the language
        Orchestrator orchestrator = OrchestratorFactory.create(testArguments.language);
        assertNotNull(orchestrator);
        assertEquals(TargetLanguage.JAVA, orchestrator.getSupportedLanguage());
        
        // Step 2: Create run configuration from arguments
        RunConfiguration runConfiguration = orchestrator.createRunConfiguration(testArguments);
        assertNotNull(runConfiguration);
        assertEquals(TargetLanguage.JAVA.getIdentifier(), runConfiguration.getLanguage());
        assertEquals(testArguments.applicationJarPath, runConfiguration.getApplicationPath().toString());
        assertEquals(testArguments.sourceCodePath, runConfiguration.getSourceCodePath().toString());
        assertNotNull(runConfiguration.getTargetMethod());
        assertEquals("add", runConfiguration.getTargetMethod().getName());
        assertNotNull(runConfiguration.getExportableValues());
        assertFalse(runConfiguration.getExportableValues().isEmpty());
        
        // Step 3: Build instrumentation model
        Model instrumentationModel = orchestrator.buildInstrumentationModel(runConfiguration);
        assertNotNull(instrumentationModel);
        
        // Step 4: Create instrumentor (using fallback to ModelBuilder for now)
        // TODO: Complete orchestrator.createInstrumentor implementation
        Instrumentor instrumentor = ModelBuilder.buildInstrumentor(testArguments);
        assertNotNull(instrumentor);
        
        // Step 5: Create analyzer
        Analyzer analyzer = orchestrator.createAnalyzer(runConfiguration);
        assertNotNull(analyzer);

        // Step 6: Verify test generation techniques are available
        String[] techniques = orchestrator.getAvailableTestGenerationTechniques();
        assertNotNull(techniques);
        assertTrue(techniques.length > 0);
        
        String defaultTechnique = orchestrator.getDefaultTestGenerationTechnique();
        assertNotNull(defaultTechnique);
        assertEquals("trace-based-basic", defaultTechnique);
        
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
        RunConfiguration runConfiguration = orchestrator.createRunConfiguration(testArguments);
        
        // Validation should pass for valid configuration
        assertDoesNotThrow(runConfiguration::validate);
    }
    
    @Test
    void testRunConfigurationWithInvalidPaths() {
        // Test with non-existent JAR file
        testArguments.applicationJarPath = "/non/existent/path.jar";
        
        Orchestrator orchestrator = OrchestratorFactory.create(testArguments.language);
        
        assertThrows(RuntimeException.class, () -> {
            orchestrator.createRunConfiguration(testArguments);
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
        RunConfiguration runConfiguration = orchestrator.createRunConfiguration(testArguments);
        
        // Test that all components can be created without throwing exceptions
        assertDoesNotThrow(() -> {
            orchestrator.buildInstrumentationModel(runConfiguration);
        });
        
        assertDoesNotThrow(() -> {
            orchestrator.createAnalyzer(runConfiguration);
        });
        
        // Test generation techniques
        String[] techniques = orchestrator.getAvailableTestGenerationTechniques();
        assertNotNull(techniques);
        assertTrue(techniques.length > 0);
        
        // Verify default technique is available
        String defaultTechnique = orchestrator.getDefaultTestGenerationTechnique();
        boolean defaultFound = false;
        for (String technique : techniques) {
            if (technique.equals(defaultTechnique)) {
                defaultFound = true;
                break;
            }
        }
        assertTrue(defaultFound, "Default technique should be in available techniques list");
    }
}
