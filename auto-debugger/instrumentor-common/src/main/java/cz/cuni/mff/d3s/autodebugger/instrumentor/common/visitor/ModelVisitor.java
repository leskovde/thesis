package cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Metaclass;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Model;

public interface ModelVisitor {
    void visit(Model visitable);
    void visit(Metaclass visitable);
}
