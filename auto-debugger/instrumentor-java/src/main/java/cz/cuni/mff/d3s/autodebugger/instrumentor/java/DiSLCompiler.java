package cz.cuni.mff.d3s.autodebugger.instrumentor.java;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.Instrumentor;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.enums.InstrumentationStatus;
import java.io.File;
import java.util.List;
import javax.tools.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class DiSLCompiler {
  private Instrumentor instrumentor;

  public InstrumentationStatus compileDiSLClass() {
    try {
      log.info("Compiling DiSL class");
      String path = "analyzer-disl/src/main/java/cz/cuni/mff/d3s/autodebugger/analyzer/disl/";
      String fileName = "NewDiSLClass.java";

      File dislClassFile = new File(path + fileName);
      JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
      StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
      File outputDirectory = new File("analyzer-disl/build/classes/java/main/");
      fileManager.setLocation(StandardLocation.CLASS_OUTPUT, List.of(outputDirectory));
      Iterable<? extends JavaFileObject> compilationUnits =
          fileManager.getJavaFileObjectsFromFiles(List.of(dislClassFile));
      compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
      fileManager.close();
    } catch (Exception e) {
      log.error("Failed to compile DiSL class", e);
      return InstrumentationStatus.FAIL;
    }
    return InstrumentationStatus.SUCCESS;
  }
}
