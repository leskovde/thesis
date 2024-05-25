package cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling;

public abstract class Metaclass {
  private final StringBuilder builder = new StringBuilder();

    public abstract String emitCode();

    protected void append(String code) {
        builder.append(code);
    }

    protected String getCode() {
        return builder.toString();
    }
}
