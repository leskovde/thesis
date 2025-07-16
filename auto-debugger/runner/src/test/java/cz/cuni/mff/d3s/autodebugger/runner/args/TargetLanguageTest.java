package cz.cuni.mff.d3s.autodebugger.runner.args;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TargetLanguageTest {
    
    @Test
    void testFromIdentifierValid() {
        assertEquals(TargetLanguage.JAVA, TargetLanguage.fromIdentifier("java"));
        assertEquals(TargetLanguage.JAVA, TargetLanguage.fromIdentifier("JAVA"));
        assertEquals(TargetLanguage.JAVA, TargetLanguage.fromIdentifier("Java"));
        assertEquals(TargetLanguage.JAVA, TargetLanguage.fromIdentifier("  java  "));
    }
    
    @Test
    void testFromIdentifierInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            TargetLanguage.fromIdentifier("invalid");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            TargetLanguage.fromIdentifier(null);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            TargetLanguage.fromIdentifier("");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            TargetLanguage.fromIdentifier("   ");
        });
    }
    
    @Test
    void testIsSupported() {
        assertTrue(TargetLanguage.isSupported("java"));
        assertTrue(TargetLanguage.isSupported("JAVA"));

        assertFalse(TargetLanguage.isSupported("invalid"));
        assertFalse(TargetLanguage.isSupported(null));
        assertFalse(TargetLanguage.isSupported(""));
    }
    
    @Test
    void testGetSupportedLanguagesString() {
        String supported = TargetLanguage.getSupportedLanguagesString();
        
        assertNotNull(supported);
        assertTrue(supported.contains("java"));
    }
    
    @Test
    void testValidateFileExtension() {
        // Test Java
        assertTrue(TargetLanguage.JAVA.validateFileExtension("app.jar", true));
        assertTrue(TargetLanguage.JAVA.validateFileExtension("Test.java", false));
        assertFalse(TargetLanguage.JAVA.validateFileExtension("app.exe", true));
        assertFalse(TargetLanguage.JAVA.validateFileExtension("test.py", false));
        
        // Test edge cases
        assertFalse(TargetLanguage.JAVA.validateFileExtension(null, true));
        assertFalse(TargetLanguage.JAVA.validateFileExtension("", false));
        
        // Test case insensitive
        assertTrue(TargetLanguage.JAVA.validateFileExtension("APP.JAR", true));
        assertTrue(TargetLanguage.JAVA.validateFileExtension("Test.JAVA", false));
    }
}
