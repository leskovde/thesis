package cz.cuni.mff.d3s.autodebugger.instrumentor.common;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.Identifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.MethodIdentifier;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.nio.file.Path;
import java.util.List;

@SuperBuilder
@Getter
public abstract class Instrumentor {
    protected final Path applicationJarPath;
    protected final Identifier className;
    protected final List<Identifier> fields;
    protected final List<Identifier> variables;
    protected final List<MethodIdentifier> methods;

    public abstract List<Path> runInstrumentation();
}
