package cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.enums.ExportableValueType;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class VariableIdentifierParameters extends ExportableValueIdentifierParameters {
    {
        exportableType = ExportableValueType.VARIABLE;
    }
}
