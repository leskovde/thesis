package cz.cuni.mff.d3s.autodebugger.instrumentor.common.factories;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.Identifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.PackageIdentifier;

public class PackageIdentifierFactory extends IdentifierFactory {
    @Override
    public Identifier createIdentifier(String name) {
        return PackageIdentifier.builder().packageName(name).build();
    }
}
