package cz.cuni.mff.d3s.autodebugger.model.java.identifiers;

import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.Identifier;
import lombok.AllArgsConstructor;
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
