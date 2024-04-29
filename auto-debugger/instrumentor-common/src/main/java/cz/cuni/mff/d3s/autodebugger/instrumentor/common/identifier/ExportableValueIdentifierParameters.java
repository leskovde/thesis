package cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.enums.ExportableValueType;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class ExportableValueIdentifierParameters {
    @Getter
    protected ExportableValueType exportableType;
    public String variableName;
    public String variableType;

}

