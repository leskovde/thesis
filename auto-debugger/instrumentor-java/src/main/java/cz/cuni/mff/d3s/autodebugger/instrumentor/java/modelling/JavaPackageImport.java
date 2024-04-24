package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.Identifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.PackageIdentifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.PackageImportClass;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor.ModelVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JavaPackageImport extends PackageImportClass {
    private JavaPackage importedPackage;

    public JavaPackageImport(Identifier identifier) {
        assert(identifier instanceof PackageIdentifier);
        this.importedPackage = new JavaPackage(identifier);
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
