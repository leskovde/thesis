package cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.enums.ExportableValueType;
import lombok.Getter;

@Getter
public class VariableIdentifier implements Identifier {
    private final String variableName;
    private final String variableType;
    private final ExportableValueType exportableValueType;

    public VariableIdentifier(VariableIdentifierParameters parameters) {
        this.variableName = parameters.variableName;
        this.variableType = parameters.variableType;
        this.exportableValueType = parameters.getExportableType();
    }

    @Override
    public String getName() {
        return variableName;
    }

    public String getType() {
        return variableType;
    }
}
