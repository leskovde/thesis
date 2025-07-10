package cz.cuni.mff.d3s.intellijplugin;

import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import com.intellij.psi.search.searches.AllClassesSearch;
import com.intellij.util.Query;
import com.intellij.util.TextFieldCompletionProvider;
import org.jetbrains.annotations.NotNull;

/**
 * PSI-based completion provider for method signatures with three-stage completion:
 * 1. Package name completion
 * 2. Class name completion (with package)
 * 3. Method name completion from all matching classes
 */
public class MethodCompletionProvider extends TextFieldCompletionProvider {
    private static final Logger LOG = Logger.getInstance(MethodValidator.class);

    private final Project project;

    public MethodCompletionProvider(Project project) {
        this.project = project;
    }

    @Override
    protected void addCompletionVariants(@NotNull String text, int offset, @NotNull String prefix,
                                         @NotNull CompletionResultSet result) {

        if (prefix.isEmpty()) {
            // Show examples for empty input
            addExamplePatterns(result);
            return;
        }

        // TODO: looking for a method after typing a package name does not work properly
        // Determine completion type based on input pattern
        if (prefix.contains(".")) {
            if (isFullyQualifiedClassName(prefix)) {
                // Case 3: Method name completion - suggest methods from the specified class
                addMethodCompletions(prefix, result);
            } else {
                // Case 1: Package name completion or Case 2: Class completion within package
                addPackageAndClassCompletions(prefix, result);
            }
        } else {
            // Case 2: Class name completion (without package) - suggest full class names
            // Case 3: Method name completion - suggest methods from all classes with matching method names
            addClassNameCompletions(prefix, result);
            addMethodNameCompletions(prefix, result);
        }
    }

    private boolean isFullyQualifiedClassName(@NotNull String prefix) {
        // Check if this looks like a fully qualified class name (has dots and ends with a class-like name)
        if (!prefix.contains(".")) return false;

        int lastDot = prefix.lastIndexOf('.');
        String lastPart = prefix.substring(lastDot + 1);

        // If the last part starts with uppercase, it's likely a class name
        return !lastPart.isEmpty() && Character.isUpperCase(lastPart.charAt(0));
    }

    // TODO: Remove this
    private void addExamplePatterns(@NotNull CompletionResultSet result) {
        result.addElement(LookupElementBuilder.create("com.example.MyClass.myMethod()")
                .withTypeText("Example pattern"));
        result.addElement(LookupElementBuilder.create("java.lang.String.toString()")
                .withTypeText("Example pattern"));
        result.addElement(LookupElementBuilder.create("MyClass")
                .withTypeText("Type class name"));
        result.addElement(LookupElementBuilder.create("myMethod")
                .withTypeText("Type method name"));
    }

    // Case 1: Package name completion and Case 2: Class completion within package
    private void addPackageAndClassCompletions(@NotNull String prefix, @NotNull CompletionResultSet result) {
        try {
            GlobalSearchScope scope = GlobalSearchScope.projectScope(project);
            Query<PsiClass> query = AllClassesSearch.search(scope, project);

            query.forEach(psiClass -> {
                String qualifiedName = psiClass.getQualifiedName();
                if (qualifiedName != null && qualifiedName.toLowerCase().startsWith(prefix.toLowerCase())) {
                    result.addElement(LookupElementBuilder.create(qualifiedName)
                            .withIcon(psiClass.getIcon(0))
                            .withTypeText("Class"));
                }
                return true;
            });
        } catch (Exception e) {
            LOG.debug("Failed to access PSI for package/class completion", e);
        }
    }

