package cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.*;

public interface ModelVisitor {
    void visit(Model visitable);
    void visit(Metaclass visitable);
    void visit(AnnotationClass visitable);
    void visit(InstrumentationLogicClass visitable);
    void visit(InstrumentationMarkerClass visitable);
    void visit(KlassClass visitable);
    void visit(PackageClass visitable);
    void visit(PackageImportClass visitable);
    void visit(ScopeClass visitable);
    void visit(VariableClass visitable);
}
