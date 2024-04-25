package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.FieldClass;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor.ModelVisitor;

public class JavaField extends FieldClass {

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
