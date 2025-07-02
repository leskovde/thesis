package cz.cuni.mff.d3s.intellijplugin;

import com.intellij.openapi.application.ReadAction;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiType;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase5;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the DebuggerToolWindowContent functionality including:
 * - Method validation and UI state management
 * - Field, parameter, and return value selection
 * - Checkbox state persistence
 */
public class DebuggerToolWindowContentTest extends LightJavaCodeInsightFixtureTestCase5 {

    private DebuggerToolWindowFactory.DebuggerToolWindowContent toolWindowContent;

    @BeforeEach
    void setUp() {
        // Create a mock tool window - we'll test the content functionality
        toolWindowContent = new DebuggerToolWindowFactory.DebuggerToolWindowContent(
                getFixture().getProject(), null);
    }

    /**
     * @return path to test data file directory relative to working directory in the run configuration for this test.
     */
    @Override
    protected String getTestDataPath() {
        return "src/test/testData";
    }

    @Test
    void testMethodValidationWithRealClass() {
        // Create a test class with various members
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

        // Test method validation
        var method = toolWindowContent.findMethodBySignature("com.example.TestClass.simpleMethod()");
        assertNotNull(method, "Should find simple method");
        assertEquals("simpleMethod", method.getName());

        var methodWithParams = toolWindowContent.findMethodBySignature("com.example.TestClass.methodWithParams(String, int)");
        assertNotNull(methodWithParams, "Should find method with parameters");
        assertEquals(2, methodWithParams.getParameterList().getParametersCount());

        var methodWithReturn = toolWindowContent.findMethodBySignature("com.example.TestClass.methodWithReturn()");
        assertNotNull(methodWithReturn, "Should find method with return value");
        assertNotNull(methodWithReturn.getReturnType());
        assertEquals("String", methodWithReturn.getReturnType().getPresentableText());
    }

    @Test
    void testFieldsPanelPopulation() {
        // Create a test class with fields
        var testClass = getFixture().addClass("""
            package com.example;
            public class FieldTestClass {
                private String privateField;
                public static final String CONSTANT = "test";
                protected int protectedField;
                public double publicField;
            }
            """);

        PsiField[] fields = ReadAction.compute(() -> testClass.getAllFields());
        assertTrue(fields.length > 0, "Should have fields");

        // Test that fields panel can be populated
        assertDoesNotThrow(() -> {
            toolWindowContent.populateFieldsPanel(fields);
        }, "Should populate fields panel without errors");

        // Test field selection state management
        Map<String, Boolean> fieldState = toolWindowContent.getFieldSelectionState();
        assertNotNull(fieldState, "Field selection state should be available");
    }

    @Test
    void testParametersPanelPopulation() {
        // Create a test class with a method that has parameters
        var testClass = getFixture().addClass("""
            package com.example;
            public class ParameterTestClass {
                public void methodWithParams(String str, int num, boolean flag) {}
            }
            """);

        PsiMethod[] methods = testClass.findMethodsByName("methodWithParams", false);
        assertTrue(methods.length > 0, "Should find method");

        PsiMethod method = methods[0];
        PsiParameter[] parameters = method.getParameterList().getParameters();
        assertEquals(3, parameters.length, "Should have 3 parameters");

        // Test that parameters panel can be populated
        assertDoesNotThrow(() -> {
            toolWindowContent.populateParametersPanel(parameters);
        }, "Should populate parameters panel without errors");

        // Test parameter selection state management
        Map<String, Boolean> paramState = toolWindowContent.getParameterSelectionState();
        assertNotNull(paramState, "Parameter selection state should be available");
    }

    @Test
    void testReturnValuePanelPopulation() {
        // Create a test class with a method that has a return value
        var testClass = getFixture().addClass("""
            package com.example;
            public class ReturnTestClass {
                public String methodWithReturn() { return "test"; }
                public void voidMethod() {}
            }
            """);

        PsiMethod[] methodsWithReturn = testClass.findMethodsByName("methodWithReturn", false);
        assertTrue(methodsWithReturn.length > 0, "Should find method with return");

        PsiMethod methodWithReturn = methodsWithReturn[0];
        PsiType returnType = methodWithReturn.getReturnType();
        assertNotNull(returnType, "Should have return type");

        // Test that return value panel can be populated
        assertDoesNotThrow(() -> {
            toolWindowContent.populateReturnValuePanel(returnType);
        }, "Should populate return value panel without errors");

        // Test return value selection state
        assertTrue(toolWindowContent.isReturnValueSelected(),
                "Return value should be selected by default");
    }

    @Test
    void testSelectionStatePersistence() {
        // Test that selection state can be cleared and managed
        toolWindowContent.clearSelectionState();

        Map<String, Boolean> fieldState = toolWindowContent.getFieldSelectionState();
        Map<String, Boolean> paramState = toolWindowContent.getParameterSelectionState();

        assertTrue(fieldState.isEmpty(), "Field state should be empty after clear");
        assertTrue(paramState.isEmpty(), "Parameter state should be empty after clear");
        assertTrue(toolWindowContent.isReturnValueSelected(),
                "Return value should be true by default after clear");
    }

    @Test
    void testInvalidMethodSignatures() {
        // Test various invalid method signature formats
        String[] invalidSignatures = {
                "",
                "methodName",
                "com.example.Class.method",
                "com.example.Class.method(",
                "com.example.Class.method)",
                "com.example.NonExistentClass.method()",
                "com.example.TestClass.nonExistentMethod()"
        };

        for (String signature : invalidSignatures) {
            var result = toolWindowContent.findMethodBySignature(signature);
            assertNull(result, "Invalid signature should return null: " + signature);
        }
    }
}