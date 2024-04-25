package cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Model;

public abstract class ModelToCodeVisitor implements ModelVisitor {
    protected final StringBuilder stringBuilder = new StringBuilder();
    protected int indentLevel = 0;

    @Override
    public void visit(Model visitable) {
        visitable.getRootClass().accept(this);
    }

    public abstract String getGeneratedCode();

    protected void append(String string) {
        assert(indentLevel >= 0);
        stringBuilder.append(string.replace("\n", "\n" + "\t".repeat(indentLevel)));
    }
}
