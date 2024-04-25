package cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.Identifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor.ModelVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class PackageClass extends Metaclass {
    protected Identifier packageIdentifier;

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
