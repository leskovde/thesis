package cz.cuni.mff.d3s.autodebugger.instrumentor.java;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.Instrumentor;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.enums.InstrumentationStatus;
import java.io.RandomAccessFile;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuperBuilder
public class DiSLInstrumentor extends Instrumentor {
  private final String dislRunnerPath = "../../disl/bin/disl.py";

  @Override
  public void runInstrumentation() {
    var status = generateDiSLClass();
    if (status == InstrumentationStatus.FAIL) {
      log.error("DiSLClass generation failed");
      return;
    }
    status = compileDiSLClass();
    if (status == InstrumentationStatus.FAIL) {
      log.error("DiSLClass compilation failed");
    }
    log.info("Running DiSL instrumentation");
    try {
      var scriptProcess =
          new ProcessBuilder("python3", dislRunnerPath, "--", applicationPath).start();
      try (var raf = new RandomAccessFile("test.txt", "rw")) {
        int a = raf.readInt();
        int b = raf.readInt();
        log.info("Read from file: a={}, b={}", a, b);
      }
      scriptProcess.waitFor();
      log.info("DiSL instrumentation finished");
    } catch (Exception e) {
      log.error("Failed to run DiSL instrumentation", e);
    }
  }

  private InstrumentationStatus generateDiSLClass() {
    var classGenerator = new DiSLClassGenerator(this);
    var status = classGenerator.generateDiSLClass();
    if (status == InstrumentationStatus.SUCCESS || status == InstrumentationStatus.SKIPPED) {
      return InstrumentationStatus.SUCCESS;
    }
    return InstrumentationStatus.FAIL;
  }

  private InstrumentationStatus compileDiSLClass() {
    var compiler = new DiSLCompiler(this);
    var status = compiler.compileDiSLClass();
    if (status == InstrumentationStatus.SUCCESS || status == InstrumentationStatus.SKIPPED) {
      return InstrumentationStatus.SUCCESS;
    }
    return InstrumentationStatus.FAIL;
  }
}
