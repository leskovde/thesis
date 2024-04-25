package cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor.ModelVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class PackageImportClass extends Metaclass {
    protected PackageClass importedPackage;

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
