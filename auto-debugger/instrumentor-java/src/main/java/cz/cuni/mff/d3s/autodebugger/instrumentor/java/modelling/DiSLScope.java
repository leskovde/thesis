package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.MethodIdentifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Metaclass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiSLScope extends Metaclass {
    private MethodIdentifier methodIdentifier;

    @Override
    public String emitCode() {
        append("scope = \"");
        append(methodIdentifier.getClassName());
        append(".");
        append(methodIdentifier.getName());
        append("\"");
        return getCode();
    }
}
