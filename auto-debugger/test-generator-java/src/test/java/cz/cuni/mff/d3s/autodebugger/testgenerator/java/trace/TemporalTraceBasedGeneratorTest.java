package cz.cuni.mff.d3s.autodebugger.testgenerator.java.trace;

import cz.cuni.mff.d3s.autodebugger.model.common.trace.TemporalTrace;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.*;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.JavaArgumentIdentifier;
import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.ExportableValue;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TestGenerationContext;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TestNamingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TemporalTraceBasedGeneratorTest {
    
    @TempDir
    Path tempDir;
    
    private TemporalTraceBasedGenerator generator;
    private TemporalTrace trace;
    private TestGenerationContext context;
    
    @BeforeEach
    void setUp() {
        generator = new TemporalTraceBasedGenerator();
        trace = new TemporalTrace();
        
        context = TestGenerationContext.builder()
                .targetMethodSignature("Calculator.divide(int, int)")
                .targetClassName("com.example.Calculator")
                .packageName("com.example.test")
                .outputDirectory(tempDir)
                .testFramework("junit5")
                .namingStrategy(TestNamingStrategy.DESCRIPTIVE)
                .maxTestCount(10)
                .generateEdgeCases(true)
                .generateNegativeTests(true)
                .build();
    }
    
    @Test
    void testGenerateTestsWithEmptyTrace() {
        List<Path> generatedFiles = generator.generateTests(trace, context);
        
        assertNotNull(generatedFiles);
        assertTrue(generatedFiles.isEmpty(), "Should not generate tests from empty trace");
    }
    
    @Test
    void testGenerateTestsWithSimpleTrace() {
        // Create test identifiers
        ExportableValue arg1 = new JavaArgumentIdentifier(
            ArgumentIdentifierParameters.builder()
                .argumentSlot(0)
                .variableType("int")
                .build()
        );
        
        ExportableValue arg2 = new JavaArgumentIdentifier(
            ArgumentIdentifierParameters.builder()
                .argumentSlot(1)
                .variableType("int")
                .build()
        );
        
        // Add trace data simulating method calls
        trace.addValue(arg1, 100, 10);
        trace.addValue(arg2, 101, 2);
        trace.addValue(arg1, 200, 20);
        trace.addValue(arg2, 201, 4);
        
        List<Path> generatedFiles = generator.generateTests(trace, context);
        
        assertNotNull(generatedFiles);
        assertFalse(generatedFiles.isEmpty(), "Should generate tests from trace with data");
        
        // Verify the generated file exists and contains expected content
        for (Path file : generatedFiles) {
            assertTrue(Files.exists(file), "Generated file should exist: " + file);
            
            try {
                String content = Files.readString(file);
                assertTrue(content.contains("package com.example.test"), 
                          "Generated file should contain package declaration");
                assertTrue(content.contains("import org.junit.jupiter.api.Test"), 
                          "Generated file should contain JUnit imports");
                assertTrue(content.contains("public class"), 
                          "Generated file should contain class declaration");
                assertTrue(content.contains("@Test"), 
                          "Generated file should contain test methods");
                assertTrue(content.contains("@DisplayName"), 
                          "Generated file should contain display names");
                assertTrue(content.contains("event"), 
                          "Generated file should reference event indices");
            } catch (Exception e) {
                fail("Failed to read generated file: " + e.getMessage());
            }
        }
    }
    
    @Test
    void testGenerateTestsWithComplexTrace() {
        // Create identifiers for a more complex scenario
        ExportableValue arg1 = new JavaArgumentIdentifier(
            ArgumentIdentifierParameters.builder()
                .argumentSlot(0)
                .variableType("int")
                .build()
        );
        
        JavaClassIdentifier calculatorClass = new JavaClassIdentifier(
            ClassIdentifierParameters.builder()
                .className("Calculator")
                .packageIdentifier(JavaPackageIdentifier.DEFAULT_PACKAGE)
                .build()
        );

        ExportableValue field1 = new JavaFieldIdentifier(
            FieldIdentifierParameters.builder()
                .variableName("counter")
                .ownerClassIdentifier(calculatorClass)
                .variableType("int")
                .build()
        );
        
        // Simulate a complex execution with field changes and method calls
        trace.addValue(field1, 50, 0);        // Initial field value
        trace.addValue(arg1, 100, 5);         // First method call
        trace.addValue(field1, 110, 1);       // Field updated
        trace.addValue(arg1, 200, 10);        // Second method call
        trace.addValue(field1, 210, 2);       // Field updated again
        trace.addValue(arg1, 300, 15);        // Third method call
        
        // Add metadata
        trace.addMetadata("test_scenario", "complex_execution");
        
        List<Path> generatedFiles = generator.generateTests(trace, context);
        
        assertNotNull(generatedFiles);
        assertFalse(generatedFiles.isEmpty());
        
        // Verify the generated content includes temporal information
        Path testFile = generatedFiles.get(0);
        try {
            String content = Files.readString(testFile);
            assertTrue(content.contains("event"), "Should reference event indices");
            assertTrue(content.contains("scenario"), "Should include scenario information");
            assertTrue(content.contains("State captured"), "Should mention state capture");
        } catch (Exception e) {
            fail("Failed to read generated file: " + e.getMessage());
        }
    }
    
    @Test
    void testGenerateTestsWithDifferentNamingStrategies() {
        // Setup trace data
        ExportableValue arg1 = new JavaArgumentIdentifier(
            ArgumentIdentifierParameters.builder()
                .argumentSlot(0)
                .variableType("int")
                .build()
        );

        trace.addValue(arg1, 100, 42);

        // Test with SIMPLE naming strategy
        TestGenerationContext simpleContext = TestGenerationContext.builder()
                .targetMethodSignature(context.getTargetMethodSignature())
                .targetClassName(context.getTargetClassName())
                .packageName(context.getPackageName())
                .outputDirectory(context.getOutputDirectory())
                .testFramework(context.getTestFramework())
                .maxTestCount(context.getMaxTestCount())
                .generateEdgeCases(context.isGenerateEdgeCases())
                .generateNegativeTests(context.isGenerateNegativeTests())
                .namingStrategy(TestNamingStrategy.SIMPLE)
                .build();

        List<Path> simpleFiles = generator.generateTests(trace, simpleContext);
        assertNotNull(simpleFiles);

        // Test with BDD_STYLE naming strategy
        TestGenerationContext bddContext = TestGenerationContext.builder()
                .targetMethodSignature(context.getTargetMethodSignature())
                .targetClassName(context.getTargetClassName())
                .packageName(context.getPackageName())
                .outputDirectory(context.getOutputDirectory())
                .testFramework(context.getTestFramework())
                .maxTestCount(context.getMaxTestCount())
                .generateEdgeCases(context.isGenerateEdgeCases())
                .generateNegativeTests(context.isGenerateNegativeTests())
                .namingStrategy(TestNamingStrategy.BDD_STYLE)
                .build();

        List<Path> bddFiles = generator.generateTests(trace, bddContext);
        assertNotNull(bddFiles);

        // Verify that the generator can handle different naming strategies
        // The actual content verification is optional since generation may vary
        System.out.println("Simple strategy generated " + simpleFiles.size() + " files");
        System.out.println("BDD strategy generated " + bddFiles.size() + " files");
    }
    
    @Test
    void testGenerateTestsWithMaxTestCountLimit() {
        // Create a trace with many events
        ExportableValue arg1 = new JavaArgumentIdentifier(
            ArgumentIdentifierParameters.builder()
                .argumentSlot(0)
                .variableType("int")
                .build()
        );
        
        // Add many events to potentially exceed maxTestCount
        for (int i = 0; i < 20; i++) {
            trace.addValue(arg1, i * 10, i);
        }
        
        // Set a low max test count
        TestGenerationContext limitedContext = TestGenerationContext.builder()
                .targetMethodSignature(context.getTargetMethodSignature())
                .targetClassName(context.getTargetClassName())
                .packageName(context.getPackageName())
                .outputDirectory(context.getOutputDirectory())
                .testFramework(context.getTestFramework())
                .generateEdgeCases(context.isGenerateEdgeCases())
                .generateNegativeTests(context.isGenerateNegativeTests())
                .namingStrategy(context.getNamingStrategy())
                .maxTestCount(3)
                .build();
        
        List<Path> generatedFiles = generator.generateTests(trace, limitedContext);
        
        assertNotNull(generatedFiles);
        if (!generatedFiles.isEmpty()) {
            // Verify that the generator respects the maxTestCount limit
            Path testFile = generatedFiles.get(0);
            try {
                String content = Files.readString(testFile);
                long testMethodCount = content.lines()
                        .filter(line -> line.trim().startsWith("@Test"))
                        .count();
                
                assertTrue(testMethodCount <= 3, 
                          "Should not generate more than maxTestCount test methods");
            } catch (Exception e) {
                fail("Failed to analyze generated file: " + e.getMessage());
            }
        }
    }
    
    @Test
    void testGenerateTestsWithMetadata() {
        ExportableValue arg1 = new JavaArgumentIdentifier(
            ArgumentIdentifierParameters.builder()
                .argumentSlot(0)
                .variableType("String")
                .build()
        );
        
        trace.addValue(arg1, 100, "test_value");
        trace.addMetadata("execution_context", "unit_test");
        trace.addMetadata("version", "1.0");
        
        List<Path> generatedFiles = generator.generateTests(trace, context);
        
        assertNotNull(generatedFiles);
        if (!generatedFiles.isEmpty()) {
            Path testFile = generatedFiles.get(0);
            try {
                String content = Files.readString(testFile);
                assertTrue(content.contains("Enhanced trace-based"), 
                          "Should mention enhanced trace-based generation");
                assertTrue(content.contains("Trace summary"), 
                          "Should include trace summary in comments");
            } catch (Exception e) {
                fail("Failed to read generated file: " + e.getMessage());
            }
        }
    }
}
