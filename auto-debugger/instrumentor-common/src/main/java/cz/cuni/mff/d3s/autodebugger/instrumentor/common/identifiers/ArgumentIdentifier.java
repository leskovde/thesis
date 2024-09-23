package cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.enums.ExportableValueType;
import lombok.Getter;

@Getter
public class ArgumentIdentifier extends ExportableIdentifier {
    private final int argumentSlot;
    private final String argumentType;

    public ArgumentIdentifier(ArgumentIdentifierParameters parameters) {
        super(ExportableValueType.ARGUMENT);
        this.argumentSlot = parameters.argumentSlot;
        this.argumentType = parameters.variableType;
    }

    @Override
    public String getName() {
        return argumentType + "_" + argumentSlot;
    }
}
