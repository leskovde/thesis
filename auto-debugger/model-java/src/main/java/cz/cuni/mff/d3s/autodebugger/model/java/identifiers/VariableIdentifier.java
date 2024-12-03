package cz.cuni.mff.d3s.autodebugger.model.java.identifiers;

import cz.cuni.mff.d3s.autodebugger.model.java.enums.ValueType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VariableIdentifier extends ValueIdentifier {
    private String variableName;
    private String variableType;

    public VariableIdentifier(VariableIdentifierParameters parameters) {
        super(ValueType.VARIABLE);
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
