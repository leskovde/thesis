package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.Identifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Metaclass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JavaPackage extends Metaclass {
    protected Identifier packageIdentifier;

    @Override
    public String emitCode(int indentLevel) {
        append("package ");
        append(packageIdentifier.getName());
        append(";");
        return getCode();
    }
}
