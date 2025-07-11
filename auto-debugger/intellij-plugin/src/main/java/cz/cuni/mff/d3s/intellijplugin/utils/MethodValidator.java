package cz.cuni.mff.d3s.intellijplugin.utils;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.search.GlobalSearchScope;
import cz.cuni.mff.d3s.intellijplugin.model.MethodValidationResult;
import cz.cuni.mff.d3s.intellijplugin.model.ParsedMethodSignature;

/**
 * Testable method validation logic separated from UI concerns
 */
public class MethodValidator {
    private final Project project;
    private static final Logger LOG = Logger.getInstance(MethodValidator.class);

    public MethodValidator(Project project) {
        this.project = project;
    }

    /**
     * Validates a method signature and returns validation result
     */
    public MethodValidationResult validateMethodSignature(String signature) {
        LOG.debug("MethodValidator: Validating signature: '" + signature + "'");

        if (signature == null || signature.trim().isEmpty()) {
            return new MethodValidationResult(false, "Empty signature", null);
        }

        try {
            ParsedMethodSignature parsed = parseMethodSignature(signature.trim());
            if (parsed == null) {
                return new MethodValidationResult(false, "Invalid signature format", null);
            }

            PsiMethod method = findMethodByParsedSignature(parsed);
            if (method != null) {
                return new MethodValidationResult(true, "Method found", method);
            } else {
                return new MethodValidationResult(false, "Method not found", null);
            }
        } catch (Exception e) {
            LOG.warn("Error validating method signature: " + signature, e);
            return new MethodValidationResult(false, "Validation error: " + e.getMessage(), null);
        }
    }

    /**
     * Parses a method signature into components
     */
    public ParsedMethodSignature parseMethodSignature(String signature) {
        LOG.debug("Parsing method signature: '" + signature + "'");

        if (!signature.contains(".")) {
            LOG.debug("Invalid signature format: no dots found");
            return null;
        }

        int lastDot = signature.lastIndexOf('.');
        int openParen = signature.indexOf('(', lastDot);

        if (openParen == -1) {
            LOG.debug("Invalid signature format: no opening parenthesis found");
            return null;
        }

        String className = signature.substring(0, lastDot);
        String methodName = signature.substring(lastDot + 1, openParen);
        String parametersStr = signature.substring(openParen + 1, signature.lastIndexOf(')'));

        LOG.debug("Parsed signature - Class: '" + className + "', Method: '" + methodName + "', Parameters: '" + parametersStr + "'");

        return new ParsedMethodSignature(className, methodName, parametersStr, signature);
    }

    /**
     * Finds a method using parsed signature components
     */
    public PsiMethod findMethodByParsedSignature(ParsedMethodSignature parsed) {
        if (project == null) {
            LOG.debug("Project is null, cannot find method");
            return null;
        }

        // Find the class using PSI
        JavaPsiFacade facade = JavaPsiFacade.getInstance(project);
        GlobalSearchScope scope = GlobalSearchScope.allScope(project);
        PsiClass psiClass = facade.findClass(parsed.className, scope);

        if (psiClass == null) {
            LOG.debug("Class not found: " + parsed.className);
            return null;
        }

        LOG.debug("Found class: " + psiClass.getQualifiedName() + ", searching for method: " + parsed.methodName);

        // Find the method with matching name and parameters
        int methodCount = 0;
        for (PsiMethod method : psiClass.getAllMethods()) {
            methodCount++;
            if (method.getName().equals(parsed.methodName)) {
                String methodSignature = MethodUtils.buildMethodSignature(method);
                LOG.debug("Comparing signatures - Expected: '" + parsed.originalSignature + "', Found: '" + methodSignature + "'");
                if (methodSignature.equals(parsed.originalSignature)) {
                    LOG.debug("Method match found!");
                    return method;
                }
            }
        }

        LOG.debug("No matching method found. Searched " + methodCount + " methods in class " + parsed.className);
        return null;
    }
}
