package cz.cuni.mff.d3s.autodebugger.intellijplugin.utils;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;
import cz.cuni.mff.d3s.autodebugger.intellijplugin.model.MethodSignature;
import cz.cuni.mff.d3s.autodebugger.intellijplugin.model.SignatureState;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MethodUtils {

    public static String buildMethodSignature(PsiMethod method) {
        StringBuilder signature = new StringBuilder();

        // Add class name
        PsiClass containingClass = method.getContainingClass();
        if (containingClass != null) {
            signature.append(containingClass.getQualifiedName()).append(".");
        }

        // Add method name
        signature.append(method.getName());

        // Add parameters
        signature.append("(");
        PsiParameter[] parameters = method.getParameterList().getParameters();
        for (int i = 0; i < parameters.length; i++) {
            if (i > 0) signature.append(", ");
            signature.append(parameters[i].getType().getPresentableText());
        }
        signature.append(")");

        return signature.toString();
    }

    public static boolean isFullyQualifiedClassName(@NotNull String prefix) {
        // Check if this looks like a fully qualified class name (has dots and ends with a class-like name)
        if (!prefix.contains(".")) return false;

        int lastDot = prefix.lastIndexOf('.');
        String lastPart = prefix.substring(lastDot + 1);

        // If the last part starts with uppercase, it's likely a class name
        return !lastPart.isEmpty() && Character.isUpperCase(lastPart.charAt(0));
    }

    public static MethodSignature parseMethodSignature(String signature) {
        SignatureState state = detectSignatureState(signature);
        if (state != SignatureState.FULL_METHOD) {
            // Return a minimal object for non-full signatures
            return new MethodSignature(
                    state == SignatureState.CLASS_ONLY || state == SignatureState.PACKAGE_ONLY
                            ? signature
                            : "",
                    state == SignatureState.METHOD_NAME_ONLY
                            ? signature
                            : "",
                    Collections.emptyList(),
                    signature,
                    state
            );
        }

        String sig = signature.trim();
        String beforeParams = sig.substring(0, sig.indexOf('(')).trim();
        String paramsBody = extractParamsBody(sig);

        String className = extractClassName(beforeParams);
        String methodName = extractMethodName(beforeParams);
        List<String> paramTypes = parseParameterTypes(paramsBody);

        return new MethodSignature(
                className,
                methodName,
                paramTypes,
                signature,
                SignatureState.FULL_METHOD
        );
    }

    /**
     * Utility to determine the SignatureState of a given signature string.
     */
    public static SignatureState detectSignatureState(String sig) {
        if (sig == null || sig.trim().isEmpty()) {
            return SignatureState.EMPTY;
        }
        String trimmed = sig.trim();
        int open = trimmed.indexOf('(');
        int close = trimmed.lastIndexOf(')');
        boolean hasParen = (open >= 0 && close > open);

        if (!hasParen) {
            if (trimmed.contains(".")) {
                String first = trimmed.substring(0, trimmed.indexOf('.'));
                return Character.isLowerCase(first.charAt(0))
                        ? SignatureState.PACKAGE_ONLY
                        : SignatureState.CLASS_ONLY;
            }
            return SignatureState.METHOD_NAME_ONLY;
        }

        if (close != trimmed.length() - 1) {
            return SignatureState.INVALID;
        }
        String beforeParams = trimmed.substring(0, open);
        if (!beforeParams.contains(".")) {
            return SignatureState.INVALID;
        }
        return SignatureState.FULL_METHOD;
    }

    public static String extractClassName(String beforeParams) {
        int dot = beforeParams.lastIndexOf('.');
        return beforeParams.substring(0, dot);
    }

    public static String extractMethodName(String beforeParams) {
        int dot = beforeParams.lastIndexOf('.');
        return beforeParams.substring(dot + 1);
    }

    public static String extractParamsBody(String sig) {
        int open = sig.indexOf('(');
        int close = sig.lastIndexOf(')');
        return sig.substring(open + 1, close).trim();
    }

    /**
     * Utility to parse a parameter list string into a list of type strings,
     * correctly handling nested generics by tracking angle-bracket depth.
     */
    public static List<String> parseParameterTypes(String paramsBody) {
        if (paramsBody == null || paramsBody.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> types = new ArrayList<>();
        StringBuilder token = new StringBuilder();
        int depth = 0;
        for (int i = 0; i < paramsBody.length(); i++) {
            char c = paramsBody.charAt(i);
            if (c == '<') {
                depth++;
                token.append(c);
            } else if (c == '>') {
                depth--;
                token.append(c);
            } else if (c == ',' && depth == 0) {
                String t = token.toString().trim();
                if (!t.isEmpty()) {
                    types.add(t);
                }
                token.setLength(0);
            } else {
                token.append(c);
            }
        }
        String last = token.toString().trim();
        if (!last.isEmpty()) {
            types.add(last);
        }
        return types;
    }
}
