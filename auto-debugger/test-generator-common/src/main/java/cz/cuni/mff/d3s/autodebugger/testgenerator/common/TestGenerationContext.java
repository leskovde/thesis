package cz.cuni.mff.d3s.autodebugger.testgenerator.common;

import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.MethodIdentifier;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/**
 * Context information for test generation, providing additional metadata
 * and configuration options for different test generation strategies.
 */
@Builder
@Getter
public class TestGenerationContext {
    
    /**
     * Target method signature for which tests are being generated.
     * @deprecated Use {@link #targetMethod} instead for better type safety and accuracy.
     * This field will be removed in a future version.
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    private final String targetMethodSignature;

    /**
     * Fully qualified class name containing the target method.
     * @deprecated Use {@link #targetClass} instead for better type safety and accuracy.
     * This field will be removed in a future version.
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    private final String targetClassName;

    /**
     * Package name of the target class.
     * @deprecated Use {@link #targetMethod} instead for better type safety.
     * This field will be removed in a future version.
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    private final String packageName;

    /**
     * Structured identifier for the target method.
     * This provides type-safe access to method information without string parsing.
     * Preferred over the deprecated string-based fields.
     */
    private final MethodIdentifier targetMethod;
    
    /**
     * Output directory where generated tests should be placed.
     */
    private final Path outputDirectory;
    
    /**
     * Test framework to use (e.g., "junit5", "junit4", "testng").
     */
    @Builder.Default
    private final String testFramework = "junit5";
    
    /**
     * Additional classpath entries needed for test compilation.
     */
    @Singular
    private final List<Path> classpathEntries;
    
    /**
     * Environment variables that may affect test generation.
     */
    @Singular
    private final Map<String, String> environmentVariables;
    
    /**
     * Maximum number of tests to generate.
     */
    @Builder.Default
    private final int maxTestCount = 50;
    
    /**
     * Whether to generate edge case tests.
     */
    @Builder.Default
    private final boolean generateEdgeCases = true;
    
    /**
     * Whether to generate negative test cases (testing error conditions).
     */
    @Builder.Default
    private final boolean generateNegativeTests = true;
    
    /**
     * Test naming strategy to use.
     */
    @Builder.Default
    private final TestNamingStrategy namingStrategy = TestNamingStrategy.DESCRIPTIVE;
    
    /**
     * Additional metadata for test generation.
     */
    @Singular("metadataEntry")
    private final Map<String, Object> metadata;
    
    /**
     * Whether to include performance assertions in generated tests.
     */
    @Builder.Default
    private final boolean includePerformanceAssertions = false;
    
    /**
     * Timeout for individual test execution (in milliseconds).
     */
    @Builder.Default
    private final long testTimeoutMs = 5000;
    
    /**
     * Strategy for handling complex object creation in tests.
     */
    @Builder.Default
    private final ObjectCreationStrategy objectCreationStrategy = ObjectCreationStrategy.SIMPLE;
    
    /**
     * Whether to generate parameterized tests when applicable.
     */
    @Builder.Default
    private final boolean generateParameterizedTests = true;

    // Migration helper methods

    /**
     * Gets the target method signature, preferring structured identifier over deprecated string field.
     * This method provides backward compatibility during the migration period.
     *
     * @return The method signature, either from structured identifier or deprecated string field
     */
    public String getTargetMethodSignature() {
        if (targetMethod != null) {
            // Check if it's a Java method identifier with utility methods (using string comparison to avoid dependency)
            if ("JavaMethodIdentifier".equals(targetMethod.getClass().getSimpleName())) {
                try {
                    // Use reflection to call getFullyQualifiedSignature() to avoid dependency on model-java
                    java.lang.reflect.Method method = targetMethod.getClass().getMethod("getFullyQualifiedSignature");
                    return (String) method.invoke(targetMethod);
                } catch (Exception e) {
                    // Fallback to toString if reflection fails
                    return targetMethod.toString();
                }
            }
            return targetMethod.toString();
        }
        return targetMethodSignature;
    }

    /**
     * Gets the target class name, preferring structured identifier over deprecated string field.
     * This method provides backward compatibility during the migration period.
     *
     * @return The class name, either from structured identifier or deprecated string field
     */
    public String getTargetClassName() {
        if (targetMethod != null) {
            // Check if it's a Java method identifier with utility methods (using string comparison to avoid dependency)
            if ("JavaMethodIdentifier".equals(targetMethod.getClass().getSimpleName())) {
                try {
                    // Use reflection to call getFullyQualifiedClassName() to avoid dependency on model-java
                    java.lang.reflect.Method method = targetMethod.getClass().getMethod("getFullyQualifiedClassName");
                    return (String) method.invoke(targetMethod);
                } catch (Exception e) {
                    // Fallback to string parsing if reflection fails
                }
            }

            // Fallback: try to extract class name from method identifier string representation
            String methodStr = targetMethod.toString();
            int lastDot = methodStr.lastIndexOf('.');
            if (lastDot > 0) {
                int secondLastDot = methodStr.lastIndexOf('.', lastDot - 1);
                if (secondLastDot > 0) {
                    return methodStr.substring(0, lastDot);
                }
            }
        }
        return targetClassName;
    }

    /**
     * Gets the package name, preferring structured identifier over deprecated string field.
     * This method provides backward compatibility during the migration period.
     *
     * @return The package name, either from structured identifier or deprecated string field
     */
    public String getPackageName() {
        if (targetMethod != null) {
            // Check if it's a Java method identifier with utility methods (using string comparison to avoid dependency)
            if ("JavaMethodIdentifier".equals(targetMethod.getClass().getSimpleName())) {
                try {
                    // Use reflection to call getPackageName() to avoid dependency on model-java
                    java.lang.reflect.Method method = targetMethod.getClass().getMethod("getPackageName");
                    return (String) method.invoke(targetMethod);
                } catch (Exception e) {
                    // Fallback to string parsing if reflection fails
                }
            }

            // Fallback: try to extract package name from method identifier string representation
            String methodStr = targetMethod.toString();
            int lastDot = methodStr.lastIndexOf('.');
            if (lastDot > 0) {
                int secondLastDot = methodStr.lastIndexOf('.', lastDot - 1);
                if (secondLastDot > 0) {
                    String className = methodStr.substring(0, lastDot);
                    int classLastDot = className.lastIndexOf('.');
                    if (classLastDot > 0) {
                        return className.substring(0, classLastDot);
                    }
                }
            }
            return "";
        }
        return packageName != null ? packageName : "";
    }

    /**
     * Gets the structured method identifier.
     *
     * @return The method identifier, or null if not set
     */
    public MethodIdentifier getTargetMethod() {
        return targetMethod;
    }
}
