package cz.cuni.mff.d3s.autodebugger.instrumentor.common.factories;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.PackageIdentifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.PackageIdentifierParameters;
import lombok.Getter;

public class PackageIdentifierFactory extends IdentifierFactory {
    @Getter
    private static final PackageIdentifierFactory instance = new PackageIdentifierFactory();

    private PackageIdentifierFactory() {
    }

    public PackageIdentifier createIdentifier(PackageIdentifierParameters parameters) {
        return new PackageIdentifier(parameters.packageName);
    }

    public PackageIdentifier generateIdentifier() {
        return new PackageIdentifier("generatedPackage" + id++);
    }
}
