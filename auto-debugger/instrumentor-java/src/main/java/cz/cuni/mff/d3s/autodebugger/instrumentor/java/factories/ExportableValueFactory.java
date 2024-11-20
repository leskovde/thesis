package cz.cuni.mff.d3s.autodebugger.instrumentor.java.factories;

import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.ArgumentIdentifier;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.ValueIdentifier;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.FieldIdentifier;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.VariableIdentifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling.JavaValue;

public abstract class ExportableValueFactory {

  public static JavaValue createFrom(ValueIdentifier identifier) {
    return switch (identifier.getValueType()) {
      case ARGUMENT ->
          ArgumentExportFactory.getInstance().createExportable((ArgumentIdentifier) identifier);
      case FIELD ->
          FieldExportFactory.getInstance().createExportable((FieldIdentifier) identifier);
      case VARIABLE ->
          VariableExportFactory.getInstance().createExportable((VariableIdentifier) identifier);
      default ->
          throw new IllegalArgumentException(
              "Unknown exportable type: " + identifier.getValueType());
    };
  }
}
