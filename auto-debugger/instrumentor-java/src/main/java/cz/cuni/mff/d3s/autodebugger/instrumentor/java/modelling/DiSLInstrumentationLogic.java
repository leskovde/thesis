package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.Identifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.InstrumentationLogicClass;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor.ModelVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DiSLInstrumentationLogic extends InstrumentationLogicClass {
    private Identifier identifier;
    private DiSLAnnotation annotation;
    private List<JavaVariable> exports;

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
