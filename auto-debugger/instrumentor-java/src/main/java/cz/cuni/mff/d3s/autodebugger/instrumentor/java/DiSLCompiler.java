package cz.cuni.mff.d3s.autodebugger.instrumentor.java;

import java.io.*;
import java.nio.file.Path;
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
  private final String DISL_CLASS_NAME = "DiSLClass";
  // private final List<String> JAVAC_OPTIONS = List.of("-proc:only");
  private DiSLInstrumentor instrumentor;

  public Optional<Path> compileDiSLClass(String sourcePath) {
    // TODO: Maybe via Gradle?
    //  https://stackoverflow.com/questions/49876189/how-to-run-a-gradle-task-from-a-java-code
    try {
      log.info("Compiling DiSL class");
      String fileName = DISL_CLASS_NAME + ".java";
      File dislClassFile = new File(sourcePath, fileName);
      JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
      try (StandardJavaFileManager fileManager =
          compiler.getStandardFileManager(null, null, null)) {
        File outputDirectory = new File("../analyzer-disl/build/classes/java/main/");
        try {
          if (outputDirectory.mkdirs()) {
            log.info("Created output directory");
          }
        } catch (Exception e) {
          log.error("Failed to create output directory", e);
          return Optional.empty();
        }
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, List.of(outputDirectory));
        fileManager.setLocation(StandardLocation.CLASS_PATH, getDiSLClassPath());
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

  private Optional<Path> packageObjects(StandardJavaFileManager fileManager) {
    Manifest manifest = new Manifest();
    manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
    manifest.getMainAttributes().putValue("DiSL-Classes", "DiSLClass");
    try {
      if (instrumentor.getJarOutputPath().toFile().exists()) {
        if (instrumentor.getJarOutputPath().toFile().delete()) {
          log.info("Removed old JAR file");
        } else {
          log.error("Failed to remove old JAR file");
          return Optional.empty();
        }
      }
    } catch (Exception e) {
      log.error("Failed to remove old JAR file", e);
      return Optional.empty();
    }
    try {
      if (instrumentor.getJarOutputPath().toFile().getParentFile().mkdirs()) {
        log.info("Created JAR directory");
      }
    } catch (Exception e) {
      log.error("Failed to create JAR directory", e);
      return Optional.empty();
    }
    try (JarOutputStream target =
        new JarOutputStream(
            new FileOutputStream(instrumentor.getJarOutputPath().toFile()), manifest)) {
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
    return Optional.of(instrumentor.getJarOutputPath());
  }

  private void addToJar(File source, JarOutputStream target) throws IOException {
    // String name = source.getPath().replace("\\", "/");
    String name = source.toPath().getFileName().toString();
    if (source.isDirectory()) {
      //      if (!name.endsWith("/")) {
      //        name += "/";
      //      }
      //      JarEntry entry = new JarEntry(name);
      //      entry.setTime(source.lastModified());
      //      target.putNextEntry(entry);
      //      target.closeEntry();
      //      for (File nestedFile : source.listFiles()) {
      //        addToJar(nestedFile, target);
      //      }
      log.error("Directory in JAR not supported");
      throw new IOException("Directory in JAR not supported");
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

  private List<File> getDiSLClassPath() {
    return List.of(
        new File(instrumentor.getDislClassPath().toString(), "disl-server.jar"),
        new File(instrumentor.getDislClassPath().toString(), "dislre-server.jar"),
        new File(instrumentor.getDislClassPath().toString(), "dislre-dispatch.jar"));
  }
}
