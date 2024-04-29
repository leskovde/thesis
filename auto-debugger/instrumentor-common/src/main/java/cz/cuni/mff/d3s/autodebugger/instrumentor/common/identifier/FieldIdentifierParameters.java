package cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.enums.ExportableValueType;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class FieldIdentifierParameters extends ExportableValueIdentifierParameters {
    {
        exportableType = ExportableValueType.FIELD;
    }
}
