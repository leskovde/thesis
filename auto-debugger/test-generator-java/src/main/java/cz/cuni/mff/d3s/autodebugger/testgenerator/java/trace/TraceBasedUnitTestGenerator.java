package cz.cuni.mff.d3s.autodebugger.testgenerator.java.trace;

import cz.cuni.mff.d3s.autodebugger.model.java.Trace;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.ExportableValue;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TraceBasedGenerator;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.UnitTestGenerator;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TestGenerationContext;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TraceBasedUnitTestGenerator implements TraceBasedGenerator, UnitTestGenerator {
  private final Map<Integer, ExportableValue> identifierMapping;
  private final NaiveTraceBasedGenerator naiveGenerator;

  public TraceBasedUnitTestGenerator(Path identifierMappingFile) {
    log.info("Loading identifier mapping from file: {}", identifierMappingFile);
    try {
      try (FileInputStream fileReader = new FileInputStream(identifierMappingFile.toFile());
          ObjectInputStream objectStream = new ObjectInputStream(fileReader)) {
        identifierMapping = (HashMap<Integer, ExportableValue>) objectStream.readObject();
      }
    } catch (Exception e) {
      log.error("Failed to load identifier mapping", e);
      throw new RuntimeException("Failed to load identifier mapping", e);
    }

    this.naiveGenerator = new NaiveTraceBasedGenerator(identifierMapping);
  }

  @Override
  public List<Path> generateTests(Trace trace) {
    log.info("Generating unit tests from trace using naive strategy");
    return naiveGenerator.generateTests(trace);
  }

  public List<Path> generateTests(Trace trace, TestGenerationContext context) {
    log.info("Generating unit tests from trace with context: {}", context.getTargetMethodSignature());
    return naiveGenerator.generateTests(trace, context);
  }

  @Override
  public List<Path> generateUnitTests() {
    log.info("Generating unit tests without trace data");
    // This method is kept for backward compatibility but doesn't have much use without trace data
    return List.of();
  }
}
