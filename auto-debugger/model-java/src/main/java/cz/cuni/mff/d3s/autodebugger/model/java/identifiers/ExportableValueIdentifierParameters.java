package cz.cuni.mff.d3s.autodebugger.model.java.identifiers;

import cz.cuni.mff.d3s.autodebugger.model.java.enums.ValueType;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class ExportableValueIdentifierParameters {
    @Getter
    protected ValueType exportableType;
    public String variableName;
    public String variableType;

}

