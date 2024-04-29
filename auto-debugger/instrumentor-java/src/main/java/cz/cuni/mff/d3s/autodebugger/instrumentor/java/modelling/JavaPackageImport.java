package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.Identifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Metaclass;
import lombok.Getter;

@Getter
public class JavaPackageImport extends Metaclass {
    protected JavaPackage importedPackage;

    public JavaPackageImport(Identifier identifier) {
        this.importedPackage = new JavaPackage(identifier);
    }

    @Override
    public String emitCode(int indentLevel) {
        append("import ");
        append(importedPackage.getPackageIdentifier().getName());
        append(";");
        return getCode();
    }
}
