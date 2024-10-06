package cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling;

public abstract class Metaclass {
  private final StringBuilder builder = new StringBuilder();

    public abstract String emitCode();

    protected void append(String code) {
        builder.append(code);
    }

    protected String getCode() {
        var result = builder.toString();
        builder.setLength(0);
        return result;
    }
}
