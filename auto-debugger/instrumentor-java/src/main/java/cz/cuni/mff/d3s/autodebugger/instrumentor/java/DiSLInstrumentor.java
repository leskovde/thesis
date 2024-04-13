package cz.cuni.mff.d3s.autodebugger.instrumentor.java;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.Instrumentor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Model;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor.ModelToCodeVisitor;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling.DiSLModel;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.visitor.DiSLModelToCodeVisitor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuperBuilder
public class DiSLInstrumentor extends Instrumentor {
  private final String dislRunnerPath = "../../disl/bin/disl.py";

  @Override
  public void runInstrumentation() {
    var jarPath = generateDiSLClass(new DiSLModel(this)).flatMap(this::compileDiSLClass);
    jarPath.ifPresent(this::instrumentApplication);
  }

  private void instrumentApplication(String jarPath) {
    log.info("Running DiSL instrumentation");
    try {
      var scriptProcess =
              new ProcessBuilder("python3", dislRunnerPath, "--", applicationPath).start();
//      try (var raf = new RandomAccessFile("test.txt", "rw")) {
//        int a = raf.readInt();
//        int b = raf.readInt();
//        log.info("Read from file: a={}, b={}", a, b);
//      }

      BufferedReader stdInput = new BufferedReader(new
              InputStreamReader(scriptProcess.getInputStream()));

      String s;
      while ((s = stdInput.readLine()) != null) {
        System.out.println(s);
      }

      scriptProcess.waitFor();
      log.info("DiSL instrumentation finished");
    } catch (Exception e) {
      log.error("Failed to run DiSL instrumentation", e);
    }
  }

  private Optional<String> generateDiSLClass(Model model) {
    ModelToCodeVisitor visitor = new DiSLModelToCodeVisitor();
    visitor.visit(model);
    return Optional.of(visitor.getGeneratedCode());
  }

  private Optional<String> compileDiSLClass(String classPath) {
    var compiler = new DiSLCompiler(this);
    return compiler.compileDiSLClass(classPath);
  }
}
