package cz.cuni.mff.d3s.autodebugger.instrumentor.common;

import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.ExportableValue;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.Identifier;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.MethodIdentifier;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public abstract class Instrumentor {
  protected final Path applicationJarPath;
  protected final List<Path> classpath;
  protected final Identifier className;
  protected final List<ExportableValue> exportedValues;
  protected final MethodIdentifier method;

  public abstract List<Path> runInstrumentation();

  public String serializeIdentifiers(Path outputDirectory) {
    Map<Integer, ExportableValue> identifierMapping = new HashMap<>();
    for (ExportableValue value : exportedValues) {
      identifierMapping.put(value.getInternalId(), value);
    }

    // TODO: Serialize to json
  }
}
