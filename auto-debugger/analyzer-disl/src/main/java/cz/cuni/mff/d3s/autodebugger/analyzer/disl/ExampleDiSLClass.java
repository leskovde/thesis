package cz.cuni.mff.d3s.autodebugger.analyzer.disl;

import ch.usi.dag.disl.annotation.After;
import ch.usi.dag.disl.dynamiccontext.DynamicContext;
import ch.usi.dag.disl.marker.BodyMarker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class UncompilableDiSLClass {

    @After(marker = BodyMarker.class, scope = "Main.main")
    public static void getLocalVariableValue(DynamicContext di) {
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
