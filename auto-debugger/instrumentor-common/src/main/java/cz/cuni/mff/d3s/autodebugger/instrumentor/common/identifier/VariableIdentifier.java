package cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
// TODO: Scopes? Fields?
public class VariableIdentifier implements Identifier {
    private final String variableName;
    private final String variableType;

    @Override
    public String getName() {
        return variableName;
    }

    public String getType() {
        return variableType;
    }
}
