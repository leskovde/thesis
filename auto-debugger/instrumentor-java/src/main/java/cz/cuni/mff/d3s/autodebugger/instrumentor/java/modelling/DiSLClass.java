package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.KlassClass;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor.ModelVisitor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DiSLClass extends KlassClass {
    private JavaPackage classPackage;
    private List<JavaPackageImport> imports;
    private List<DiSLInstrumentationLogic> logic;
    public final String CLASS_NAME = "DiSLClass";

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
