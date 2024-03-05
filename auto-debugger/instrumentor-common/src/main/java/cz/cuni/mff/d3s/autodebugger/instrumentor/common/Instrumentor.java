package cz.cuni.mff.d3s.autodebugger.instrumentor.common;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.enums.Visibility;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public abstract class Instrumentor {
    protected final String applicationPath;
    protected final String className;
    protected final List<Visibility> fields;
    protected final List<Visibility> methods;

    public abstract void runInstrumentation();
}
