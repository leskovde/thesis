package cz.cuni.mff.d3s.autodebugger.instrumentor.java;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.Instrumentor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.RandomAccessFile;

@Slf4j
@SuperBuilder
public class DiSLInstrumentor extends Instrumentor {
  private final String dislRunnerPath = "../../disl/bin/disl.py";

  @Override
  public void runInstrumentation() {
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
}
