package cz.cuni.mff.d3s.intellijplugin.utils;

import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase5;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MethodValidatorTest extends LightJavaCodeInsightFixtureTestCase5 {

    private MethodValidator methodValidator;

    @BeforeEach
    void setUp() {
        methodValidator = new MethodValidator(getFixture().getProject());
    }

    /**
     * @return path to test data file directory relative to working directory in the run configuration for this test.
     */
    @Override
    protected String getTestDataPath() {
        return "src/test/testData";
    }

    @Test
    public void givenInstanceMethod_whenParsingMethodSignature_thenSignatureValid() {
        getFixture().addClass("""
            package com.example;
            public class TestClass {
                private String privateField;
                public static final String CONSTANT = "test";
                protected int protectedField;

                public void simpleMethod() {}
                public void methodWithParams(String str, int num) {}
                public String methodWithReturn() { return "test"; }
                private void privateMethod() {}
            }
            """);

        var result = methodValidator.parseMethodSignature("com.example.TestClass.simpleMethod()");
        assertNotNull(result);
        assertEquals("com.example.TestClass", result.className);
        assertEquals("simpleMethod", result.methodName);
        assertEquals("", result.parametersStr);
        assertEquals("com.example.TestClass.simpleMethod()", result.originalSignature);
    }

//
//    @Test
//    public void testParseValidMethodSignatures() {
//        // Test valid method signature parsing
//        var result = methodValidator.parseMethodSignature("com.example.MyClass.myMethod()");
//        assertNotNull(result);
//        assertEquals("com.example.MyClass", result.className);
//        assertEquals("myMethod", result.methodName);
//        assertEquals("", result.parametersStr);
//        assertEquals("com.example.MyClass.myMethod()", result.originalSignature);
//
//        // Test method with parameters
//        var resultWithParams = methodValidator.parseMethodSignature("com.example.MyClass.myMethod(String, int)");
//        assertNotNull(resultWithParams);
//        assertEquals("com.example.MyClass", resultWithParams.className);
//        assertEquals("myMethod", resultWithParams.methodName);
//        assertEquals("String, int", resultWithParams.parametersStr);
//    }
//
//    public void testParseInvalidMethodSignatures() {
//        // Test invalid signatures
//        assertNull(methodValidator.parseMethodSignature(""));
//        assertNull(methodValidator.parseMethodSignature("methodName"));
//        assertNull(methodValidator.parseMethodSignature("com.example.Class.method"));
//        assertNull(methodValidator.parseMethodSignature(null));
//    }
//
//    public void testValidateMethodSignatureWithNullProject() {
//        // Test validation with null project (should handle gracefully)
//        var result = methodValidator.validateMethodSignature("com.example.MyClass.myMethod()");
//        assertNotNull(result);
//        assertFalse(result.isValid);
//        assertNull(result.method);
//    }
//
//    public void testValidateEmptySignatures() {
//        // Test empty and null signatures
//        var emptyResult = methodValidator.validateMethodSignature("");
//        assertNotNull(emptyResult);
//        assertFalse(emptyResult.isValid);
//        assertEquals("Empty signature", emptyResult.message);
//
//        var nullResult = methodValidator.validateMethodSignature(null);
//        assertNotNull(nullResult);
//        assertFalse(nullResult.isValid);
//        assertEquals("Empty signature", nullResult.message);
//    }
//
//    public void testValidateInvalidSignatureFormats() {
//        // Test various invalid signature formats
//        String[] invalidSignatures = {
//            "methodName",
//            "com.example.Class.method",
//            "com.example.Class.method(",
//            "com.example.Class.method)",
//            ".method()",
//            "com..method()",
//            "com.example..method()"
//        };
//
//        for (String signature : invalidSignatures) {
//            var result = methodValidator.validateMethodSignature(signature);
//            assertNotNull(result);
//            assertFalse(result.isValid);
//            assertEquals("Invalid signature format", result.message);
//        }
//    }
//
//    public void testParsedMethodSignatureDataClass() {
//        // Test the ParsedMethodSignature data class
//        var parsed = new DebuggerToolWindowFactory.ParsedMethodSignature(
//            "com.example.MyClass",
//            "myMethod",
//            "String, int",
//            "com.example.MyClass.myMethod(String, int)"
//        );
//
//        assertEquals("com.example.MyClass", parsed.className);
//        assertEquals("myMethod", parsed.methodName);
//        assertEquals("String, int", parsed.parametersStr);
//        assertEquals("com.example.MyClass.myMethod(String, int)", parsed.originalSignature);
//    }
//
//    public void testMethodValidationResultDataClass() {
//        // Test the MethodValidationResult data class
//        var validResult = new DebuggerToolWindowFactory.MethodValidationResult(true, "Success", null);
//        assertTrue(validResult.isValid);
//        assertEquals("Success", validResult.message);
//        assertNull(validResult.method);
//
//        var invalidResult = new DebuggerToolWindowFactory.MethodValidationResult(false, "Error", null);
//        assertFalse(invalidResult.isValid);
//        assertEquals("Error", invalidResult.message);
//        assertNull(invalidResult.method);
//    }
//
//    public void testComplexMethodSignatures() {
//        // Test parsing of complex method signatures
//        var complexSignature = "com.example.service.UserService.findUsersByAgeAndStatus(int, String, boolean)";
//        var result = methodValidator.parseMethodSignature(complexSignature);
//
//        assertNotNull(result);
//        assertEquals("com.example.service.UserService", result.className);
//        assertEquals("findUsersByAgeAndStatus", result.methodName);
//        assertEquals("int, String, boolean", result.parametersStr);
//
//        // Test nested class signature
//        var nestedSignature = "com.example.OuterClass$InnerClass.innerMethod()";
//        var nestedResult = methodValidator.parseMethodSignature(nestedSignature);
//
//        assertNotNull(nestedResult);
//        assertEquals("com.example.OuterClass$InnerClass", nestedResult.className);
//        assertEquals("innerMethod", nestedResult.methodName);
//        assertEquals("", nestedResult.parametersStr);
//    }
//
//    public void testValidatorInstantiation() {
//        // Test that validator can be instantiated with different project states
//        var validatorWithNull = new DebuggerToolWindowFactory.MethodValidator(null);
//        assertNotNull(validatorWithNull);
//
//        // Test basic functionality doesn't throw exceptions
//        try {
//            validatorWithNull.parseMethodSignature("com.example.Test.method()");
//            validatorWithNull.validateMethodSignature("com.example.Test.method()");
//            assertTrue(true); // Should reach this point without exceptions
//        } catch (Exception e) {
//            fail("Should not throw exceptions: " + e.getMessage());
//        }
//    }
}
