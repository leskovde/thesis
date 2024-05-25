package cz.cuni.mff.d3s.autodebugger.instrumentor.java.factories;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.VariableIdentifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling.ExportableValue;

public abstract class ExportableValueFactory {

    public static ExportableValue createFrom(VariableIdentifier identifier) {
        return switch (identifier.getExportableValueType()) {
            case ARGUMENT -> ArgumentExportFactory.getInstance().createExportable(identifier);
            case FIELD -> FieldExportFactory.getInstance().createExportable(identifier);
            case VARIABLE -> VariableExportFactory.getInstance().createExportable(identifier);
            default -> throw new IllegalArgumentException("Unknown exportable type: " + identifier.getExportableValueType());
        };
    }
}
