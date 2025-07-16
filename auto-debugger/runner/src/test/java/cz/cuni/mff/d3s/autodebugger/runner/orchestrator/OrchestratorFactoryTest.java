package cz.cuni.mff.d3s.autodebugger.runner.orchestrator;

import cz.cuni.mff.d3s.autodebugger.model.common.TargetLanguage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrchestratorFactoryTest {

    @Test
    void testCreateJavaOrchestratorWithEnum() {
        Orchestrator orchestrator = OrchestratorFactory.create(TargetLanguage.JAVA);

        assertNotNull(orchestrator);
        assertEquals(TargetLanguage.JAVA, orchestrator.getSupportedLanguage());
        assertInstanceOf(JavaOrchestrator.class, orchestrator);
    }

    @Test
    void testCreateJavaOrchestratorWithString() {
        Orchestrator orchestrator = OrchestratorFactory.create("java");

        assertNotNull(orchestrator);
        assertEquals(TargetLanguage.JAVA, orchestrator.getSupportedLanguage());
        assertInstanceOf(JavaOrchestrator.class, orchestrator);
    }

    @Test
    void testCreateOrchestratorCaseInsensitive() {
        Orchestrator orchestrator1 = OrchestratorFactory.create("JAVA");
        Orchestrator orchestrator2 = OrchestratorFactory.create("Java");

        assertNotNull(orchestrator1);
        assertNotNull(orchestrator2);
        assertEquals(TargetLanguage.JAVA, orchestrator1.getSupportedLanguage());
        assertEquals(TargetLanguage.JAVA, orchestrator2.getSupportedLanguage());
    }

    @Test
    void testCreateOrchestratorNullLanguage() {
        assertThrows(IllegalArgumentException.class, () -> {
            OrchestratorFactory.create((TargetLanguage) null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            OrchestratorFactory.create((String) null);
        });
    }

    @Test
    void testCreateOrchestratorEmptyLanguage() {
        assertThrows(IllegalArgumentException.class, () -> {
            OrchestratorFactory.create("");
        });
    }

    @Test
    void testIsLanguageSupported() {
        // Test with enum
        assertTrue(OrchestratorFactory.isLanguageSupported(TargetLanguage.JAVA));

        // Test with string
        assertTrue(OrchestratorFactory.isLanguageSupported("java"));
        assertTrue(OrchestratorFactory.isLanguageSupported("JAVA"));
        assertFalse(OrchestratorFactory.isLanguageSupported("invalid-language"));
        assertFalse(OrchestratorFactory.isLanguageSupported((String) null));
        assertFalse(OrchestratorFactory.isLanguageSupported(""));
    }

    @Test
    void testGetSupportedLanguages() {
        String supportedLanguages = OrchestratorFactory.getSupportedLanguages();

        assertNotNull(supportedLanguages);
        assertTrue(supportedLanguages.contains("java"));
    }
}
