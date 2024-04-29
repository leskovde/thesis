package cz.cuni.mff.d3s.autodebugger.instrumentor.java.factories;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.VariableIdentifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling.JavaField;
import lombok.Getter;

public class FieldExportFactory extends ExportableValueFactory {
    @Getter
    private static final FieldExportFactory instance = new FieldExportFactory();

    private FieldExportFactory() {
    }

    public JavaField createExportable(VariableIdentifier identifier) {
        // TODO: Add owner type to VariableIdentifier
        return new JavaField(identifier.getType(), identifier.getName(), "TODO");
    }
}
