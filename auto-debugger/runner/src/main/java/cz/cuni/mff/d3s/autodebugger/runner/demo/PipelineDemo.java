package cz.cuni.mff.d3s.autodebugger.runner.demo;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.Instrumentor;
import cz.cuni.mff.d3s.autodebugger.runner.model.ModelBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Demonstration of the pipeline that converts CLI/plugin arguments into a DiSLInstrumentor.
 * This class shows how to use the ModelBuilder to create instrumentors from various input formats.
 */
@Slf4j
public class PipelineDemo {
    
    public static void main(String[] args) {
        demonstratePipeline();
    }
    
    /**
     * Demonstrates the complete pipeline with various examples.
     */
    public static void demonstratePipeline() {
        log.info("=== Auto-Debugger Pipeline Demonstration ===");
        
        // Example 1: Simple method with parameters
        demonstrateExample1();
        
        // Example 2: Method in default package
        demonstrateExample2();
        
        // Example 3: Method with fields
        demonstrateExample3();
        
        // Example 4: Complex method with multiple types
        demonstrateExample4();
        
        log.info("=== Pipeline Demonstration Complete ===");
    }
    
    /**
     * Example 1: Simple method with parameters
     * Demonstrates: org.example.Calculator.add(int,int) with parameter monitoring
     */
    private static void demonstrateExample1() {
        log.info("\n--- Example 1: Simple Calculator Method ---");
        
        try {
            Instrumentor instrumentor = ModelBuilder.buildInstrumentor(
                "calculator.jar",                           // Application JAR path
                "src/main/java",                           // Source code path
                "org.example.Calculator.add(int,int)",     // Target method reference
                List.of("0:int", "1:int"),                 // Target parameters (slot:type format)
                List.of()                                  // No target fields
            );
            
            log.info("✓ Successfully created instrumentor for Calculator.add");
            log.info("  - Method: {}", instrumentor.getMethod().getMethodName());
            log.info("  - Class: {}", instrumentor.getMethod().getOwnerClassIdentifier().getClassName());
            log.info("  - Package: {}", instrumentor.getMethod().getOwnerClassIdentifier().getPackageIdentifier().getPackageName());
            log.info("  - Exported values: {}", instrumentor.getExportedValues().size());
            
        } catch (Exception e) {
            log.info("✗ Expected failure (files don't exist): {}", e.getMessage());
        }
    }
    
    /**
     * Example 2: Method in default package
     * Demonstrates: Test.main(String[]) in default package
     */
    private static void demonstrateExample2() {
        log.info("\n--- Example 2: Default Package Method ---");
        
        try {
            Instrumentor instrumentor = ModelBuilder.buildInstrumentor(
                "test.jar",                               // Application JAR path
                "src",                                    // Source code path
                "Test.main(String[])",                    // Target method reference (no package)
                List.of("0:java.lang.String[]"),          // Target parameters
                List.of()                                 // No target fields
            );
            
            log.info("✓ Successfully created instrumentor for Test.main");
            log.info("  - Method: {}", instrumentor.getMethod().getMethodName());
            log.info("  - Class: {}", instrumentor.getMethod().getOwnerClassIdentifier().getClassName());
            log.info("  - Package: {}", instrumentor.getMethod().getOwnerClassIdentifier().getPackageIdentifier().getPackageName());
            log.info("  - Is default package: {}", instrumentor.getMethod().getOwnerClassIdentifier().getPackageIdentifier().getPackageName().isEmpty());
            
        } catch (Exception e) {
            log.info("✗ Expected failure (files don't exist): {}", e.getMessage());
        }
    }
    
    /**
     * Example 3: Method with fields
     * Demonstrates: com.service.UserService.processUser(User) with field monitoring
     */
    private static void demonstrateExample3() {
        log.info("\n--- Example 3: Method with Field Monitoring ---");
        
        try {
            Instrumentor instrumentor = ModelBuilder.buildInstrumentor(
                "userservice.jar",                        // Application JAR path
                "src/main/java",                          // Source code path
                "com.service.UserService.processUser(com.model.User)", // Target method reference
                List.of("0:com.model.User"),              // Target parameters
                List.of("int:userCount", "boolean:isActive", "java.lang.String:serviceName") // Target fields
            );
            
            log.info("✓ Successfully created instrumentor for UserService.processUser");
            log.info("  - Method: {}", instrumentor.getMethod().getMethodName());
            log.info("  - Exported values: {}", instrumentor.getExportedValues().size());
            log.info("    - Parameters: 1");
            log.info("    - Fields: 3");
            
        } catch (Exception e) {
            log.info("✗ Expected failure (files don't exist): {}", e.getMessage());
        }
    }
    
    /**
     * Example 4: Complex method with multiple types
     * Demonstrates: Various parameter and field types
     */
    private static void demonstrateExample4() {
        log.info("\n--- Example 4: Complex Types ---");
        
        try {
            Instrumentor instrumentor = ModelBuilder.buildInstrumentor(
                "complex.jar",                            // Application JAR path
                "src/main/java",                          // Source code path
                "com.complex.DataProcessor.process(java.util.List,java.util.Map,boolean)", // Target method
                List.of(
                    "0:java.util.List",                   // List parameter
                    "1:java.util.Map",                    // Map parameter
                    "2:boolean"                           // Boolean parameter
                ),
                List.of(
                    "java.util.Set:processedItems",       // Set field
                    "long:processCount",                  // Long field
                    "double:averageTime"                  // Double field
                )
            );
            
            log.info("✓ Successfully created instrumentor for DataProcessor.process");
            log.info("  - Method: {}", instrumentor.getMethod().getMethodName());
            log.info("  - Parameter types: {}", instrumentor.getMethod().getParameterTypes());
            log.info("  - Total exported values: {}", instrumentor.getExportedValues().size());
            
        } catch (Exception e) {
            log.info("✗ Expected failure (files don't exist): {}", e.getMessage());
        }
    }
}
