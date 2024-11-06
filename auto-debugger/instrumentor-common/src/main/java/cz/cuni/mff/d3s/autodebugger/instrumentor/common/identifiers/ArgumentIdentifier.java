package cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.enums.ValueType;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.factories.IdentifierFactory;
import lombok.Getter;

@Getter
public class ArgumentIdentifier extends ValueIdentifier implements ExportableValue {
    private final int internalId;
    private final int argumentSlot;

    public ArgumentIdentifier(ArgumentIdentifierParameters parameters) {
        super(ValueType.ARGUMENT);
        this.internalId = IdentifierFactory.getNextId();
        this.argumentSlot = parameters.argumentSlot;
        this.type = parameters.variableType;
    }

    @Override
    public String getName() {
        return type + "_" + argumentSlot;
    }
}
