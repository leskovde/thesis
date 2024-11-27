package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.ExportableValue;

public class JavaReturnValue extends JavaValue {

    public JavaReturnValue(ExportableValue exportableValue) {
        super(exportableValue);
    }

    @Override
    public String emitCode() {
        // TODO
        return "";
    }
}
