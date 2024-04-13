package cz.cuni.mff.d3s.autodebugger.instrumentor.common.factories;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.Identifier;

public abstract class IdentifierFactory {
    public abstract Identifier createIdentifier(String name);

    public static Identifier createFrom(String name) {
        if (name.contains("(")) {
            return new MethodIdentifierFactory().createIdentifier(name);
        } else if (name.contains(".")) {
            return new PackageIdentifierFactory().createIdentifier(name);
        } else {
            return new VariableIdentifierFactory().createIdentifier(name);
        }
    }
}
