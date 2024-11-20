package cz.cuni.mff.d3s.autodebugger.model.java.identifiers;

import cz.cuni.mff.d3s.autodebugger.model.java.enums.ValueType;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class VariableIdentifierParameters extends ExportableValueIdentifierParameters {
    {
        exportableType = ValueType.VARIABLE;
    }
}
