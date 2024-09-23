package cz.cuni.mff.d3s.autodebugger.instrumentor.java.factories;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.ArgumentIdentifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.ExportableIdentifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.VariableIdentifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling.ExportableValue;

public abstract class ExportableValueFactory {

  public static ExportableValue createFrom(ExportableIdentifier identifier) {
    return switch (identifier.getExportableValueType()) {
      case ARGUMENT ->
          ArgumentExportFactory.getInstance().createExportable((ArgumentIdentifier) identifier);
      case FIELD ->
          FieldExportFactory.getInstance().createExportable((VariableIdentifier) identifier);
      case VARIABLE ->
          VariableExportFactory.getInstance().createExportable((VariableIdentifier) identifier);
      default ->
          throw new IllegalArgumentException(
              "Unknown exportable type: " + identifier.getExportableValueType());
    };
  }
}
