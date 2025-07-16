package cz.cuni.mff.d3s.autodebugger.model.java.identifiers;

import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.MethodIdentifier;

public class JavaMethodIdentifier extends MethodIdentifier {
    private final JavaClassIdentifier ownerClassIdentifier;

    public JavaMethodIdentifier(MethodIdentifierParameters parameters) {
        super(parameters.methodName, parameters.returnType, parameters.parameterTypes);
        this.ownerClassIdentifier = parameters.ownerClassIdentifier;
    }
}
