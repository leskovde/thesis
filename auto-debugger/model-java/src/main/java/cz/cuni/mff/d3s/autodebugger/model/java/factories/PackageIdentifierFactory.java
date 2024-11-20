package cz.cuni.mff.d3s.autodebugger.model.java.factories;

import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.PackageIdentifier;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.PackageIdentifierParameters;
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
        return new PackageIdentifier("generatedPackage" + getNextId());
    }
}
