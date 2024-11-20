package cz.cuni.mff.d3s.autodebugger.model.java.identifiers;

import cz.cuni.mff.d3s.autodebugger.model.java.enums.ValueType;
import lombok.Getter;

@Getter
public abstract class ValueIdentifier implements Identifier {
    private final ValueType valueType;
    protected String type;

    protected ValueIdentifier(ValueType valueType) {
        this.valueType = valueType;
    }
}
