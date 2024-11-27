package cz.cuni.mff.d3s.autodebugger.model.java.identifiers;

import cz.cuni.mff.d3s.autodebugger.model.java.enums.ValueType;
import cz.cuni.mff.d3s.autodebugger.model.java.factories.IdentifierFactory;

public class ReturnValueIdentifier extends ValueIdentifier implements ExportableValue {
    private final int internalId;
    private final MethodIdentifier methodIdentifier;

    public ReturnValueIdentifier(ReturnValueIdentifierParameters parameters) {
        super(ValueType.RETURN_VALUE);
        this.methodIdentifier = parameters.methodIdentifier;
        this.internalId = IdentifierFactory.getNextId();
    }

    @Override
    public String getName() {
        return "return_" + methodIdentifier.getName();
    }

    @Override
    public int getInternalId() {
        return internalId;
    }

    @Override
    public String getType() {
        return type;
    }
}
