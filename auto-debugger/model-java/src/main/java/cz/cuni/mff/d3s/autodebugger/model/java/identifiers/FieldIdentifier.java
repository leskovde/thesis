package cz.cuni.mff.d3s.autodebugger.model.java.identifiers;

import cz.cuni.mff.d3s.autodebugger.model.java.enums.ValueType;
import cz.cuni.mff.d3s.autodebugger.model.java.factories.IdentifierFactory;
import lombok.Getter;

@Getter
public class FieldIdentifier extends ValueIdentifier implements ExportableValue {
    private final int internalId;
    private final String fieldName;
    private final ClassIdentifier ownerClassIdentifier;

    public FieldIdentifier(FieldIdentifierParameters parameters) {
        super(ValueType.FIELD);
        this.internalId = IdentifierFactory.getNextId();
        this.fieldName = parameters.variableName;
        this.ownerClassIdentifier = parameters.ownerClassIdentifier;
        this.type = parameters.variableType;
    }

    @Override
    public String getName() {
        return fieldName;
    }
}
