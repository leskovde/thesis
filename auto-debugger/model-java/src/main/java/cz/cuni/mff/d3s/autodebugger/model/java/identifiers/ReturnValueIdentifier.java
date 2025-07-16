package cz.cuni.mff.d3s.autodebugger.model.java.identifiers;

import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.ExportableValue;
import cz.cuni.mff.d3s.autodebugger.model.java.enums.ValueType;
import cz.cuni.mff.d3s.autodebugger.model.java.factories.IdentifierFactory;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReturnValueIdentifier extends ValueIdentifier implements ExportableValue {
    private int internalId;
    private MethodIdentifier methodIdentifier;

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
