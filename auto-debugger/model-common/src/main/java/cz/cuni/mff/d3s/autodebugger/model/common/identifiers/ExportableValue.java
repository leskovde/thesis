package cz.cuni.mff.d3s.autodebugger.model.common.identifiers;

import java.io.Serializable;

public interface ExportableValue extends Serializable {
    int getInternalId();
    String getType();
}
