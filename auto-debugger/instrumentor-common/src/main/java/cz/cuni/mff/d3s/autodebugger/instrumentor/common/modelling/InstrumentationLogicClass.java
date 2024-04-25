package cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor.ModelVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public abstract class InstrumentationLogicClass extends Metaclass {
    protected List<? extends VariableClass> exports;

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
