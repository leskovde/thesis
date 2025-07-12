package cz.cuni.mff.d3s.autodebugger.runner.model;

import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.MethodIdentifier;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.PackageIdentifier;
import cz.cuni.mff.d3s.autodebugger.model.java.parsing.JavaMethodSignatureParser;
import cz.cuni.mff.d3s.autodebugger.model.java.parsing.MethodSignature;
import cz.cuni.mff.d3s.autodebugger.model.java.parsing.SignatureState;
import cz.cuni.mff.d3s.autodebugger.runner.parsing.JavaMethodSignatureParsingStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MethodReferenceParserTest {

    private final JavaMethodSignatureParsingStrategy strategy = new JavaMethodSignatureParsingStrategy();

    @Test
    void testParseSimpleMethodReference() {
        String methodRef = "org.example.Main.main(String[])";
        MethodIdentifier methodIdentifier = strategy.parseMethodReference(methodRef);

        assertEquals("org.example", methodIdentifier.getOwnerClassIdentifier().getPackageIdentifier().getPackageName());
        assertEquals("Main", methodIdentifier.getOwnerClassIdentifier().getClassName());
        assertEquals("main", methodIdentifier.getMethodName());
        assertEquals(List.of("String[]"), methodIdentifier.getParameterTypes());
    }

    @Test
    void testParseMethodWithMultipleParameters() {
        String methodRef = "com.test.Calculator.add(int,int)";
        MethodIdentifier methodIdentifier = strategy.parseMethodReference(methodRef);

        assertEquals("com.test", methodIdentifier.getOwnerClassIdentifier().getPackageIdentifier().getPackageName());
        assertEquals("Calculator", methodIdentifier.getOwnerClassIdentifier().getClassName());
        assertEquals("add", methodIdentifier.getMethodName());
        assertEquals(List.of("int", "int"), methodIdentifier.getParameterTypes());
    }

    @Test
    void testParseMethodWithNoParameters() {
        String methodRef = "com.test.Service.initialize()";
        MethodIdentifier methodIdentifier = strategy.parseMethodReference(methodRef);

        assertEquals("com.test", methodIdentifier.getOwnerClassIdentifier().getPackageIdentifier().getPackageName());
        assertEquals("Service", methodIdentifier.getOwnerClassIdentifier().getClassName());
        assertEquals("initialize", methodIdentifier.getMethodName());
        assertTrue(methodIdentifier.getParameterTypes().isEmpty());
    }

    @Test
    void testParseMethodInDefaultPackage() {
        String methodRef = "Test.test(int)";
        MethodIdentifier methodIdentifier = strategy.parseMethodReference(methodRef);

        assertEquals("", methodIdentifier.getOwnerClassIdentifier().getPackageIdentifier().getPackageName());
        assertEquals(PackageIdentifier.DEFAULT_PACKAGE, methodIdentifier.getOwnerClassIdentifier().getPackageIdentifier());
        assertEquals("Test", methodIdentifier.getOwnerClassIdentifier().getClassName());
        assertEquals("test", methodIdentifier.getMethodName());
        assertEquals(List.of("int"), methodIdentifier.getParameterTypes());
    }



    @Test
    void testParseInvalidMethodReference() {
        assertThrows(IllegalArgumentException.class, () -> {
            strategy.parseMethodReference("invalid.format");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            strategy.parseMethodReference("missing.parentheses");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            strategy.parseMethodReference("");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            strategy.parseMethodReference(null);
        });
    }

    @Test
    void testComplexParameterTypes() {
        String methodRef = "org.example.Test.method(java.util.List<String>,int[],boolean)";
        MethodIdentifier methodIdentifier = strategy.parseMethodReference(methodRef);

        assertEquals(List.of("java.util.List<String>", "int[]", "boolean"),
                    methodIdentifier.getParameterTypes());
    }

    @Test
    void testJavaMethodSignatureParserDirectly() {
        // Test the underlying parser directly
        MethodSignature signature = JavaMethodSignatureParser.parseMethodSignature("org.example.Test.method(String,Object,List)");

        assertEquals("org.example.Test", signature.getClassName());
        assertEquals("method", signature.getMethodName());
        assertEquals("org.example", signature.getPackageName());
        assertEquals("Test", signature.getSimpleClassName());
        assertEquals(List.of("String", "Object", "List"), signature.getParameterTypes());
        assertEquals(SignatureState.FULL_METHOD, signature.getState());
        assertTrue(signature.isComplete());
        assertFalse(signature.isInDefaultPackage());
    }
}
