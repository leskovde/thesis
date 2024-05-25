package cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PackageIdentifier implements Identifier {
    private final String packageName;

    @Override
    public String getName() {
        return packageName;
    }
}
