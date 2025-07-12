package cz.cuni.mff.d3s.autodebugger.intellijplugin.utils;

import cz.cuni.mff.d3s.autodebugger.intellijplugin.model.MethodSignature;
import cz.cuni.mff.d3s.autodebugger.intellijplugin.model.SignatureState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MethodUtilsTest {

    @Test
    public void givenBlankPrefix_whenCategorizingPrefix_thenReturnEmptyCategory() {
        String prefix = "";

        SignatureState state = MethodUtils.detectSignatureState(prefix);

        assertEquals(SignatureState.EMPTY, state);
    }

    @ParameterizedTest
    @ValueSource(strings = {"org", "org.", "org.example", "org.example."})
    public void givenPackagePrefix_whenCategorizingPrefix_thenReturnPackageCategory(String prefix) {
        SignatureState state = MethodUtils.detectSignatureState(prefix);

        assertEquals(SignatureState.PACKAGE_ONLY, state);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Test", "org.Test", "org.example.Test", "org.example.Test.", "org.example.Foo.Bar"})
    public void givenClassPrefix_whenCategorizingPrefix_thenReturnClassCategory(String prefix) {
        SignatureState state = MethodUtils.detectSignatureState(prefix);

        assertEquals(SignatureState.CLASS_ONLY, state);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Test.test", "org.Test.test", "org.example.Test.test", "org.example.Foo.Bar.test"})
    public void givenMethodPrefix_whenCategorizingPrefix_thenReturnMethodCategory(String prefix) {
        SignatureState state = MethodUtils.detectSignatureState(prefix);

        assertEquals(SignatureState.METHOD_NAME_ONLY, state);
    }
    
    @Test
    void givenNullOrBlankSignature_whenDetectingState_thenStateIsEmpty() {
        assertEquals(SignatureState.EMPTY, MethodUtils.detectSignatureState(null));
        assertEquals(SignatureState.EMPTY, MethodUtils.detectSignatureState(""));
        assertEquals(SignatureState.EMPTY, MethodUtils.detectSignatureState("   \t"));
    }

    @Test
    void givenPackageString_whenDetectingState_thenStateIsPackageOnly() {
        assertEquals(SignatureState.PACKAGE_ONLY,
                MethodUtils.detectSignatureState("com.example"));
        assertEquals(SignatureState.PACKAGE_ONLY,
                MethodUtils.detectSignatureState("org.apache.commons.lang"));
    }

    @Test
    void givenClassStringStartingWithUppercaseAndDot_whenDetectingState_thenStateIsClassOnly() {
        assertEquals(SignatureState.CLASS_ONLY,
                MethodUtils.detectSignatureState("MyClass"));
        assertEquals(SignatureState.CLASS_ONLY,
                MethodUtils.detectSignatureState("Utilities.Helper"));
    }

    @Test
    void givenSimpleMethodNameWithoutDot_whenDetectingState_thenStateIsMethodNameOnly() {
        assertEquals(SignatureState.METHOD_NAME_ONLY,
                MethodUtils.detectSignatureState("doWork"));
        assertEquals(SignatureState.METHOD_NAME_ONLY,
                MethodUtils.detectSignatureState("computeValue"));
    }

    @Test
    void givenMalformedSignatureMissingClosingParen_whenDetectingState_thenStateIsInvalid() {
        assertEquals(SignatureState.INVALID,
                MethodUtils.detectSignatureState("com.pkg.Class.method(int,string"));
    }

    @Test
    void givenSignatureWithParenthesesButNoQualifier_whenDetectingState_thenStateIsInvalid() {
        assertEquals(SignatureState.INVALID,
                MethodUtils.detectSignatureState("methodName()"));
    }

    @Test
    void givenWellFormedSignatureWithoutParams_whenDetectingState_thenStateIsFullMethod() {
        assertEquals(SignatureState.FULL_METHOD,
                MethodUtils.detectSignatureState("com.pkg.Class.method()"));
    }

    @Test
    void givenWellFormedSignatureWithParams_whenDetectingState_thenStateIsFullMethod() {
        assertEquals(SignatureState.FULL_METHOD,
                MethodUtils.detectSignatureState("MyClass.compute(int, String)"));
    }

    @Test
    void givenBeforeParamsString_whenExtractingClassAndMethod_thenReturnCorrectParts() {
        String before = "com.example.Test.doStuff";
        assertEquals("com.example.Test", MethodUtils.extractClassName(before));
        assertEquals("doStuff", MethodUtils.extractMethodName(before));
    }

    @Test
    void givenSignatureWithParams_whenExtractingParamsBody_thenReturnParamsBody() {
        String sig = "pkg.C.foo(int, List<String>)";
        assertEquals("int, List<String>", MethodUtils.extractParamsBody(sig));
    }

    @Test
    void givenEmptyParamsBody_whenParsingTypes_thenReturnEmptyList() {
        assertTrue(MethodUtils.parseParameterTypes(" ").isEmpty());
        assertTrue(MethodUtils.parseParameterTypes("").isEmpty());
    }

    @Test
    void givenSimpleParamsBody_whenParsingTypes_thenReturnCorrectList() {
        List<String> types = MethodUtils.parseParameterTypes("int, String,boolean");
        assertEquals(List.of("int", "String", "boolean"), types);
    }

    @Test
    void givenNestedGenericsParamsBody_whenParsingTypes_thenReturnCorrectList() {
        List<String> types = MethodUtils.parseParameterTypes(
                "Map<String, List<Integer>>, T[], List<Map<K, V>>"
        );
        assertEquals(List.of(
                "Map<String, List<Integer>>",
                "T[]",
                "List<Map<K, V>>"
        ), types);
    }

    @Test
    void givenSignatureWithoutParams_whenParsingFullSignature_thenReturnFullMethodNoParams() {
        MethodSignature ms = MethodUtils.parseMethodSignature("com.A.B.foo()");
        assertEquals(SignatureState.FULL_METHOD, ms.getState());
        assertEquals("com.A.B", ms.getClassName());
        assertEquals("foo", ms.getMethodName());
        assertTrue(ms.getParameterTypes().isEmpty());
    }

    @Test
    void givenSignatureWithParams_whenParsingFullSignature_thenReturnFullMethodWithParams() {
        String sig = "org.Example.doIt(int, List<String>)";
        MethodSignature ms = MethodUtils.parseMethodSignature(sig);
        assertEquals(SignatureState.FULL_METHOD, ms.getState());
        assertEquals("org.Example", ms.getClassName());
        assertEquals("doIt", ms.getMethodName());
        assertEquals(List.of("int", "List<String>"), ms.getParameterTypes());
        assertEquals(sig, ms.getOriginalSignature());
    }

    @Test
    void givenPackageOnlySignature_whenParsingSignature_thenReturnPackageOnlyState() {
        MethodSignature msPkg = MethodUtils.parseMethodSignature("com.only.pkg");
        assertEquals(SignatureState.PACKAGE_ONLY, msPkg.getState());
        assertEquals("com.only.pkg", msPkg.getClassName());
        assertTrue(msPkg.getMethodName().isEmpty());
    }

    @Test
    void givenMethodNameOnlySignature_whenParsingSignature_thenReturnMethodNameOnlyState() {
        MethodSignature msMth = MethodUtils.parseMethodSignature("doSomething");
        assertEquals(SignatureState.METHOD_NAME_ONLY, msMth.getState());
        assertEquals("doSomething", msMth.getMethodName());
        assertTrue(msMth.getClassName().isEmpty());
    }

    @Test
    void givenInvalidSignature_whenParsingSignature_thenReturnInvalidState() {
        MethodSignature ms = MethodUtils.parseMethodSignature("Class.method(int");
        assertEquals(SignatureState.INVALID, ms.getState());
        assertTrue(ms.getClassName().isEmpty());
        assertTrue(ms.getMethodName().isEmpty());
    }
}
