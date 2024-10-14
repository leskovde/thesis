package cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.enums.ExportableValueType;
import lombok.Getter;

@Getter
public abstract class ExportableIdentifier implements Identifier {
    private final ExportableValueType exportableValueType;
    protected String type;

    protected ExportableIdentifier(ExportableValueType exportableValueType) {
        this.exportableValueType = exportableValueType;
    }
}
