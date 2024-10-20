package cz.cuni.mff.d3s.autodebugger.instrumentor.java.factories;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.FieldIdentifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling.JavaField;
import lombok.Getter;

public class FieldExportFactory extends ExportableValueFactory {
    @Getter
    private static final FieldExportFactory instance = new FieldExportFactory();

    private FieldExportFactory() {
    }

    public JavaField createExportable(FieldIdentifier identifier) {
        return new JavaField(identifier.getType(), identifier.getFieldName(), identifier.getOwnerClassIdentifier().getName());
    }
}
