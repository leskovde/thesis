package cz.cuni.mff.d3s.autodebugger.instrumentor.common;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.Identifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.MethodIdentifier;
import java.nio.file.Path;
import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public abstract class Instrumentor {
  protected final Path applicationJarPath;
  protected final Identifier className;
  protected final List<Identifier> exportedValues;
  protected final MethodIdentifier method;

  public abstract List<Path> runInstrumentation();
}
