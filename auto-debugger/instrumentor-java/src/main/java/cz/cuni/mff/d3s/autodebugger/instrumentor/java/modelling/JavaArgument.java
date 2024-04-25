package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.ArgumentClass;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor.ModelVisitor;

public class JavaArgument extends ArgumentClass {

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
