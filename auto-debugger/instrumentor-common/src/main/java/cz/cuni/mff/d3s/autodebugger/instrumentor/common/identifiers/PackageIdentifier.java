package cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PackageIdentifier implements Identifier {
    private final String packageName;

    @Override
    public String getName() {
        return packageName;
    }
}
