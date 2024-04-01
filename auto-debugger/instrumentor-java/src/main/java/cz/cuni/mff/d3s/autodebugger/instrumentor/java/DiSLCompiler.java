package cz.cuni.mff.d3s.autodebugger.instrumentor.java;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.Instrumentor;
import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import javax.tools.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class DiSLCompiler {
  private final String DISL_CLASS_PATH = "../../disl/output/lib/";
  private final String JAR_TARGET = "analyzer-disl/build/libs/instrumentation.jar";
  // private final List<String> JAVAC_OPTIONS = List.of("-proc:only");

  private Instrumentor instrumentor;

  public Optional<String> compileDiSLClass(String sourcePath) {
    // TODO: Maybe via Gradle?
    //  https://stackoverflow.com/questions/49876189/how-to-run-a-gradle-task-from-a-java-code
    try {
      log.info("Compiling DiSL class");
      String fileName = "NewDiSLClass.java";

      File dislClassFile = new File(sourcePath, fileName);
      JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
      try (StandardJavaFileManager fileManager =
          compiler.getStandardFileManager(null, null, null)) {
        File outputDirectory = new File("analyzer-disl/build/classes/java/main/");
        try {
          if (outputDirectory.mkdirs()) {
            log.info("Created output directory");
          }
        } catch (Exception e) {
          log.error("Failed to create output directory", e);
          return Optional.empty();
        }
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, List.of(outputDirectory));
        fileManager.setLocation(
            StandardLocation.CLASS_PATH,
            List.of(
                new File(DISL_CLASS_PATH, "disl-server.jar"),
                new File(DISL_CLASS_PATH, "dislre-server.jar"),
                new File(DISL_CLASS_PATH, "dislre-dispatch.jar")));
        Iterable<? extends JavaFileObject> compilationUnits =
            fileManager.getJavaFileObjectsFromFiles(List.of(dislClassFile));
        if (!compiler.getTask(null, fileManager, null, null, null, compilationUnits).call()) {
          log.error("Failed to compile DiSL class");
          return Optional.empty();
        }
        return packageObjects(fileManager);
      }
    } catch (Exception e) {
      log.error("Failed to compile DiSL class", e);
      return Optional.empty();
    }
  }

  private Optional<String> packageObjects(StandardJavaFileManager fileManager) {
    Manifest manifest = new Manifest();
    manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
    try {
      if (new File(JAR_TARGET).getParentFile().mkdirs()) {
        log.info("Created JAR directory");
      }
    } catch (Exception e) {
      log.error("Failed to create JAR directory", e);
      return Optional.empty();
    }
    try (JarOutputStream target = new JarOutputStream(new FileOutputStream(JAR_TARGET), manifest)) {
      for (var object :
          fileManager.list(
              StandardLocation.CLASS_OUTPUT, "", Set.of(JavaFileObject.Kind.CLASS), true)) {
        log.info("Compiled class: {}", object.getName());
        addToJar(new File(object.getName()), target);
      }
    } catch (IOException e) {
      log.error("Failed to package DiSL class", e);
      return Optional.empty();
    }
    return Optional.of(JAR_TARGET);
  }

  private void addToJar(File source, JarOutputStream target) throws IOException {
    String name = source.getPath().replace("\\", "/");
    if (source.isDirectory()) {
      if (!name.endsWith("/")) {
        name += "/";
      }
      JarEntry entry = new JarEntry(name);
      entry.setTime(source.lastModified());
      target.putNextEntry(entry);
      target.closeEntry();
      for (File nestedFile : source.listFiles()) {
        addToJar(nestedFile, target);
      }
    } else {
      JarEntry entry = new JarEntry(name);
      entry.setTime(source.lastModified());
      target.putNextEntry(entry);
      try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source))) {
        byte[] buffer = new byte[1024];
        while (true) {
          int count = in.read(buffer);
          if (count == -1) break;
          target.write(buffer, 0, count);
        }
        target.closeEntry();
      }
    }
  }
}
