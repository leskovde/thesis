package cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.enums.ExportableValueType;
import lombok.Getter;

@Getter
public class FieldIdentifier extends ExportableIdentifier {
    private final String name;
    private final String ownerType;

    public FieldIdentifier(FieldIdentifierParameters parameters) {
        super(ExportableValueType.FIELD);
        this.name = parameters.variableName;
        this.ownerType = parameters.ownerType;
        this.type = parameters.variableType;
    }

    @Override
    public String getName() {
        return name;
    }
}
