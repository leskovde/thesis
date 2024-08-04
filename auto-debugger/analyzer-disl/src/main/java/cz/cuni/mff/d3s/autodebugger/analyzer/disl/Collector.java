import ch.usi.dag.dislreserver.remoteanalysis.RemoteAnalysis;
import ch.usi.dag.dislreserver.shadow.ShadowClass;
import ch.usi.dag.dislreserver.shadow.ShadowObject;
import ch.usi.dag.dislreserver.shadow.ShadowString;
import ch.usi.dag.dislreserver.shadow.ShadowThread;

public class Collector extends RemoteAnalysis {

  public static void testingAdvanced(
      final ShadowObject s, final ShadowObject o, final ShadowObject c, final ShadowObject t) {

    if (!(s instanceof ShadowString)) {
      throw new RuntimeException("This string should be transfered as string");
    }

    if (!s.toString().equals("Corect transfer of String")) {
      throw new RuntimeException("Incorect transfer of String");
    }

    // object id should be non 0
    if (o.getId() == 0) {
      throw new RuntimeException("Object id should not be null");
    }

    System.out.println("Received object id: " + o.getId());

    if (!(o instanceof ShadowString)) {
      throw new RuntimeException("This string should be transfered as string");
    }

    if (((ShadowString) o).toString() != null) {
      throw new RuntimeException("This string should be transfered without data");
    }

    if (!(c instanceof ShadowClass)) {
      throw new RuntimeException("This class should be transfered as class");
    }

    if (!(t instanceof ShadowThread)) {
      throw new RuntimeException("This thread should be transfered as thread");
    }

    System.out.println(
        "Received thread: "
            + ((ShadowThread) t).getName()
            + " is deamon "
            + ((ShadowThread) t).isDaemon());
  }

  public static void printClassInfo(final ShadowClass sc) {

    if (sc == null) {
      System.out.println("null");
      return;
    }

    System.out.println("name: " + sc.getName());
  }

  public void testingBasic(
      final boolean b, final byte by, final char c, final short s, final int i, final long l) {

    System.out.println("[Receiving process] PID: " + ProcessHandle.current().pid());

    if (b != true) {
      throw new RuntimeException("Incorect transfer of boolean");
    }

    if (by != (byte) 125) {
      throw new RuntimeException("Incorect transfer of byte");
    }

    if (c != 's') {
      throw new RuntimeException("Incorect transfer of char");
    }

    if (s != (short) 50000) {
      throw new RuntimeException("Incorect transfer of short");
    }

    if (i != 100000) {
      throw new RuntimeException("Incorect transfer of int");
    }

    if (l != 10000000000L) {
      throw new RuntimeException("Incorect transfer of long");
    }
  }

  @Override
  public void atExit() {
    System.out.println("Exiting analysis...");
  }

  @Override
  public void objectFree(final ShadowObject netRef) {
    System.out.println("Object free for id " + netRef.getId());
  }
}
