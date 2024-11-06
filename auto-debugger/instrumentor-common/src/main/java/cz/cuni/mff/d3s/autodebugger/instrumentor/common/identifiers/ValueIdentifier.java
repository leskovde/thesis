package cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.enums.ValueType;
import lombok.Getter;

@Getter
public abstract class ValueIdentifier implements Identifier {
    private final ValueType valueType;
    protected String type;

    protected ValueIdentifier(ValueType valueType) {
        this.valueType = valueType;
    }
}
