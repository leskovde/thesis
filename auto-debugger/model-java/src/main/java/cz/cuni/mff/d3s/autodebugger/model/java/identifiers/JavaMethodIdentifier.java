package cz.cuni.mff.d3s.autodebugger.model.java.identifiers;

import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.MethodIdentifier;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class JavaMethodIdentifier extends MethodIdentifier implements Serializable {
    private final JavaClassIdentifier ownerClassIdentifier;

    public JavaMethodIdentifier(MethodIdentifierParameters parameters) {
        super(parameters.methodName, parameters.returnType, parameters.parameterTypes);
        this.ownerClassIdentifier = parameters.ownerClassIdentifier;
    }
}
