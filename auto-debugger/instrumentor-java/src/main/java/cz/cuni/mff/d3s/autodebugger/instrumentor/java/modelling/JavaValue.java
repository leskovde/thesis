package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.model.java.factories.VariableIdentifierFactory;
import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.ExportableValue;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.JavaVariableIdentifier;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.VariableIdentifierParameters;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Metaclass;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.CollectorMethodRegistry;

public abstract class JavaValue extends Metaclass {
    protected final ExportableValue exportedValueIdentifier;
    protected final JavaVariableIdentifier instrumentationVariableIdentifier;

    public JavaValue(ExportableValue exportableValue) {
        this.exportedValueIdentifier = exportableValue;
        var parameters = VariableIdentifierParameters.builder()
                .variableType(exportableValue.getType())
                .build();
        this.instrumentationVariableIdentifier = VariableIdentifierFactory.getInstance()
                .generateIdentifier(parameters);
    }

    public String emitCollectorCode() {
        return "CollectorRE." + CollectorMethodRegistry.getCollectorMethodName(exportedValueIdentifier) + "(" + exportedValueIdentifier.getInternalId() + ", " + instrumentationVariableIdentifier.getName() + ");";
    }
}
