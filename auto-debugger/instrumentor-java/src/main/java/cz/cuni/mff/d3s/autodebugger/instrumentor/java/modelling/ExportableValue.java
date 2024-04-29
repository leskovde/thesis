package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.enums.ExportableValueType;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.factories.VariableIdentifierFactory;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.Identifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.VariableIdentifierParameters;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Metaclass;

public abstract class ExportableValue extends Metaclass {
    protected String type;
    protected Identifier instrumentationVariableIdentifier;

    public ExportableValue(String type) {
        this.type = type;
        var parameters = VariableIdentifierParameters.builder()
                .variableType(type)
                .exportableType(ExportableValueType.VARIABLE)
                .build();
        this.instrumentationVariableIdentifier = VariableIdentifierFactory.getInstance()
                .generateIdentifier(parameters);
    }
}
