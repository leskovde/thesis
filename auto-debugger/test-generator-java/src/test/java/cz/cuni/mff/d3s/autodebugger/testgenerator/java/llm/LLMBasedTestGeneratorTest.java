package cz.cuni.mff.d3s.autodebugger.testgenerator.java.llm;

import cz.cuni.mff.d3s.autodebugger.model.common.trace.Trace;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.LLMConfiguration;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TestGenerationContext;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TestNamingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LLMBasedTestGeneratorTest {

    private LLMBasedTestGenerator generator;
    private Trace trace;
    private TestGenerationContext context;

    @TempDir
    Path tempDir;

    private Path mockSourceFile;

    @BeforeEach
    void setUp() throws Exception {
        generator = new LLMBasedTestGenerator();

        // Create a simple trace for testing
        trace = new Trace();
        trace.addIntValue(0, 10);
        trace.addIntValue(1, 5);

        // Create a mock source file for testing
        mockSourceFile = tempDir.resolve("Calculator.java");
        Files.writeString(mockSourceFile, """
            package com.example;

            public class Calculator {
                public int add(int a, int b) {
                    return a + b;
                }
            }
            """);

        // Create test context
        context = TestGenerationContext.builder()
                .targetMethodSignature("Calculator.add")
                .targetClassName("Calculator")
                .packageName("com.example")
                .outputDirectory(tempDir)
                .testFramework("JUnit5")
                .maxTestCount(10)
                .generateEdgeCases(true)
                .generateNegativeTests(false)
                .namingStrategy(TestNamingStrategy.DESCRIPTIVE)
                .build();
    }

    @Test
    void testConfigureWithValidConfiguration() {
        LLMConfiguration config = LLMConfiguration.builder()
                .provider("anthropic")
                .modelName("claude-sonnet-4-20250514")
                .apiKey("test-key")
                .maxTokens(4000)
                .temperature(0.3)
                .build();

        assertDoesNotThrow(() -> generator.configure(config));
    }

    @Test
    void testConfigureWithNullConfiguration() {
        assertThrows(IllegalArgumentException.class, () -> generator.configure(null));
    }

    @Test
    void testGenerateTestsWithoutConfiguration() {
        // Should throw exception if not configured
        assertThrows(IllegalStateException.class, () ->
            generator.generateTests(trace, mockSourceFile, context));
    }

    @Test
    void testGenerateTestsWithMockConfiguration() {
        // Configure with mock settings (no real API call)
        LLMConfiguration config = LLMConfiguration.builder()
                .provider("mock")  // Use mock provider to avoid real API calls
                .modelName("test-model")
                .apiKey("test-key")
                .maxTokens(1000)
                .temperature(0.5)
                .build();

        generator.configure(config);

        // This should not throw an exception, but may return empty list
        // since we're using a mock configuration
        List<Path> result = generator.generateTests(trace, mockSourceFile, context);
        assertNotNull(result);
    }

    @Test
    void testGenerateTestsWithNullTrace() {
        LLMConfiguration config = LLMConfiguration.builder()
                .provider("mock")
                .modelName("test-model")
                .apiKey("test-key")
                .build();

        generator.configure(config);

        assertThrows(IllegalArgumentException.class, () ->
            generator.generateTests((Trace) null, mockSourceFile, context));
    }

    @Test
    void testGenerateTestsWithNullSourcePath() {
        LLMConfiguration config = LLMConfiguration.builder()
                .provider("mock")
                .modelName("test-model")
                .apiKey("test-key")
                .build();

        generator.configure(config);

        assertThrows(IllegalArgumentException.class, () ->
            generator.generateTests(trace, null, context));
    }

    @Test
    void testGenerateTestsWithDirectoryAsSourcePath() {
        LLMConfiguration config = LLMConfiguration.builder()
                .provider("mock")
                .modelName("test-model")
                .apiKey("test-key")
                .build();

        generator.configure(config);

        // Should throw exception when passing a directory instead of a file
        assertThrows(IllegalArgumentException.class, () ->
            generator.generateTests(trace, tempDir, context));
    }

    @Test
    void testGenerateTestsWithNullContext() {
        LLMConfiguration config = LLMConfiguration.builder()
                .provider("mock")
                .modelName("test-model")
                .apiKey("test-key")
                .build();

        generator.configure(config);

        assertThrows(IllegalArgumentException.class, () ->
            generator.generateTests(trace, mockSourceFile, null));
    }

    @Test
    void testDefaultConfiguration() {
        // Test that default configuration values are set correctly
        LLMConfiguration config = LLMConfiguration.builder()
                .apiKey("test-key")
                .build();

        assertEquals("anthropic", config.getProvider());
        assertEquals("claude-sonnet-4-20250514", config.getModelName());
        
        assertDoesNotThrow(() -> generator.configure(config));
    }
}
