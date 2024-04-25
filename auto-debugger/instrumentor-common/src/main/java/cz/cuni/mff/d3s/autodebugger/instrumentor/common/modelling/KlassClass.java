package cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor.ModelVisitor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public abstract class KlassClass extends Metaclass {
    protected String className;
    protected List<? extends InstrumentationLogicClass> logic;

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}


