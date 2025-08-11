package cz.cuni.mff.d3s.autodebugger.testgenerator.java.trace;

import cz.cuni.mff.d3s.autodebugger.model.common.trace.Trace;
import cz.cuni.mff.d3s.autodebugger.model.java.JavaRunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.*;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TestGenerationContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for NaiveTraceBasedGenerator RunConfiguration-based methods.
 * This test verifies that the generator can create tests directly from RunConfiguration
 * without requiring manual TestGenerationContext construction.
 */
class NaiveTraceBasedGeneratorRunConfigurationTest {

    @TempDir
    Path tempDir;

    private NaiveTraceBasedGenerator generator;
    private Map<Integer, JavaValueIdentifier> identifierMapping;
    private JavaRunConfiguration runConfiguration;
    private Trace trace;

    @BeforeEach
    void setUp() {
        // Create identifier mapping
        identifierMapping = new HashMap<>();
        
        JavaArgumentIdentifier arg1 = new JavaArgumentIdentifier(
            ArgumentIdentifierParameters.builder()
                .argumentSlot(0)
                .variableType("int")
                .build()
        );
        JavaArgumentIdentifier arg2 = new JavaArgumentIdentifier(
            ArgumentIdentifierParameters.builder()
                .argumentSlot(1)
                .variableType("int")
                .build()
        );

        identifierMapping.put(0, arg1);
        identifierMapping.put(1, arg2);
        
        generator = new NaiveTraceBasedGenerator(identifierMapping);

        // Create Java method identifier
        JavaPackageIdentifier packageIdentifier = new JavaPackageIdentifier("com.example.math");
        JavaClassIdentifier classIdentifier = new JavaClassIdentifier(
            ClassIdentifierParameters.builder()
                .packageIdentifier(packageIdentifier)
                .className("Calculator")
                .build()
        );
        
        JavaMethodIdentifier methodIdentifier = new JavaMethodIdentifier(
            MethodIdentifierParameters.builder()
                .ownerClassIdentifier(classIdentifier)
                .methodName("add")
                .returnType("int")
                .parameterTypes(Arrays.asList("int", "int"))
                .build()
        );

        // Create run configuration
        runConfiguration = JavaRunConfiguration.builder()
                .applicationPath(Path.of("test-app.jar"))
                .sourceCodePath(Path.of("src/main/java"))
                .targetMethod(methodIdentifier)
                .outputDirectory(tempDir)
                .build();

        // Create trace with test data
        trace = new Trace();
        trace.addIntValue(0, 5);
        trace.addIntValue(1, 10);
        trace.addIntValue(0, 3);
        trace.addIntValue(1, 7);
    }

    @Test
    void testGenerateTests_WithRunConfiguration() throws Exception {
        // When - generate tests using RunConfiguration directly
        List<Path> generatedFiles = generator.generateTests(trace, runConfiguration);

        // Then - verify test generation succeeded
        assertNotNull(generatedFiles);
        assertEquals(1, generatedFiles.size(), "Should generate exactly one test file");
        
        Path testFile = generatedFiles.get(0);
        assertTrue(Files.exists(testFile), "Generated test file should exist");
        
        String content = Files.readString(testFile);
        
        // Verify that the content uses information from JavaMethodIdentifier
        assertTrue(content.contains("com.example.math.Calculator.add(int, int)"), 
                  "Should use fully qualified signature from JavaMethodIdentifier");
        assertTrue(content.contains("com.example.math.Calculator"), 
                  "Should use fully qualified class name from JavaMethodIdentifier");
        assertTrue(content.contains("package com.example.math"), 
                  "Should use package name from JavaMethodIdentifier");
        
        // Verify test structure
        assertTrue(content.contains("@Test"), "Should contain test annotations");
        assertTrue(content.contains("void test"), "Should contain test methods");
        assertTrue(content.contains("assertNotNull"), "Should contain assertions");
    }

    @Test
    void testGenerateTests_RunConfigurationVsManualContext() throws Exception {
        // Given - create manual TestGenerationContext for comparison
        TestGenerationContext manualContext = TestGenerationContext.builder()
                .targetMethodSignature("com.example.math.Calculator.add(int, int)")
                .targetClassName("com.example.math.Calculator")
                .packageName("com.example.math")
                .outputDirectory(tempDir.resolve("manual"))
                .build();

        // When - generate tests both ways
        List<Path> configBasedFiles = generator.generateTests(trace, runConfiguration);
        List<Path> contextBasedFiles = generator.generateTests(trace, manualContext);

        // Then - both should succeed and produce similar results
        assertEquals(1, configBasedFiles.size());
        assertEquals(1, contextBasedFiles.size());
        
        String configBasedContent = Files.readString(configBasedFiles.get(0));
        String contextBasedContent = Files.readString(contextBasedFiles.get(0));
        
        // Both should contain the same key elements
        assertTrue(configBasedContent.contains("com.example.math.Calculator"));
        assertTrue(contextBasedContent.contains("com.example.math.Calculator"));
        
        assertTrue(configBasedContent.contains("package com.example.math"));
        assertTrue(contextBasedContent.contains("package com.example.math"));
        
        // Both should have similar test structure
        assertTrue(configBasedContent.contains("@Test"));
        assertTrue(contextBasedContent.contains("@Test"));
    }

    @Test
    void testGenerateTests_WithNullRunConfiguration() {
        // When/Then - should throw exception for null configuration
        assertThrows(IllegalArgumentException.class, () -> {
            generator.generateTests(trace, (JavaRunConfiguration) null);
        });
    }

    @Test
    void testGenerateTests_WithEmptyTrace() {
        // Given - empty trace
        Trace emptyTrace = new Trace();

        // When/Then - should throw exception for empty trace
        assertThrows(IllegalArgumentException.class, () -> {
            generator.generateTests(emptyTrace, runConfiguration);
        });
    }

    @Test
    void testGenerateTests_UsesJavaMethodIdentifierUtilityMethods() throws Exception {
        // When - generate tests using RunConfiguration
        List<Path> generatedFiles = generator.generateTests(trace, runConfiguration);

        // Then - verify that the utility methods from JavaMethodIdentifier are used
        String content = Files.readString(generatedFiles.get(0));
        
        // Verify fully qualified signature is used (from getFullyQualifiedSignature())
        assertTrue(content.contains("com.example.math.Calculator.add(int, int)"));
        
        // Verify fully qualified class name is used (from getFullyQualifiedClassName())
        assertTrue(content.contains("com.example.math.Calculator"));
        
        // Verify package name is used (from getPackageName())
        assertTrue(content.contains("package com.example.math"));
        
        // Verify the content is well-formed
        assertTrue(content.contains("import"), "Should contain import statements");
        assertTrue(content.contains("public class"), "Should contain class declaration");
        assertTrue(content.contains("@BeforeEach"), "Should contain setup method");
    }
}
