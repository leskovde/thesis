package cz.cuni.mff.d3s.autodebugger.testrunner.java;

import cz.cuni.mff.d3s.autodebugger.testrunner.common.TestRunnerConfiguration;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Manages instrumentation setup for test execution.
 * Handles DiSL agent configuration and trace collection setup.
 */
@Slf4j
public class InstrumentationManager {
    
    private boolean instrumentationSetup = false;
    private Path instrumentationJarPath;
    
    /**
     * Sets up instrumentation for test execution based on the configuration.
     * 
     * @param configuration Test runner configuration containing instrumentation settings
     */
    public void setupInstrumentation(TestRunnerConfiguration configuration) {
        log.info("Setting up instrumentation for test execution");
        
        if (!configuration.isEnableInstrumentation()) {
            log.debug("Instrumentation is disabled in configuration");
            return;
        }
        
        instrumentationJarPath = configuration.getInstrumentationJarPath();
        
        if (instrumentationJarPath == null) {
            log.warn("Instrumentation enabled but no instrumentation JAR path provided");
            return;
        }
        
        if (!Files.exists(instrumentationJarPath)) {
            log.error("Instrumentation JAR not found: {}", instrumentationJarPath);
            throw new RuntimeException("Instrumentation JAR not found: " + instrumentationJarPath);
        }
        
        log.info("Instrumentation JAR found: {}", instrumentationJarPath);
        
        // Set up JVM arguments for DiSL agent
        setupDiSLAgent();
        
        // Set up ShadowVM for trace collection
        setupShadowVM();
        
        instrumentationSetup = true;
        log.info("Instrumentation setup completed");
    }
    
    /**
     * Checks if instrumentation has been properly set up.
     * 
     * @return true if instrumentation is ready, false otherwise
     */
    public boolean isInstrumentationReady() {
        return instrumentationSetup;
    }
    
    /**
     * Gets the instrumentation JAR path.
     * 
     * @return Path to the instrumentation JAR, or null if not set up
     */
    public Path getInstrumentationJarPath() {
        return instrumentationJarPath;
    }
    
    /**
     * Cleans up instrumentation resources.
     */
    public void cleanup() {
        log.info("Cleaning up instrumentation resources");
        instrumentationSetup = false;
        instrumentationJarPath = null;
    }
    
    private void setupDiSLAgent() {
        log.debug("Setting up DiSL agent configuration");
        
        // Set system properties for DiSL
        System.setProperty("disl.instrumentation.jar", instrumentationJarPath.toString());
        
        // Configure DiSL server settings
        System.setProperty("disl.server.host", "localhost");
        System.setProperty("disl.server.port", "11217");
        
        // Configure ShadowVM settings
        System.setProperty("shadowvm.server.host", "localhost");
        System.setProperty("shadowvm.server.port", "11218");
        
        log.debug("DiSL agent configuration completed");
    }
    
    private void setupShadowVM() {
        log.debug("Setting up ShadowVM for trace collection");
        
        // Configure ShadowVM for remote analysis
        System.setProperty("shadowvm.analysis.class", "cz.cuni.mff.d3s.autodebugger.analyzer.disl.Collector");
        
        // Set up trace collection directory
        Path traceDir = Path.of(System.getProperty("java.io.tmpdir"), "autodebugger-traces");
        try {
            Files.createDirectories(traceDir);
            System.setProperty("autodebugger.trace.dir", traceDir.toString());
            log.debug("Trace collection directory: {}", traceDir);
        } catch (Exception e) {
            log.warn("Failed to create trace directory: {}", traceDir, e);
        }
        
        log.debug("ShadowVM setup completed");
    }
    
    /**
     * Gets JVM arguments needed for instrumentation.
     * 
     * @return Array of JVM arguments for DiSL agent
     */
    public String[] getInstrumentationJVMArgs() {
        if (!instrumentationSetup) {
            return new String[0];
        }
        
        return new String[] {
            "-javaagent:" + instrumentationJarPath.toString(),
            "-Ddisl.server.host=localhost",
            "-Ddisl.server.port=11217",
            "-Dshadowvm.server.host=localhost",
            "-Dshadowvm.server.port=11218"
        };
    }
}
