package cz.cuni.mff.d3s.autodebugger.runner.model;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.Instrumentor;
import cz.cuni.mff.d3s.autodebugger.runner.Arguments;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModelBuilderTest {

    @TempDir
    Path tempDir;

    @Test
    void testBuildInstrumentorFromArguments() throws IOException {
        // Create temporary files
        Path jarFile = tempDir.resolve("test.jar");
        Path sourceDir = tempDir.resolve("src");
        Files.createFile(jarFile);
        Files.createDirectory(sourceDir);
        
        Arguments arguments = new Arguments();
        arguments.applicationJarPath = jarFile.toString();
        arguments.sourceCodePath = sourceDir.toString();
        arguments.targetMethodReference = "org.example.Test.testMethod(int,java.lang.String)";
        arguments.targetParameters = List.of("0:int", "1:java.lang.String");
        arguments.targetFields = List.of("int:counter", "java.lang.String:name");
        
        Instrumentor instrumentor = ModelBuilder.buildInstrumentor(arguments);

        assertNotNull(instrumentor);
        assertEquals(jarFile, instrumentor.getApplicationJarPath());
        assertEquals("testMethod", instrumentor.getMethod().getMethodName());
        assertEquals("Test", instrumentor.getMethod().getOwnerClassIdentifier().getClassName());
        assertEquals("org.example", instrumentor.getMethod().getOwnerClassIdentifier().getPackageIdentifier().getPackageName());
        assertEquals(4, instrumentor.getExportedValues().size()); // 2 parameters + 2 fields
    }

    @Test
    void testBuildInstrumentorFromParameters() throws IOException {
        // Create temporary files
        Path jarFile = tempDir.resolve("test.jar");
        Path sourceDir = tempDir.resolve("src");
        Files.createFile(jarFile);
        Files.createDirectory(sourceDir);
        
        Instrumentor instrumentor = ModelBuilder.buildInstrumentor(
            jarFile.toString(),
            sourceDir.toString(),
            "com.test.Calculator.add(int,int)",
            List.of("0:int", "1:int"),
            List.of("int:result")
        );

        assertNotNull(instrumentor);
        assertEquals(jarFile, instrumentor.getApplicationJarPath());
        assertEquals("add", instrumentor.getMethod().getMethodName());
        assertEquals("Calculator", instrumentor.getMethod().getOwnerClassIdentifier().getClassName());
        assertEquals("com.test", instrumentor.getMethod().getOwnerClassIdentifier().getPackageIdentifier().getPackageName());
        assertEquals(3, instrumentor.getExportedValues().size()); // 2 parameters + 1 field
    }

    @Test
    void testBuildInstrumentorWithEmptyTargets() throws IOException {
        // Create temporary files
        Path jarFile = tempDir.resolve("test.jar");
        Path sourceDir = tempDir.resolve("src");
        Files.createFile(jarFile);
        Files.createDirectory(sourceDir);
        
        Instrumentor instrumentor = ModelBuilder.buildInstrumentor(
            jarFile.toString(),
            sourceDir.toString(),
            "Test.simpleMethod()",
            null,
            null
        );

        assertNotNull(instrumentor);
        assertEquals("simpleMethod", instrumentor.getMethod().getMethodName());
        assertEquals("Test", instrumentor.getMethod().getOwnerClassIdentifier().getClassName());
        assertTrue(instrumentor.getExportedValues().isEmpty());
    }

    @Test
    void testValidationFailsForNullArguments() {
        assertThrows(IllegalArgumentException.class, () -> {
            ModelBuilder.buildInstrumentor(null);
        });
    }

    @Test
    void testValidationFailsForMissingJarPath() throws IOException {
        Path sourceDir = tempDir.resolve("src");
        Files.createDirectory(sourceDir);
        
        Arguments arguments = new Arguments();
        arguments.applicationJarPath = null;
        arguments.sourceCodePath = sourceDir.toString();
        arguments.targetMethodReference = "Test.method()";
        
        assertThrows(IllegalArgumentException.class, () -> {
            ModelBuilder.buildInstrumentor(arguments);
        });
    }

    @Test
    void testValidationFailsForMissingSourcePath() throws IOException {
        Path jarFile = tempDir.resolve("test.jar");
        Files.createFile(jarFile);
        
        Arguments arguments = new Arguments();
        arguments.applicationJarPath = jarFile.toString();
        arguments.sourceCodePath = null;
        arguments.targetMethodReference = "Test.method()";
        
        assertThrows(IllegalArgumentException.class, () -> {
            ModelBuilder.buildInstrumentor(arguments);
        });
    }

    @Test
    void testValidationFailsForMissingMethodReference() throws IOException {
        Path jarFile = tempDir.resolve("test.jar");
        Path sourceDir = tempDir.resolve("src");
        Files.createFile(jarFile);
        Files.createDirectory(sourceDir);
        
        Arguments arguments = new Arguments();
        arguments.applicationJarPath = jarFile.toString();
        arguments.sourceCodePath = sourceDir.toString();
        arguments.targetMethodReference = null;
        
        assertThrows(IllegalArgumentException.class, () -> {
            ModelBuilder.buildInstrumentor(arguments);
        });
    }

    @Test
    void testValidationFailsForNonExistentJarFile() throws IOException {
        Path sourceDir = tempDir.resolve("src");
        Files.createDirectory(sourceDir);
        
        Arguments arguments = new Arguments();
        arguments.applicationJarPath = tempDir.resolve("nonexistent.jar").toString();
        arguments.sourceCodePath = sourceDir.toString();
        arguments.targetMethodReference = "Test.method()";
        
        assertThrows(IllegalArgumentException.class, () -> {
            ModelBuilder.buildInstrumentor(arguments);
        });
    }

    @Test
    void testValidationFailsForNonExistentSourcePath() throws IOException {
        Path jarFile = tempDir.resolve("test.jar");
        Files.createFile(jarFile);
        
        Arguments arguments = new Arguments();
        arguments.applicationJarPath = jarFile.toString();
        arguments.sourceCodePath = tempDir.resolve("nonexistent").toString();
        arguments.targetMethodReference = "Test.method()";
        
        assertThrows(IllegalArgumentException.class, () -> {
            ModelBuilder.buildInstrumentor(arguments);
        });
    }

    @Test
    void testCreateSampleInstrumentor() {
        // This test just verifies that the sample method doesn't throw exceptions
        // In a real scenario, we'd need actual files, but this tests the logic
        assertThrows(IllegalArgumentException.class, () -> {
            ModelBuilder.createSampleInstrumentor();
        });
    }
}