    // Case 2: Class name completion (without package) - suggest full class names
    private void addClassNameCompletions(@NotNull String prefix, @NotNull CompletionResultSet result) {
        try {
            PsiShortNamesCache cache = PsiShortNamesCache.getInstance(project);
            GlobalSearchScope scope = GlobalSearchScope.projectScope(project);

            String[] allClassNames = cache.getAllClassNames();
            for (String className : allClassNames) {
                if (className.toLowerCase().contains(prefix.toLowerCase())) {
                    PsiClass[] classes = cache.getClassesByName(className, scope);
                    for (PsiClass psiClass : classes) {
                        String qualifiedName = psiClass.getQualifiedName();
                        if (qualifiedName != null) {
                            result.addElement(LookupElementBuilder.create(qualifiedName)
                                    .withIcon(psiClass.getIcon(0))
                                    .withTypeText("Class")
                                    .withLookupString(className)); // Allow matching by short name
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOG.debug("Failed to access PSI for class name completion", e);
        }
    }

    // Case 3: Method name completion - suggest methods from all classes with matching method names
    private void addMethodNameCompletions(@NotNull String prefix, @NotNull CompletionResultSet result) {
        try {
            GlobalSearchScope scope = GlobalSearchScope.projectScope(project);
            Query<PsiClass> query = AllClassesSearch.search(scope, project);

            query.forEach(psiClass -> {
                PsiMethod[] methods = psiClass.getAllMethods();
                for (PsiMethod method : methods) {
                    if (method.getName().toLowerCase().contains(prefix.toLowerCase())) {
                        String methodSignature = buildMethodSignature(method);
                        result.addElement(LookupElementBuilder.create(methodSignature)
                                .withIcon(method.getIcon(0))
                                .withTypeText(method.getReturnType() != null ?
                                        method.getReturnType().getPresentableText() : "void")
                                .withTailText(" (" + (method.getContainingClass() != null ?
                                        method.getContainingClass().getName() : "Unknown") + ")")
                                .withLookupString(method.getName())); // Allow matching by method name
                    }
                }
                return true;
            });
        } catch (Exception e) {
            LOG.debug("Failed to access PSI for method name completion", e);
        }
    }

    // Case 3: Method completion for fully qualified class names
    private void addMethodCompletions(@NotNull String prefix, @NotNull CompletionResultSet result) {
        try {
            int lastDot = prefix.lastIndexOf('.');
            String className = prefix.substring(0, lastDot);
            String methodPrefix = prefix.substring(lastDot + 1);

            // Find the class using PSI
            JavaPsiFacade facade = JavaPsiFacade.getInstance(project);
            GlobalSearchScope scope = GlobalSearchScope.projectScope(project);
            PsiClass psiClass = facade.findClass(className, scope);

            if (psiClass != null) {
                // Get all methods from the class
                PsiMethod[] methods = psiClass.getAllMethods();
                for (PsiMethod method : methods) {
                    if (method.getName().toLowerCase().startsWith(methodPrefix.toLowerCase())) {
                        String methodSignature = buildMethodSignature(method);
                        result.addElement(LookupElementBuilder.create(methodSignature)
                                .withIcon(method.getIcon(0))
                                .withTypeText(method.getReturnType() != null ?
                                        method.getReturnType().getPresentableText() : "void")
                                .withTailText(" (" + (method.getContainingClass() != null ?
                                        method.getContainingClass().getName() : "Unknown") + ")"));
                    }
                }
            }
        } catch (Exception e) {
            LOG.debug("Failed to access PSI for method completion", e);
        }
    }

    private String buildMethodSignature(PsiMethod method) {
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

    public PsiMethod findMethodBySignature(String signature) {
        try {
            LOG.debug("MethodCompletionProvider: Parsing method signature: '" + signature + "'");

            // Parse the method signature to extract class and method information
            if (!signature.contains(".")) {
                LOG.debug("Invalid signature format: no dots found");
                return null; // Invalid format
            }

            int lastDot = signature.lastIndexOf('.');
            int openParen = signature.indexOf('(', lastDot);

            if (openParen == -1) {
                LOG.debug("Invalid signature format: no opening parenthesis found");
                return null; // No parameters specified
            }

            String className = signature.substring(0, lastDot);
            String methodName = signature.substring(lastDot + 1, openParen);
            String parametersStr = signature.substring(openParen + 1, signature.lastIndexOf(')'));

            LOG.debug("Parsed signature - Class: '" + className + "', Method: '" + methodName + "', Parameters: '" + parametersStr + "'");

            // Find the class using PSI
            JavaPsiFacade facade = JavaPsiFacade.getInstance(project);
            GlobalSearchScope scope = GlobalSearchScope.allScope(project);
            PsiClass psiClass = facade.findClass(className, scope);

            if (psiClass == null) {
                LOG.debug("Class not found: " + className);
                return null;
            }

            LOG.debug("Found class: " + psiClass.getQualifiedName() + ", searching for method: " + methodName);

            // Find the method with matching name and parameters
            int methodCount = 0;
            for (PsiMethod method : psiClass.getAllMethods()) {
                methodCount++;
                if (method.getName().equals(methodName)) {
                    String methodSignature = buildMethodSignature(method);
                    LOG.debug("Comparing signatures - Expected: '" + signature + "', Found: '" + methodSignature + "'");
                    if (methodSignature.equals(signature)) {
                        LOG.debug("Method match found!");
                        return method;
                    }
                }
            }

            LOG.debug("No matching method found. Searched " + methodCount + " methods in class " + className);
            return null;
        } catch (Exception e) {
            LOG.warn("Error parsing method signature: " + signature, e);
            return null;
        }
    }
}

