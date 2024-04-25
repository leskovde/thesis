package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.Identifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.PackageClass;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor.ModelVisitor;
import lombok.Getter;

@Getter
public class JavaPackage extends PackageClass {

    public JavaPackage(Identifier packageIdentifier) {
        super(packageIdentifier);
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
