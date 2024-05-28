package cz.cuni.mff.d3s.autodebugger.instrumentor.java;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.Instrumentor;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Model;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling.DiSLModel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuperBuilder
public class DiSLInstrumentor extends Instrumentor {
  private final String dislRunnerPath = "../../disl/bin/disl.py";
  private final String dislHomePath = "../../disl/output";

  @Override
  public List<Path> runInstrumentation() {
    var jarPath = generateDiSLClass(new DiSLModel(this)).flatMap(this::compileDiSLClass);
    if (jarPath.isEmpty()) {
      return List.of();
    }
    return instrumentApplication(jarPath.get());
  }

  private List<Path> instrumentApplication(String jarPath) {
    List<Path> paths = new ArrayList<>();
    log.info("Running DiSL instrumentation");
    try {
      var scriptProcess =
          new ProcessBuilder(
                  "python3",
                  dislRunnerPath,
                  "-d",
                  dislHomePath,
                  "-cse",
                  "--",
                  jarPath,
                  "-jar",
                  applicationPath)
              .start();
      // Print stdout
      try (var stdoutReader =
          new BufferedReader(new InputStreamReader(scriptProcess.getInputStream()))) {
        String line;
        while ((line = stdoutReader.readLine()) != null) {
          System.out.println(line);
        }
      }
      // Print stderr
      try (var stderrReader =
          new BufferedReader(new InputStreamReader(scriptProcess.getErrorStream()))) {
        String line;
        while ((line = stderrReader.readLine()) != null) {
          System.err.println(line);
        }
      }
      scriptProcess.waitFor();
      log.info("DiSL instrumentation finished");
    } catch (Exception e) {
      log.error("Failed to run DiSL instrumentation", e);
    }
    return paths;
  }

  private Optional<String> generateDiSLClass(Model model) {
    var generator = new DiSLClassGenerator(model);
    return generator.generateCode();
  }

  private Optional<String> compileDiSLClass(String classPath) {
    var compiler = new DiSLCompiler(this);
    return compiler.compileDiSLClass(classPath);
  }
}
