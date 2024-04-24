package cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor.ModelVisitor;

public class AnnotationClass extends Metaclass {
    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
