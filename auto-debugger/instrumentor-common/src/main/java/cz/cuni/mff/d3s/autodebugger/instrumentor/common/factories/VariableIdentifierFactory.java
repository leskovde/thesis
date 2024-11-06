package cz.cuni.mff.d3s.autodebugger.instrumentor.common.factories;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.VariableIdentifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.VariableIdentifierParameters;
import lombok.Getter;

public class VariableIdentifierFactory extends IdentifierFactory {
    @Getter
    private static final VariableIdentifierFactory instance = new VariableIdentifierFactory();

    private VariableIdentifierFactory() {
    }

    public VariableIdentifier createIdentifier(VariableIdentifierParameters parameters) {
//        return VariableIdentifier.builder()
//                .variableName(name.substring(name.lastIndexOf(':') + 1))
//                .variableType(name.substring(0, name.lastIndexOf(':')))
//                .build();
        return new VariableIdentifier(parameters);
    }

    public VariableIdentifier generateIdentifier(VariableIdentifierParameters parameters) {
        parameters.variableName = "generatedVariable" + getNextId();
        return new VariableIdentifier(parameters);
    }
}
