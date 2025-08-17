package cz.cuni.mff.d3s.autodebugger.testgenerator.common;

import cz.cuni.mff.d3s.autodebugger.model.common.RunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.common.TargetLanguage;
import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.MethodIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for TestGenerationContextFactory.
 */
class TestGenerationContextFactoryTest {

    @TempDir
    Path tempDir;

    private RunConfiguration mockRunConfiguration;
    private MethodIdentifier mockMethodIdentifier;

    @BeforeEach
    void setUp() {
        // Create a mock method identifier
        mockMethodIdentifier = new MethodIdentifier("authenticate", "boolean",
                List.of("java.lang.String", "java.lang.String")) {
            @Override
            public String getClassName() {
                return "UserService";
            }

            @Override
            public String getPackageName() {
                return "";
            }

            @Override
            public String getFullyQualifiedClassName() {
                return "UserService";
            }

            @Override
            public String getFullyQualifiedSignature() {
                return "UserService.authenticate(String, String)";
            }

            @Override
            public String toString() {
                return "UserService.authenticate(String, String)";
            }
        };

        // Create mock run configuration
        mockRunConfiguration = new RunConfiguration() {
            @Override
            public TargetLanguage getLanguage() {
                return TargetLanguage.JAVA;
            }

            @Override
            public Path getApplicationPath() {
                return Path.of("test-app.jar");
            }

            @Override
            public Path getSourceCodePath() {
                return Path.of("src/main/java");
            }

            @Override
            public MethodIdentifier getTargetMethod() {
                return mockMethodIdentifier;
            }

            @Override
            public List getExportableValues() {
                return Collections.emptyList();
            }

            @Override
            public Path getOutputDirectory() {
                return tempDir;
            }

            @Override
            public List<String> getRuntimeArguments() {
                return Collections.emptyList();
            }

            @Override
            public void validate() {
                // Mock implementation - no validation needed
            }
        };
    }

    @Test
    void testCreateFromRunConfiguration_WithDefaultSettings() {
        TestGenerationContext context = TestGenerationContextFactory
                .createFromRunConfiguration(mockRunConfiguration);

        assertNotNull(context);
        assertEquals("UserService.authenticate(String, String)",
                    context.getTargetMethod().getFullyQualifiedSignature());
        assertEquals("UserService", context.getTargetMethod().getFullyQualifiedClassName());
        assertEquals("", context.getTargetMethod().getPackageName());
        assertEquals(tempDir, context.getOutputDirectory());

        // Verify default settings
        assertEquals("junit5", context.getTestFramework());
        assertEquals(50, context.getMaxTestCount());
        assertTrue(context.isGenerateEdgeCases());
        assertTrue(context.isGenerateNegativeTests());
        assertEquals(TestNamingStrategy.DESCRIPTIVE, context.getNamingStrategy());
    }

    @Test
    void testCreateFromRunConfiguration_WithCustomSettings() {
        TestGenerationSettings customSettings = TestGenerationSettings.builder()
                .testFramework("junit4")
                .maxTestCount(25)
                .generateEdgeCases(false)
                .generateNegativeTests(false)
                .namingStrategy(TestNamingStrategy.SIMPLE)
                .build();

        TestGenerationContext context = TestGenerationContextFactory
                .createFromRunConfiguration(mockRunConfiguration, customSettings);

        assertNotNull(context);
        assertEquals("UserService.authenticate(String, String)",
                    context.getTargetMethod().getFullyQualifiedSignature());
        assertEquals("UserService", context.getTargetMethod().getFullyQualifiedClassName());
        assertEquals("", context.getTargetMethod().getPackageName());

        // Verify custom settings
        assertEquals("junit4", context.getTestFramework());
        assertEquals(25, context.getMaxTestCount());
        assertFalse(context.isGenerateEdgeCases());
        assertFalse(context.isGenerateNegativeTests());
        assertEquals(TestNamingStrategy.SIMPLE, context.getNamingStrategy());
    }

    @Test
    void testCreateFromRunConfiguration_WithNullMethod() {
        RunConfiguration configWithNullMethod = new RunConfiguration() {
            @Override
            public TargetLanguage getLanguage() {
                return TargetLanguage.JAVA;
            }

            @Override
            public Path getApplicationPath() {
                return Path.of("test-app.jar");
            }

            @Override
            public Path getSourceCodePath() {
                return Path.of("src/main/java");
            }

            @Override
            public MethodIdentifier getTargetMethod() {
                return null;
            }

            @Override
            public List getExportableValues() {
                return Collections.emptyList();
            }

            @Override
            public Path getOutputDirectory() {
                return tempDir;
            }

            @Override
            public List<String> getRuntimeArguments() {
                return Collections.emptyList();
            }

            @Override
            public void validate() {
                // Mock implementation
            }
        };

        TestGenerationContext context = TestGenerationContextFactory
                .createFromRunConfiguration(configWithNullMethod);

        assertNotNull(context);
        assertThrows(IllegalStateException.class, context::getTargetMethodSignature);
        assertThrows(IllegalStateException.class, context::getTargetClassName);
        assertThrows(IllegalStateException.class, context::getPackageName);
    }

    @Test
    void testCreateMinimal() {
        MethodIdentifier methodIdentifier = new MethodIdentifier("add", "int", List.of("int", "int")) {
            @Override public String getClassName() { return "Calculator"; }
            @Override public String getPackageName() { return ""; }
            @Override public String getFullyQualifiedClassName() { return "Calculator"; }
            @Override public String getFullyQualifiedSignature() { return "Calculator.add(int, int)"; }
        };

        TestGenerationContext context = TestGenerationContextFactory
                .createMinimal(methodIdentifier, tempDir);

        assertNotNull(context);
        assertEquals("Calculator.add(int, int)", context.getTargetMethod().getFullyQualifiedSignature());
        assertEquals(tempDir, context.getOutputDirectory());
    }

    @Test
    void testCreateFromRunConfiguration_WithNullConfiguration() {
        assertThrows(IllegalArgumentException.class, () -> 
            TestGenerationContextFactory.createFromRunConfiguration(null));
    }

    @Test
    void testCreateFromRunConfiguration_WithNullSettings() {
        // Should use default settings when null is passed
        TestGenerationContext context = TestGenerationContextFactory
                .createFromRunConfiguration(mockRunConfiguration, null);

        assertNotNull(context);
        assertEquals("junit5", context.getTestFramework()); // Default value
        assertEquals(50, context.getMaxTestCount()); // Default value
    }
}
