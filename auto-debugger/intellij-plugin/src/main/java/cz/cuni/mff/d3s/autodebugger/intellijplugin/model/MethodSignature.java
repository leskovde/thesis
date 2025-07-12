package cz.cuni.mff.d3s.autodebugger.intellijplugin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Holds the parsed details of a method signature.
 */
@Getter
@AllArgsConstructor
public class MethodSignature {
    private final String className;
    private final String methodName;
    private final List<String> parameterTypes;
    private final String originalSignature;
    private final SignatureState state;
}