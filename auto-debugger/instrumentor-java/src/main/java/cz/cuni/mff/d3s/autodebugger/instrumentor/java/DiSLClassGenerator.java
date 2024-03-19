package cz.cuni.mff.d3s.autodebugger.instrumentor.java;

import java.io.File;
import java.io.FileWriter;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.enums.InstrumentationStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DiSLClassGenerator {
    private static boolean alreadyGenerated = false;
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
    private final String CLASS_NAME = "DiSLClass";
    private final String METHOD_NAME = "getLocalVariableValue";

    public InstrumentationStatus generateDiSLClass() {
        if (alreadyGenerated) {
            log.info("DiSL class already generated");
            return InstrumentationStatus.SKIPPED;
        }

        try {
            log.info("Generating DiSL class");

            String fileName = "NewDiSLClass.java";
            File dislClassFile = new File(fileName);
            try (FileWriter writer = new FileWriter(dislClassFile)) {
              writer.write(
                  String.format(
                      """
                      package %s;
  
                      %s
  
                      public class %s {
  
                          @After(marker = BodyMarker.class, scope = "Main.main")
                          public static void %s(DynamicContext di) {
                              int a = di.getLocalVariableValue(0, int.class);
                              int b = di.getLocalVariableValue(1, int.class);
                              try (RandomAccessFile raf = new RandomAccessFile("test.txt", "rw")) {
                                  raf.writeInt(a);
                                  raf.writeInt(b);
                              } catch (FileNotFoundException e) {
                                  throw new RuntimeException("Could not open named pipe");
                              } catch (IOException e) {
                                  throw new RuntimeException(e);
                              }
                          }
                      }
                      """,
                      PACKAGE_NAME, IMPORTS, CLASS_NAME, METHOD_NAME));
            }

            alreadyGenerated = true;
            log.info("DiSL class generated");
        } catch (Exception e) {
            log.error("Failed to generate DiSL class", e);
            return InstrumentationStatus.FAIL;
        }
        return InstrumentationStatus.SUCCESS;
    }
}
