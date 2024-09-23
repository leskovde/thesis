package cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers;

import lombok.Getter;

@Getter
public class VariableIdentifier extends ExportableIdentifier {
    private final String variableName;
    private final String variableType;

    public VariableIdentifier(VariableIdentifierParameters parameters) {
        super(parameters.exportableType);
        this.variableName = parameters.variableName;
        this.variableType = parameters.variableType;
    }

    @Override
    public String getName() {
        return variableName;
    }

    public String getType() {
        return variableType;
    }
}
