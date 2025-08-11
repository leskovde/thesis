package cz.cuni.mff.d3s.autodebugger.testgenerator.java.trace;

import cz.cuni.mff.d3s.autodebugger.model.common.RunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.common.trace.Trace;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.JavaValueIdentifier;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TraceBasedGenerator;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.UnitTestGenerator;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TestGenerationContext;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TestGenerator;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TraceBasedUnitTestGenerator implements TraceBasedGenerator, UnitTestGenerator, TestGenerator {
  private final Map<Integer, JavaValueIdentifier> identifierMapping;
  private final NaiveTraceBasedGenerator naiveGenerator;
  private RunConfiguration runConfiguration;

  public TraceBasedUnitTestGenerator(Path identifierMappingFile) {
    log.info("Loading identifier mapping from file: {}", identifierMappingFile);
    try {
      try (FileInputStream fileReader = new FileInputStream(identifierMappingFile.toFile());
          ObjectInputStream objectStream = new ObjectInputStream(fileReader)) {
        identifierMapping = (HashMap<Integer, JavaValueIdentifier>) objectStream.readObject();
      }
    } catch (Exception e) {
      log.error("Failed to load identifier mapping", e);
      throw new RuntimeException("Failed to load identifier mapping", e);
    }

    this.naiveGenerator = new NaiveTraceBasedGenerator(identifierMapping);
  }

  @Override
  public void configure(RunConfiguration configuration) {
    this.runConfiguration = configuration;
    log.debug("Configured trace-based test generator with run configuration");
  }

  @Override
  public List<Path> generateTests(Trace trace) {
    log.info("Generating unit tests from trace using naive strategy");
    if (runConfiguration != null) {
      return generateTests(trace, runConfiguration);
    }
    return naiveGenerator.generateTests(trace);
  }

  @Override
  public List<Path> generateTests(Trace trace, RunConfiguration configuration) {
    log.info("Generating unit tests from trace with RunConfiguration");
    return naiveGenerator.generateTests(trace, configuration);
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

  @Override
  public String getGenerationTechnique() {
    return "Trace-based";
  }

  @Override
  public void validateTrace(Trace trace) {
    if (trace == null) {
      throw new IllegalArgumentException("Trace cannot be null");
    }
    // Additional validation can be added here if needed
    log.debug("Trace validation passed for trace-based test generation");
  }
}
