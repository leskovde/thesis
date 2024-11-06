package cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.enums.ValueType;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class ExportableValueIdentifierParameters {
    @Getter
    protected ValueType exportableType;
    public String variableName;
    public String variableType;

}

