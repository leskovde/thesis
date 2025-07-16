package cz.cuni.mff.d3s.autodebugger.instrumentor.common;

import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.ExportableValue;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.Identifier;
import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.MethodIdentifier;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

@SuperBuilder
@Getter
@Slf4j
public abstract class Instrumentor {
  protected final Path applicationJarPath;
  protected final List<Path> classpath;
  protected final Identifier className;
  protected final List<ExportableValue> exportedValues;
  protected final MethodIdentifier method;

  public abstract List<Path> runInstrumentation();

  public Path serializeIdentifiers(Path outputDirectory) {
    Map<Integer, ExportableValue> identifierMapping = new HashMap<>();
    for (ExportableValue value : exportedValues) {
      identifierMapping.put(value.getInternalId(), value);
    }

    try {
      if (!outputDirectory.toFile().exists()) {
        if (outputDirectory.toFile().mkdirs()) {
            log.info("Created directory {}", outputDirectory);
            } else {
            log.error("Failed to create directory {}", outputDirectory);
            return null;
        }
      }
      File mappingFile =
          File.createTempFile("identifierMapping", ".json", outputDirectory.toFile());
      try (FileOutputStream fileOutput = new FileOutputStream(mappingFile);
          ObjectOutputStream objectStream = new ObjectOutputStream(fileOutput)) {
        objectStream.writeObject(identifierMapping);
      }
      return mappingFile.toPath();
    } catch (IOException e) {
      log.error("Failed to serialize identifier mapping", e);
      return null;
    }
  }
}
