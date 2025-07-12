package cz.cuni.mff.d3s.autodebugger.intellijplugin.utils;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;

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

    public static NameCategory categorizeMethodNamePrefix(String prefix) {
        if (prefix.isEmpty()) {
            return NameCategory.EMPTY;
        }
        NameCategory currentVariant = NameCategory.PACKAGE;
        for (int startIndex = 0, endIndex; startIndex != -1; startIndex = endIndex) {
            endIndex = prefix.indexOf('.', startIndex);
            String currentPart = prefix.substring(startIndex, endIndex);
            if (currentPart.isEmpty()) {
                break;
            }

            char initialChar = currentPart.charAt(0);
            if (initialChar >= 'a' && initialChar <= 'z') {
                currentVariant = switch (currentVariant) {
                    case PACKAGE -> NameCategory.PACKAGE;
                    case CLASS, METHOD ->  NameCategory.METHOD;
                    default -> NameCategory.EMPTY;
                };
            } else {
                currentVariant = switch (currentVariant) {
                    // Classes can have nested classes
                    case PACKAGE, CLASS -> NameCategory.CLASS;
                    case METHOD ->  NameCategory.METHOD;
                    default ->  NameCategory.EMPTY;
                };
            }
        }
        return currentVariant;
    }

    public enum NameCategory {
        EMPTY,
        PACKAGE,
        CLASS,
        METHOD
    }
}
