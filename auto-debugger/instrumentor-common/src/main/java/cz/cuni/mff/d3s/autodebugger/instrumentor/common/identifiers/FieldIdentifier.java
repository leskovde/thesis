package cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.enums.ExportableValueType;
import lombok.Getter;

@Getter
public class FieldIdentifier extends ExportableIdentifier {
    private final String fieldName;
    private final ClassIdentifier ownerClassIdentifier;

    public FieldIdentifier(FieldIdentifierParameters parameters) {
        super(ExportableValueType.FIELD);
        this.fieldName = parameters.variableName;
        this.ownerClassIdentifier = parameters.ownerClassIdentifier;
        this.type = parameters.variableType;
    }

    @Override
    public String getName() {
        return fieldName;
    }
}
