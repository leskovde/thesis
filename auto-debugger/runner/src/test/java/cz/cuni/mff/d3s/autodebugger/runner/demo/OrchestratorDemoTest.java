package cz.cuni.mff.d3s.autodebugger.runner.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrchestratorDemoTest {
    
    @Test
    void testDemonstrateOrchestratorAPI() {
        // This test verifies that the orchestrator API demonstration runs without exceptions
        assertDoesNotThrow(() -> {
            OrchestratorDemo.demonstrateOrchestratorAPI();
        });
    }
    
    @Test
    void testDemonstrateMainWorkflow() {
        // This test verifies that the main workflow demonstration runs without exceptions
        assertDoesNotThrow(() -> {
            OrchestratorDemo.demonstrateMainWorkflow();
        });
    }
}
