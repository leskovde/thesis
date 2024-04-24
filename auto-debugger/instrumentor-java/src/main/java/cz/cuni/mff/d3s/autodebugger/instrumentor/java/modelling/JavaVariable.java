package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.Identifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.VariableClass;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor.ModelVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JavaVariable extends VariableClass {
    private Identifier variableIdentifier;
    private String type;
    private int frameSlot;

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
