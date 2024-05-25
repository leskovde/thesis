package cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class PackageIdentifierParameters {
    public String packageName;
}
