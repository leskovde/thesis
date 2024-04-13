package cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Metaclass;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Model;

public abstract class ModelToCodeVisitor implements ModelVisitor {
    protected final StringBuilder stringBuilder = new StringBuilder();

    @Override
    public void visit(Model visitable) {
        visit(visitable.getRootClass());
    }

    @Override
    public void visit(Metaclass visitable) {
        visitable.accept(this);
    }

    public String getGeneratedCode() {
        return stringBuilder.toString();
    }

    protected void append(String string) {
        stringBuilder.append(string);
    }
}
