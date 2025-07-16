package cz.cuni.mff.d3s.autodebugger.runner.orchestrator;

import cz.cuni.mff.d3s.autodebugger.analyzer.common.Analyzer;
import cz.cuni.mff.d3s.autodebugger.analyzer.java.JavaAnalyzer;
import cz.cuni.mff.d3s.autodebugger.model.common.RunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.common.TargetLanguage;
import cz.cuni.mff.d3s.autodebugger.runner.args.Arguments;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JavaOrchestratorTest {
    
    @TempDir
    Path tempDir;
    
    private JavaOrchestrator orchestrator;
    private Arguments testArguments;
    
    @BeforeEach
    void setUp() throws Exception {
        orchestrator = new JavaOrchestrator();
        
        // Create test JAR file
        Path testJar = tempDir.resolve("test.jar");
        Files.createFile(testJar);
        
        // Create test source directory
        Path sourceDir = tempDir.resolve("src");
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
    void testGetSupportedLanguage() {
        assertEquals(TargetLanguage.JAVA, orchestrator.getSupportedLanguage());
    }
    
    @Test
    void testGetAvailableTestGenerationTechniques() {
        String[] techniques = orchestrator.getAvailableTestGenerationTechniques();
        
        assertNotNull(techniques);
        assertTrue(techniques.length > 0);
        
        // Should contain at least the basic trace-based technique
        boolean hasTraceBasic = false;
        for (String technique : techniques) {
            if ("trace-based-basic".equals(technique)) {
                hasTraceBasic = true;
                break;
            }
        }
        assertTrue(hasTraceBasic, "Should contain trace-based-basic technique");
    }
    
    @Test
    void testGetDefaultTestGenerationTechnique() {
        String defaultTechnique = orchestrator.getDefaultTestGenerationTechnique();
        
        assertNotNull(defaultTechnique);
        assertEquals("trace-based-basic", defaultTechnique);
    }
    
    @Test
    void testCreateRunConfiguration() {
        RunConfiguration config = orchestrator.createRunConfiguration(testArguments);
        
        assertNotNull(config);
        assertEquals(TargetLanguage.JAVA.getIdentifier(), config.getLanguage());
        assertEquals(testArguments.applicationJarPath, config.getApplicationPath().toString());
        assertEquals(testArguments.sourceCodePath, config.getSourceCodePath().toString());
        assertNotNull(config.getTargetMethod());
        assertEquals("add", config.getTargetMethod().getName());
        assertNotNull(config.getExportableValues());
        assertFalse(config.getExportableValues().isEmpty());
    }
    
    @Test
    void testCreateAnalyzer() {
        RunConfiguration config = orchestrator.createRunConfiguration(testArguments);
        Analyzer analyzer = orchestrator.createAnalyzer(config);
        
        assertNotNull(analyzer);
        assertInstanceOf(JavaAnalyzer.class, analyzer);
    }
    
    @Test
    void testCreateTestGeneratorWithUnsupportedTechnique() {
        RunConfiguration config = orchestrator.createRunConfiguration(testArguments);

        assertThrows(RuntimeException.class, () -> {
            orchestrator.createTestGenerator(config, "unsupported-technique");
        });
    }

    @Test
    void testBuildInstrumentationModel() {
        RunConfiguration config = orchestrator.createRunConfiguration(testArguments);
        
        // This should work without throwing exceptions
        assertDoesNotThrow(() -> {
            orchestrator.buildInstrumentationModel(config);
        });
    }
}
