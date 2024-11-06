package cz.cuni.mff.d3s.autodebugger.instrumentor.java.factories;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.VariableIdentifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling.JavaVariable;
import lombok.Getter;

public class VariableExportFactory extends ExportableValueFactory {
    @Getter
    private static final VariableExportFactory instance = new VariableExportFactory();

    private VariableExportFactory() {
    }

    public JavaVariable createExportable(VariableIdentifier identifier) {
        // TODO: Get slot number
        throw new IllegalStateException("Not implemented");
    }
}
