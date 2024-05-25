package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import lombok.Getter;

@Getter
public class JavaVariable extends ExportableValue {
    private final int frameSlot;

    public JavaVariable(int frameSlot, String type) {
        super(type);
        this.frameSlot = frameSlot;
    }

    @Override
    public String emitCode() {
        append("\n");
        append(type);
        append(" ");
        append(instrumentationVariableIdentifier.getName());
        append(" = ");
        append("di.getLocalVariableValue(");
        append(Integer.toString(frameSlot));
        append(", ");
        append(type);
        append(".class);");
        return getCode();
    }
}
