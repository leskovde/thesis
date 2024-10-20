package cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PackageIdentifier implements Identifier {
    private final String packageName;
    public static final PackageIdentifier DEFAULT_PACKAGE = new PackageIdentifier("");

    @Override
    public String getName() {
        return packageName;
    }
}
