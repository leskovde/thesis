package cz.cuni.mff.d3s.autodebugger.model.java.identifiers;

import java.util.List;

import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.Identifier;
import lombok.Getter;

@Getter
public class MethodIdentifier implements Identifier {
    private final ClassIdentifier ownerClassIdentifier;
    private final String methodName;
    private final String returnType;
    private final List<String> parameterTypes;

    public MethodIdentifier(MethodIdentifierParameters parameters) {
        this.ownerClassIdentifier = parameters.ownerClassIdentifier;
        this.methodName = parameters.methodName;
        this.returnType = parameters.returnType;
        this.parameterTypes = parameters.parameterTypes;
    }

    public String getName() {
        return methodName;
    }
}
