package cz.cuni.mff.d3s.autodebugger.instrumentor.common.factories;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.Identifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.IdentifierParameters;

public abstract class IdentifierFactory {
    protected int id = 0;

    public static Identifier createFrom(IdentifierParameters parameters) {
        if (parameters.getMethodParameters().isPresent()) {
            return MethodIdentifierFactory.getInstance().createIdentifier(parameters.getMethodParameters().get());
        } else if (parameters.getPackageParameters().isPresent()) {
            return PackageIdentifierFactory.getInstance().createIdentifier(parameters.getPackageParameters().get());
        } else {
            return VariableIdentifierFactory.getInstance().createIdentifier(parameters.getVariableParameters().get());
        }
    }
}
