package cz.cuni.mff.d3s.autodebugger.testgenerator.java.trace;

import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.ExportableValue;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TraceBasedGenerator;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.UnitTestGenerator;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class TraceBasedUnitTestGenerator implements TraceBasedGenerator, UnitTestGenerator {
  private final Map<Integer, ExportableValue> identifierMapping;

  public TraceBasedUnitTestGenerator(Path identifierMappingFile) {
    // TODO: deserialize JSON
  }

  @Override
  public List<Path> generateTests(cz.cuni.mff.d3s.autodebugger.model.java.Trace trace) {
    System.err.println("Generating unit tests from trace: " + trace);
    return List.of();
  }

  @Override
  public List<Path> generateUnitTests() {
    System.err.println("Generating unit tests");
    return List.of();
  }
}
