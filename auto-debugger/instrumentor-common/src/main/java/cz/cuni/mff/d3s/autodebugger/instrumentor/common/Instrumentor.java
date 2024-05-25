package cz.cuni.mff.d3s.autodebugger.instrumentor.common;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.Identifier;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
public abstract class Instrumentor {
    protected final String applicationPath;
    protected final Identifier className;
    protected final List<Identifier> fields;
    protected final List<Identifier> variables;
    protected final List<Identifier> methods;

    public abstract void runInstrumentation();
}
