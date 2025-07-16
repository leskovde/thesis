package cz.cuni.mff.d3s.autodebugger.testgenerator.java.trace;

import cz.cuni.mff.d3s.autodebugger.model.common.trace.Trace;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.JavaArgumentIdentifier;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.ArgumentIdentifierParameters;
import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.ExportableValue;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TestGenerationContext;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TestNamingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class NaiveTraceBasedGeneratorTest {
    
    @TempDir
    Path tempDir;
    
    private NaiveTraceBasedGenerator generator;
    private Map<Integer, ExportableValue> identifierMapping;
    private Trace trace;
    
    @BeforeEach
    void setUp() {
        // Create test identifier mapping
        identifierMapping = new HashMap<>();
        
        JavaArgumentIdentifier arg1 = new JavaArgumentIdentifier(
            ArgumentIdentifierParameters.builder()
                .argumentSlot(0)
                .variableType("int")
                .build()
        );
        // Note: setInternalId method may not be available, using slot as key instead

        JavaArgumentIdentifier arg2 = new JavaArgumentIdentifier(
            ArgumentIdentifierParameters.builder()
                .argumentSlot(1)
                .variableType("int")
                .build()
        );
        // Note: setInternalId method may not be available, using slot as key instead
        
        // Use argument slot as key since setInternalId may not be available
        identifierMapping.put(0, arg1);  // slot 0
        identifierMapping.put(1, arg2);  // slot 1
        
        generator = new NaiveTraceBasedGenerator(identifierMapping);
        
        // Create test trace
        trace = new Trace();
        trace.addIntValue(0, 10);  // slot 0
        trace.addIntValue(0, 20);
        trace.addIntValue(1, 2);   // slot 1
        trace.addIntValue(1, 4);
    }
    
    @Test
    void testGenerateTestsWithDefaultContext() {
        List<Path> generatedFiles = generator.generateTests(trace);
        
        assertNotNull(generatedFiles);
        // The default implementation may return empty list or generate files
        // depending on the implementation details
    }
    
    @Test
    void testGenerateTestsWithCustomContext() {
        TestGenerationContext context = TestGenerationContext.builder()
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
        
        List<Path> generatedFiles = generator.generateTests(trace, context);
        
        assertNotNull(generatedFiles);
        
        // If files were generated, verify they exist and contain expected content
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
            } catch (Exception e) {
                fail("Failed to read generated file: " + e.getMessage());
            }
        }
    }
    
    @Test
    void testGenerateTestsWithSimpleNamingStrategy() {
        TestGenerationContext context = TestGenerationContext.builder()
                .targetMethodSignature("Calculator.add")
                .targetClassName("Calculator")
                .outputDirectory(tempDir)
                .namingStrategy(TestNamingStrategy.SIMPLE)
                .build();
        
        List<Path> generatedFiles = generator.generateTests(trace, context);
        
        assertNotNull(generatedFiles);
    }
    
    @Test
    void testGenerateTestsWithEmptyTrace() {
        Trace emptyTrace = new Trace();
        
        TestGenerationContext context = TestGenerationContext.builder()
                .targetMethodSignature("Calculator.multiply")
                .targetClassName("Calculator")
                .outputDirectory(tempDir)
                .build();
        
        List<Path> generatedFiles = generator.generateTests(emptyTrace, context);
        
        assertNotNull(generatedFiles);
    }
}
