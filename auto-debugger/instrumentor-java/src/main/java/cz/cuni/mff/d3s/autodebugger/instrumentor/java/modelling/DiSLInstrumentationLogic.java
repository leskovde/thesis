package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.Identifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.InstrumentationLogicClass;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.VariableClass;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor.ModelVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class DiSLInstrumentationLogic extends InstrumentationLogicClass {
    private final Identifier identifier;
    private final DiSLAnnotation annotation;

    public DiSLInstrumentationLogic(Identifier identifier, DiSLAnnotation annotation, List<? extends VariableClass> exports) {
        super(exports);
        this.identifier = identifier;
        this.annotation = annotation;
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
