package cz.cuni.mff.d3s.autodebugger.model.java.identifiers;

import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.ExportableValue;
import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.Identifier;
import cz.cuni.mff.d3s.autodebugger.model.java.enums.ValueType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public abstract class JavaValueIdentifier implements Identifier, ExportableValue {
    private ValueType valueType;
    protected String type;

    protected JavaValueIdentifier(ValueType valueType) {
        this.valueType = valueType;
    }
}
