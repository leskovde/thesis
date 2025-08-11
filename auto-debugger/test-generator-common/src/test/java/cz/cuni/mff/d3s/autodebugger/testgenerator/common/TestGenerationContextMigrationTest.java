package cz.cuni.mff.d3s.autodebugger.testgenerator.common;

import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.MethodIdentifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class demonstrating the migration from string-based to structured identifier-based
 * TestGenerationContext construction, and verifying backward compatibility.
 */
class TestGenerationContextMigrationTest {

    @TempDir
    Path tempDir;

    @Test
    void testBackwardCompatibility_StringFieldsStillWork() {
        // Given - old approach using deprecated string fields
        TestGenerationContext context = TestGenerationContext.builder()
                .targetMethodSignature("com.example.Calculator.add(int, int)")
                .targetClassName("com.example.Calculator")
                .packageName("com.example")
                .outputDirectory(tempDir)
                .build();

        // When - accessing through getter methods
        String methodSignature = context.getTargetMethodSignature();
        String className = context.getTargetClassName();
        String packageName = context.getPackageName();

        // Then - values should be accessible
        assertEquals("com.example.Calculator.add(int, int)", methodSignature);
        assertEquals("com.example.Calculator", className);
        assertEquals("com.example", packageName);
        assertEquals(tempDir, context.getOutputDirectory());
    }

    @Test
    void testNewApproach_StructuredIdentifiers() {
        // Given - new approach using structured identifiers
        MethodIdentifier methodIdentifier = new MethodIdentifier("add", "int", 
                List.of("int", "int")) {
            @Override
            public String toString() {
                return "com.example.Calculator.add(int, int)";
            }
        };

        TestGenerationContext context = TestGenerationContext.builder()
                .targetMethod(methodIdentifier)
                .outputDirectory(tempDir)
                .build();

        // When - accessing through getter methods
        String methodSignature = context.getTargetMethodSignature();
        MethodIdentifier retrievedMethod = context.getTargetMethod();

        // Then - structured identifier should be preferred
        assertEquals("com.example.Calculator.add(int, int)", methodSignature);
        assertSame(methodIdentifier, retrievedMethod);
        assertEquals(tempDir, context.getOutputDirectory());
    }

    @Test
    void testMigrationCompatibility_BothApproachesProduceSameResults() {
        // Given - same information using both approaches
        MethodIdentifier methodIdentifier = new MethodIdentifier("add", "int", 
                List.of("int", "int")) {
            @Override
            public String toString() {
                return "com.example.Calculator.add(int, int)";
            }
        };

        TestGenerationContext oldApproach = TestGenerationContext.builder()
                .targetMethodSignature("com.example.Calculator.add(int, int)")
                .targetClassName("com.example.Calculator")
                .packageName("com.example")
                .outputDirectory(tempDir)
                .build();

        TestGenerationContext newApproach = TestGenerationContext.builder()
                .targetMethod(methodIdentifier)
                .outputDirectory(tempDir)
                .build();

        // When - accessing the same information
        String oldSignature = oldApproach.getTargetMethodSignature();
        String newSignature = newApproach.getTargetMethodSignature();

        // Then - results should be equivalent
        assertEquals(oldSignature, newSignature);
        assertEquals(oldApproach.getOutputDirectory(), newApproach.getOutputDirectory());
    }

    @Test
    void testStructuredIdentifierPreference() {
        // Given - context with both structured identifier and deprecated string fields
        MethodIdentifier methodIdentifier = new MethodIdentifier("multiply", "double", 
                List.of("double", "double")) {
            @Override
            public String toString() {
                return "com.example.Calculator.multiply(double, double)";
            }
        };

        TestGenerationContext context = TestGenerationContext.builder()
                .targetMethod(methodIdentifier)
                // Also set deprecated fields (should be ignored in favor of structured identifier)
                .targetMethodSignature("com.example.Calculator.add(int, int)")
                .targetClassName("com.example.Calculator")
                .packageName("com.example")
                .outputDirectory(tempDir)
                .build();

        // When - accessing method signature
        String signature = context.getTargetMethodSignature();

        // Then - structured identifier should take precedence
        assertEquals("com.example.Calculator.multiply(double, double)", signature);
        assertNotEquals("com.example.Calculator.add(int, int)", signature);
    }

    @Test
    void testFallbackToDeprecatedFields() {
        // Given - context with only deprecated string fields (no structured identifiers)
        TestGenerationContext context = TestGenerationContext.builder()
                .targetMethodSignature("com.example.Calculator.subtract(int, int)")
                .targetClassName("com.example.Calculator")
                .packageName("com.example")
                .outputDirectory(tempDir)
                .build();

        // When - accessing information
        String signature = context.getTargetMethodSignature();
        String className = context.getTargetClassName();
        String packageName = context.getPackageName();
        MethodIdentifier method = context.getTargetMethod();

        // Then - should fall back to deprecated fields
        assertEquals("com.example.Calculator.subtract(int, int)", signature);
        assertEquals("com.example.Calculator", className);
        assertEquals("com.example", packageName);
        assertNull(method); // No structured identifier was provided
    }

    @Test
    void testBuilderWithNewFields() {
        // Given - using builder with new structured fields
        MethodIdentifier methodIdentifier = new MethodIdentifier("divide", "double", 
                List.of("double", "double")) {
            @Override
            public String toString() {
                return "com.example.Calculator.divide(double, double)";
            }
        };

        // When - building context with new fields
        TestGenerationContext context = TestGenerationContext.builder()
                .targetMethod(methodIdentifier)
                .outputDirectory(tempDir)
                .testFramework("junit5")
                .maxTestCount(25)
                .generateEdgeCases(true)
                .build();

        // Then - all fields should be accessible
        assertNotNull(context.getTargetMethod());
        assertEquals(methodIdentifier, context.getTargetMethod());
        assertEquals("com.example.Calculator.divide(double, double)", context.getTargetMethodSignature());
        assertEquals("junit5", context.getTestFramework());
        assertEquals(25, context.getMaxTestCount());
        assertTrue(context.isGenerateEdgeCases());
    }

    @Test
    void testDeprecationWarnings() {
        // This test documents that deprecated fields should generate warnings
        // In a real scenario, these would show compiler warnings
        
        // Given - using deprecated fields (should generate warnings in IDE/compiler)
        @SuppressWarnings("deprecation") // Suppressing for test purposes
        TestGenerationContext context = TestGenerationContext.builder()
                .targetMethodSignature("com.example.Calculator.power(double, double)")
                .targetClassName("com.example.Calculator")
                .packageName("com.example")
                .outputDirectory(tempDir)
                .build();

        // When - accessing deprecated fields directly (not recommended)
        // These field accesses would normally generate deprecation warnings
        
        // Then - values should still be accessible for backward compatibility
        assertNotNull(context);
        assertEquals("com.example.Calculator.power(double, double)", context.getTargetMethodSignature());
    }
}
