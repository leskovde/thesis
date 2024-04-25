package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.Identifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.PackageImportClass;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor.ModelVisitor;
import lombok.Getter;

@Getter
public class JavaPackageImport extends PackageImportClass {

    public JavaPackageImport(Identifier identifier) {
        super(new JavaPackage(identifier));
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
