package cz.cuni.mff.d3s.autodebugger.testgenerator.java;

import cz.cuni.mff.d3s.autodebugger.model.common.trace.TemporalTrace;
import cz.cuni.mff.d3s.autodebugger.model.common.trace.Trace;
import cz.cuni.mff.d3s.autodebugger.model.java.TraceAdapter;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.*;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.JavaArgumentIdentifier;
import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.ExportableValue;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TestGenerationContext;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TestNamingStrategy;
import cz.cuni.mff.d3s.autodebugger.testgenerator.java.trace.TemporalTraceBasedGenerator;
import cz.cuni.mff.d3s.autodebugger.testgenerator.java.trace.NaiveTraceBasedGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test demonstrating the enhanced test generation workflow
 * using the new TemporalTrace implementation and improved generators.
 */
class EnhancedWorkflowIntegrationTest {
    
    @TempDir
    Path tempDir;
    
    private Map<Integer, JavaValueIdentifier> identifierMapping;
    private TestGenerationContext context;
    
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
        
        JavaClassIdentifier calculatorClass = new JavaClassIdentifier(
            ClassIdentifierParameters.builder()
                .className("Calculator")
                .packageIdentifier(JavaPackageIdentifier.DEFAULT_PACKAGE)
                .build()
        );

        JavaFieldIdentifier field1 = new JavaFieldIdentifier(
            FieldIdentifierParameters.builder()
                .variableName("operationCount")
                .ownerClassIdentifier(calculatorClass)
                .variableType("int")
                .build()
        );
        
        identifierMapping.put(0, arg1);
        identifierMapping.put(1, arg2);
        identifierMapping.put(2, field1);
        
