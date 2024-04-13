package cz.cuni.mff.d3s.autodebugger.instrumentor.common.factories;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.Identifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.VariableIdentifier;

public class VariableIdentifierFactory extends IdentifierFactory {
    @Override
    public Identifier createIdentifier(String name) {
        return VariableIdentifier.builder()
                .variableName(name.substring(name.lastIndexOf(':') + 1))
                .variableType(name.substring(0, name.lastIndexOf(':')))
                .build();
    }
}
