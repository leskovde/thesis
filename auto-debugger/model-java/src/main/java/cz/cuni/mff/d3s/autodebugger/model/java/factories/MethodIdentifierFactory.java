package cz.cuni.mff.d3s.autodebugger.model.java.factories;

import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.MethodIdentifier;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.JavaMethodIdentifier;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.MethodIdentifierParameters;
import lombok.Getter;

public class MethodIdentifierFactory extends IdentifierFactory {
    @Getter
    private static final MethodIdentifierFactory instance = new MethodIdentifierFactory();

    private MethodIdentifierFactory() {
    }

    public MethodIdentifier createIdentifier(MethodIdentifierParameters parameters) {
        return new JavaMethodIdentifier(parameters);
    }

    public MethodIdentifier generateIdentifier(MethodIdentifierParameters parameters) {
        parameters.methodName = "generatedMethod" + getNextId();
        return new JavaMethodIdentifier(parameters);
    }
}
