package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.factories.VariableIdentifierFactory;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.VariableIdentifierParameters;
import lombok.Getter;

@Getter
public class JavaArgument extends ExportableValue {
    private final int argumentSlot;

    public JavaArgument(int argumentSlot, String type) {
        super(type);
        this.argumentSlot = argumentSlot;
    }

    @Override
    public String emitCode(int indentLevel) {
        append("\n");
        append(type);
        append(" ");
        append(instrumentationVariableIdentifier.getName());
        append(" = ");
        append("di.getMethodArgumentValue(");
        append(Integer.toString(argumentSlot));
        append(", ");
        append(type);
        append(".class);");
        return getCode();
    }
}
