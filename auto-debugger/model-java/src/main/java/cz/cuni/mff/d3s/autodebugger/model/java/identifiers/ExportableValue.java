package cz.cuni.mff.d3s.autodebugger.model.java.identifiers;

import java.io.Serializable;

public interface ExportableValue extends Serializable {
    int getInternalId();
    String getType();
}
