package cz.cuni.mff.d3s.autodebugger.instrumentor.java;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.Instrumentor;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Model;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling.DiSLModel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuperBuilder
public class DiSLInstrumentor extends Instrumentor {

  @Builder.Default private Path dislRepositoryPath = Path.of("../../disl/");

  @Builder.Default
  private Path generatedCodeOutputDirectory =
      Path.of("analyzer-disl/src/main/java/cz/cuni/mff/d3s/autodebugger/analyzer/disl/");

  @Getter @Builder.Default
  private Path jarOutputPath = Path.of("analyzer-disl/build/libs/instrumentation.jar");

  public Path getDislClassPath() {
    return dislRepositoryPath.resolve("output/lib/");
  }

  public Path getDislRunnerPath() {
    return dislRepositoryPath.resolve("bin/disl.py");
  }

  public Path getDislHomePath() {
    return dislRepositoryPath.resolve("output");
  }

  @Override
  public List<Path> runInstrumentation() {
    var jarPath = generateDiSLClass(new DiSLModel(this)).flatMap(this::compileDiSLClass);
    return jarPath.map(this::instrumentApplication).orElseGet(List::of);
  }

  private List<Path> instrumentApplication(Path instrumentationJarPath) {
    log.info("Running DiSL instrumentation");
    try {
      var scriptProcess =
          new ProcessBuilder(
                  "python3",
                  getDislRunnerPath().toString(),
                  "-d",
                  getDislHomePath().toString(),
                  "-cse",
                  "-e_cp",
                  "../test-generator-java/build/libs/*:../test-generator-common/build/libs/*:../analyzer/build/libs/*",
                  "--",
                  instrumentationJarPath.toString(),
                  "-jar",
                  applicationJarPath.toString())
              .start();
      // Print stdout
      try (var stdoutReader =
          new BufferedReader(new InputStreamReader(scriptProcess.getInputStream()))) {
        String line;
        while ((line = stdoutReader.readLine()) != null) {
          log.info(line);
        }
      }
      // Print stderr
      try (var stderrReader =
          new BufferedReader(new InputStreamReader(scriptProcess.getErrorStream()))) {
        String line;
        while ((line = stderrReader.readLine()) != null) {
          log.error(line);
        }
      }
      scriptProcess.waitFor();
      log.info("DiSL instrumentation finished");
    } catch (Exception e) {
      log.error("Failed to run DiSL instrumentation", e);
    }
    return List.of();
  }

  private Optional<String> generateDiSLClass(Model model) {
    var generator = new DiSLClassGenerator(generatedCodeOutputDirectory, model);
    return generator.generateCode();
  }

  private Optional<Path> compileDiSLClass(String classPath) {
    var compiler = new DiSLCompiler(this);
    return compiler.compileDiSLClass(classPath);
  }
}
