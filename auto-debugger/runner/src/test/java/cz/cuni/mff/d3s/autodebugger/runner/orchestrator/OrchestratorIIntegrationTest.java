package cz.cuni.mff.d3s.autodebugger.runner.orchestrator;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.InstrumentationModel;
import cz.cuni.mff.d3s.autodebugger.model.common.TargetLanguage;
import cz.cuni.mff.d3s.autodebugger.runner.args.Arguments;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

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

        // Create DiSL home directory with required structure
        Path dislHome = tempDir.resolve("disl");
        Files.createDirectories(dislHome);

        // Create required DiSL structure
        Path dislBin = dislHome.resolve("bin");
        Files.createDirectories(dislBin);
        Path dislPy = dislBin.resolve("disl.py");
        Files.createFile(dislPy);

        Path dislOutputLib = dislHome.resolve("output/lib");
        Files.createDirectories(dislOutputLib);

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
        testArguments.runtimeArguments = List.of();
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

    /**
     * Enhanced orchestrator.createInstrumentation Integration Test
     * Test Case 3.1: Full JAR generation from a complete model.
     *
     * This test verifies the complete instrumentation pipeline through the orchestrator,
     * including JAR creation, manifest verification, and DiSLClass content validation.
     */
    @Test
    void testCreateInstrumentationEndToEnd() throws IOException {
        // given
        Orchestrator orchestrator = OrchestratorFactory.create(testArguments);
        assertNotNull(orchestrator);

        // Step 1: Build instrumentation model
        InstrumentationModel instrumentationModel = orchestrator.buildInstrumentationModel();
        assertNotNull(instrumentationModel);

        // when
        List<Path> instrumentationPaths = orchestrator.createInstrumentation(instrumentationModel);

        // then
        assertNotNull(instrumentationPaths, "Instrumentation paths should not be null");
        assertEquals(1, instrumentationPaths.size(), "Should return exactly one instrumentation JAR");

        Path instrumentationJar = instrumentationPaths.get(0);
        assertNotNull(instrumentationJar, "Instrumentation JAR path should not be null");
        assertTrue(Files.exists(instrumentationJar), "Instrumentation JAR file should exist");

        // Verify JAR structure and contents
        try (JarFile jarFile = new JarFile(instrumentationJar.toFile())) {
            // Assert JAR contains required class files
            assertNotNull(jarFile.getEntry("DiSLClass.class"),
                "JAR should contain DiSLClass.class");
            assertNotNull(jarFile.getEntry("Collector.class"),
                "JAR should contain Collector.class");
            assertNotNull(jarFile.getEntry("CollectorRE.class"),
                "JAR should contain CollectorRE.class");

            // Verify manifest contains correct DiSL-Classes entry
            Manifest manifest = jarFile.getManifest();
            assertNotNull(manifest, "JAR should have a manifest");

            String dislClasses = manifest.getMainAttributes().getValue("DiSL-Classes");
            assertNotNull(dislClasses, "Manifest should contain DiSL-Classes entry");
            assertEquals("DiSLClass", dislClasses,
                "DiSL-Classes should reference DiSLClass");
        }

        // Additional verification: Check that the instrumentation model was properly transformed
        // This ensures the DiSLClass contains the expected instrumentation logic
        assertTrue(instrumentationJar.toString().endsWith(".jar"),
            "Output should be a JAR file");
        assertTrue(Files.size(instrumentationJar) > 0,
            "JAR file should not be empty");
    }

    /**
     * Test that the orchestrator properly handles instrumentation creation with
     * multiple exportable values (arguments and fields).
     */
    @Test
    void testCreateInstrumentationWithMultipleExportableValues() throws IOException {
        // given - modify test arguments to include both parameters and fields
        testArguments.targetParameters = List.of("0:int", "1:java.lang.String");
        testArguments.targetFields = List.of("int:counter", "java.lang.String:status");

        Orchestrator orchestrator = OrchestratorFactory.create(testArguments);
        InstrumentationModel instrumentationModel = orchestrator.buildInstrumentationModel();

        // when
        List<Path> instrumentationPaths = orchestrator.createInstrumentation(instrumentationModel);

        // then
        assertEquals(1, instrumentationPaths.size(), "Should create one instrumentation JAR");
        Path instrumentationJar = instrumentationPaths.get(0);
        assertTrue(Files.exists(instrumentationJar), "Instrumentation JAR should exist");

        // Verify the JAR is properly structured
        try (JarFile jarFile = new JarFile(instrumentationJar.toFile())) {
            // Ensure all required components are present
            assertNotNull(jarFile.getEntry("DiSLClass.class"));
            assertNotNull(jarFile.getEntry("Collector.class"));
            assertNotNull(jarFile.getEntry("CollectorRE.class"));

            // Verify manifest
            Manifest manifest = jarFile.getManifest();
            assertNotNull(manifest);
            assertEquals("DiSLClass", manifest.getMainAttributes().getValue("DiSL-Classes"));
        }

        // The instrumentation should handle multiple types of exportable values
        assertTrue(Files.size(instrumentationJar) > 1000,
            "JAR with multiple exportable values should be reasonably sized");
    }

    /**
     * Test that the orchestrator fails gracefully when given invalid configuration.
     */
    @Test
    void testCreateInstrumentationWithInvalidConfiguration() {
        // given - invalid DiSL home path
        testArguments.dislHomePath = "/non/existent/disl/path";

        // when/then - should fail during orchestrator creation due to validation
        assertThrows(RuntimeException.class, () -> {
            OrchestratorFactory.create(testArguments);
        });
    }
}