        // Create test generation context
        context = TestGenerationContext.builder()
                .targetMethodSignature("Calculator.divide(int, int)")
                .targetClassName("com.example.Calculator")
                .packageName("com.example.test")
                .outputDirectory(tempDir)
                .testFramework("junit5")
                .namingStrategy(TestNamingStrategy.DESCRIPTIVE)
                .maxTestCount(5)
                .generateEdgeCases(true)
                .generateNegativeTests(true)
                .build();
    }
    
    @Test
    void testLegacyToTemporalTraceConversion() {
        // 1. Create a legacy trace (simulating existing data)
        Trace legacyTrace = new Trace();
        legacyTrace.addIntValue(0, 10);  // arg1
        legacyTrace.addIntValue(0, 20);
        legacyTrace.addIntValue(1, 2);   // arg2
        legacyTrace.addIntValue(1, 4);
        legacyTrace.addIntValue(2, 1);   // field1
        legacyTrace.addIntValue(2, 2);
        
        // 2. Convert to enhanced trace
        TemporalTrace enhancedTrace = TraceAdapter.convertToEnhanced(legacyTrace, identifierMapping);
        
        // 3. Verify conversion
        assertNotNull(enhancedTrace);
        assertEquals(3, enhancedTrace.getTrackedVariableCount());
        assertTrue(enhancedTrace.getTotalEventCount() > 0);
        
        // 4. Verify metadata was added during conversion
        assertEquals("legacy_trace", enhancedTrace.getMetadata("converted_from"));
        assertNotNull(enhancedTrace.getMetadata("conversion_timestamp"));
        
        System.out.println("✅ Legacy trace conversion completed successfully!");
        System.out.println(enhancedTrace.getSummary());
    }
    
    @Test
    void testTemporalTraceBasedGeneration() {
        // 1. Create an enhanced trace with temporal data
        TemporalTrace enhancedTrace = createRealisticTemporalTrace();
        
        // 2. Generate tests using enhanced generator
        TemporalTraceBasedGenerator enhancedGenerator = new TemporalTraceBasedGenerator();
        List<Path> enhancedFiles = enhancedGenerator.generateTests(enhancedTrace, context);
        
        // 3. Verify enhanced generation results
        assertNotNull(enhancedFiles);
        assertFalse(enhancedFiles.isEmpty(), "Enhanced generator should produce test files");
        
        // 4. Analyze generated content
        for (Path file : enhancedFiles) {
            assertTrue(Files.exists(file), "Generated file should exist: " + file);
            
            try {
                String content = Files.readString(file);
                
                // Verify enhanced features
                assertTrue(content.contains("Enhanced trace-based"), 
                          "Should mention enhanced generation strategy");
                assertTrue(content.contains("@DisplayName"), 
                          "Should include descriptive display names");
                assertTrue(content.contains("event"), 
                          "Should reference event indices");
                assertTrue(content.contains("State captured"), 
                          "Should mention state capture");
                
                System.out.println("✅ Enhanced test generation completed successfully!");
                System.out.println("📄 Generated file: " + file.getFileName());
                
            } catch (Exception e) {
                fail("Failed to read enhanced generated file: " + e.getMessage());
            }
        }
    }
    
    @Test
    void testComparisonBetweenNaiveAndEnhanced() {
        // 1. Create both legacy and enhanced traces with same data
        Trace legacyTrace = new Trace();
        legacyTrace.addIntValue(0, 15);
        legacyTrace.addIntValue(1, 3);
        
        TemporalTrace enhancedTrace = TraceAdapter.convertToEnhanced(legacyTrace, identifierMapping);
        
        // 2. Generate tests with both approaches
        NaiveTraceBasedGenerator naiveGenerator = new NaiveTraceBasedGenerator(identifierMapping);
        List<Path> naiveFiles = naiveGenerator.generateTests(legacyTrace, context);
        
        TemporalTraceBasedGenerator enhancedGenerator = new TemporalTraceBasedGenerator();
        List<Path> enhancedFiles = enhancedGenerator.generateTests(enhancedTrace, context);
        
        // 3. Compare results
        assertNotNull(naiveFiles);
        assertNotNull(enhancedFiles);
        
        if (!naiveFiles.isEmpty() && !enhancedFiles.isEmpty()) {
            try {
                String naiveContent = Files.readString(naiveFiles.getFirst());
                String enhancedContent = Files.readString(enhancedFiles.getFirst());
                
                // Both should generate valid test classes
                assertTrue(naiveContent.contains("@Test"));
                assertTrue(enhancedContent.contains("@Test"));
                
                // Enhanced should have additional features
                assertTrue(enhancedContent.contains("@DisplayName"));
                assertTrue(enhancedContent.contains("Enhanced"));
                
                System.out.println("✅ Comparison between naive and enhanced generators completed!");
                System.out.println("📊 Naive generator features: Basic test structure");
                System.out.println("📊 Enhanced generator features: Temporal context, display names, event tracking");
                
            } catch (Exception e) {
                fail("Failed to compare generated files: " + e.getMessage());
            }
        }
    }
    
    @Test
    void testSyntheticTraceGeneration() {
        // 1. Create synthetic trace for testing
        TemporalTrace syntheticTrace = TraceAdapter.createSyntheticTrace(identifierMapping);
        
        // 2. Verify synthetic trace properties
        assertNotNull(syntheticTrace);
        assertTrue(syntheticTrace.getTrackedVariableCount() > 0);
        assertTrue(syntheticTrace.getTotalEventCount() > 0);
        assertEquals(true, syntheticTrace.getMetadata("synthetic"));
        
        // 3. Generate tests from synthetic trace
        TemporalTraceBasedGenerator generator = new TemporalTraceBasedGenerator();
        List<Path> generatedFiles = generator.generateTests(syntheticTrace, context);
        
        // 4. Verify generation works with synthetic data
        assertNotNull(generatedFiles);
        
        System.out.println("✅ Synthetic trace generation and test creation completed!");
        System.out.println(syntheticTrace.getSummary());
    }
    
    @Test
    void testTraceTemporalQueries() {
        // 1. Create a trace with temporal evolution
        TemporalTrace trace = new TemporalTrace();
        ExportableValue counter = identifierMapping.get(2); // field1
        
        // Simulate counter evolution over time
        trace.addValue(counter, 100, 0);   // Initial value
        trace.addValue(counter, 200, 1);   // After first operation
        trace.addValue(counter, 300, 2);   // After second operation
        trace.addValue(counter, 400, 3);   // After third operation
        
        // 2. Test temporal queries
        assertEquals(0, trace.getLatestValueBefore(counter, 150).orElse(-1));
        assertEquals(1, trace.getLatestValueBefore(counter, 250).orElse(-1));
        assertEquals(2, trace.getLatestValueBefore(counter, 350).orElse(-1));
        assertEquals(3, trace.getLatestValueBefore(counter, 450).orElse(-1));
        
        // 3. Test state snapshots at different points
        var snapshot200 = trace.getStateSnapshotAt(200);
        assertEquals(1, snapshot200.get(counter));
        
        var snapshot350 = trace.getStateSnapshotAt(350);
        assertEquals(2, snapshot350.get(counter));
        
        System.out.println("✅ Temporal query testing completed successfully!");
        System.out.println("📈 Verified state evolution over " + trace.getTotalEventCount() + " events");
    }
    
    /**
     * Creates a realistic enhanced trace simulating a complex execution scenario.
     */
    private TemporalTrace createRealisticTemporalTrace() {
        TemporalTrace trace = new TemporalTrace();
        
        ExportableValue arg1 = identifierMapping.get(0);
        ExportableValue arg2 = identifierMapping.get(1);
        ExportableValue field1 = identifierMapping.get(2);
        
        // Simulate a realistic execution timeline
        trace.addValue(field1, 50, 0);        // Initialize counter
        
        // First method invocation
        trace.addValue(arg1, 100, 20);
        trace.addValue(arg2, 101, 4);
        trace.addValue(field1, 110, 1);       // Counter incremented
        
        // Second method invocation
        trace.addValue(arg1, 200, 15);
        trace.addValue(arg2, 201, 3);
        trace.addValue(field1, 210, 2);       // Counter incremented
        
        // Third method invocation with edge case
        trace.addValue(arg1, 300, 10);
        trace.addValue(arg2, 301, 0);         // Division by zero scenario
        trace.addValue(field1, 310, 3);       // Counter incremented
        
        // Add metadata about the execution
        trace.addMetadata("scenario", "realistic_calculator_usage");
        trace.addMetadata("method_invocations", 3);
        trace.addMetadata("edge_cases", "division_by_zero");
        
        return trace;
    }
}
