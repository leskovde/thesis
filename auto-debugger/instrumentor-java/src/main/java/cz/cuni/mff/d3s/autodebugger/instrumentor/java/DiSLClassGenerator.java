package cz.cuni.mff.d3s.autodebugger.instrumentor.java;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.Instrumentor;
import java.io.File;
import java.io.FileWriter;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class DiSLClassGenerator {
  private final String PACKAGE_NAME = "cz.cuni.mff.d3s.autodebugger.analyzer.disl";
  private final String IMPORTS =
      """
          import ch.usi.dag.disl.annotation.After;
          import ch.usi.dag.disl.dynamiccontext.DynamicContext;
          import ch.usi.dag.disl.marker.BodyMarker;

          import java.io.FileNotFoundException;
          import java.io.IOException;
          import java.io.RandomAccessFile;
          """;
  private final String CLASS_NAME = "NewDiSLClass";
  private Instrumentor instrumentor;

  public Optional<String> generateDiSLClass() {
    try {
      log.info("Generating DiSL class");

      String path = "analyzer-disl/src/main/java/cz/cuni/mff/d3s/autodebugger/analyzer/disl/";
      String fileName = "NewDiSLClass.java";
      File dislClassFile = new File(path + fileName);
      try (FileWriter writer = new FileWriter(dislClassFile)) {
        writer.write(
            String.format(
                """
                      package %s;

                      %s

                      public class %s {

                          @After(marker = BodyMarker.class, scope = "Main.main")
                          public static void getLocalVariableValue(DynamicContext di) {
                              int a = di.getLocalVariableValue(0, int.class);
                              int b = di.getLocalVariableValue(1, int.class);
                              System.out.println("disl: a=" + a);
                              System.out.println("disl: b=" + b);
                          }
                      }
                      """,
                PACKAGE_NAME, IMPORTS, CLASS_NAME));
      }
      log.info("DiSL class generated");
      return Optional.of(dislClassFile.getParentFile().getAbsolutePath());
    } catch (Exception e) {
      log.error("Failed to generate DiSL class", e);
      return Optional.empty();
    }
  }
}
