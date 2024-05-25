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
        return PackageIdentifier.builder().packageName(parameters.packageName).build();
    }

    public PackageIdentifier generateIdentifier() {
        return PackageIdentifier.builder().packageName("generatedPackage" + id++).build();
    }
}
