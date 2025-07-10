package cz.cuni.mff.d3s.intellijplugin;

/**
 * Data class for parsed method signature components
 */
public class ParsedMethodSignature {
    public final String className;
    public final String methodName;
    public final String parametersStr;
    public final String originalSignature;

    public ParsedMethodSignature(String className, String methodName, String parametersStr, String originalSignature) {
        this.className = className;
        this.methodName = methodName;
        this.parametersStr = parametersStr;
        this.originalSignature = originalSignature;
    }
}
