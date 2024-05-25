package cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers;

import java.util.List;

import lombok.Getter;

@Getter
public class MethodIdentifier implements Identifier {
    private final String className;
    private final String methodName;
    private final String returnType;
    private final List<String> parameterTypes;

    public MethodIdentifier(MethodIdentifierParameters parameters) {
        this.className = parameters.className;
        this.methodName = parameters.methodName;
        this.returnType = parameters.returnType;
        this.parameterTypes = parameters.parameterTypes;
    }

    public String getName() {
        return methodName;
    }
}
