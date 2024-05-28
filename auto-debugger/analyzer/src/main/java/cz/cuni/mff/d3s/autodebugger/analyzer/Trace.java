package cz.cuni.mff.d3s.autodebugger.analyzer;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.enums.ExportableValueType;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.VariableIdentifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.VariableIdentifierParameters;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Trace {
  private final Map<VariableIdentifier, Set<Object>> variableValues = new HashMap<>();

  public Trace(List<Path> paths) {
    try {
      for (var path : paths) {
        deserializeTrace(path);
      }
    } catch (IOException i) {
      log.error("Failed to deserialize trace", i);
    } catch (ClassNotFoundException c) {
      log.error("Class not found", c);
    }
  }

  public Set<Object> getArgumentValues() {
    var keys =
        variableValues.keySet().stream()
            .filter(variable -> variable.getExportableValueType() == ExportableValueType.ARGUMENT);
    return keys.map(variableValues::get).flatMap(Set::stream).collect(Collectors.toSet());
  }

  public Set<Object> getVariableValues() {
    var keys =
        variableValues.keySet().stream()
            .filter(variable -> variable.getExportableValueType() == ExportableValueType.VARIABLE);
    return keys.map(variableValues::get).flatMap(Set::stream).collect(Collectors.toSet());
  }

  public Set<Object> getFieldValues() {
    var keys =
        variableValues.keySet().stream()
            .filter(variable -> variable.getExportableValueType() == ExportableValueType.FIELD);
    return keys.map(variableValues::get).flatMap(Set::stream).collect(Collectors.toSet());
  }

  public Set<Object> getStaticFieldValues() {
    var keys =
        variableValues.keySet().stream()
            .filter(
                variable -> variable.getExportableValueType() == ExportableValueType.STATIC_FIELD);
    return keys.map(variableValues::get).flatMap(Set::stream).collect(Collectors.toSet());
  }

  private void deserializeTrace(Path path) throws IOException, ClassNotFoundException {
    try (var fileIn = new FileInputStream(path.toFile());
        var in = new ObjectInputStream(fileIn)) {
      var value = in.readObject();
      var identifier =
          new VariableIdentifier(VariableIdentifierParameters.builder().variableName(path.getFileName().toString()).build());
      if (variableValues.containsValue(identifier)) {
        variableValues.get(identifier).add(value);
      } else {
        variableValues.put(identifier, Set.of(value));
      }
    }
  }
}
