package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.KlassClass;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor.ModelVisitor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class DiSLClass extends KlassClass {
    private JavaPackage classPackage;
    private List<JavaPackageImport> imports;

    {
        className = "DiSLClass";
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
