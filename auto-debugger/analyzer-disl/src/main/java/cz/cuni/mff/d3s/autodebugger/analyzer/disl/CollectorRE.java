import ch.usi.dag.dislre.REDispatch;

public class CollectorRE {
  private static final String messageFormat = "[%s]: %s";
  private static final String processName = "Sending process";

  private static short byteId = REDispatch.registerMethod("Collector.collectByte");
  private static short charId = REDispatch.registerMethod("Collector.collectChar");
  private static short shortId = REDispatch.registerMethod("Collector.collectShort");
  private static short intId = REDispatch.registerMethod("Collector.collectInt");
  private static short longId = REDispatch.registerMethod("Collector.collectLong");
  private static short floatId = REDispatch.registerMethod("Collector.collectFloat");
  private static short doubleId = REDispatch.registerMethod("Collector.collectDouble");
  private static short booleanId = REDispatch.registerMethod("Collector.collectBoolean");

  public static void collectByte(final int slot, final byte b) {
    printPid();
    REDispatch.analysisStart(byteId);
    REDispatch.sendInt(slot);
    REDispatch.sendByte(b);
    REDispatch.analysisEnd();
  }

  public static void collectChar(final int slot, final char c) {
    printPid();
    REDispatch.analysisStart(charId);
    REDispatch.sendInt(slot);
    REDispatch.sendChar(c);
    REDispatch.analysisEnd();
  }

  public static void collectShort(final int slot, final short s) {
    printPid();
    REDispatch.analysisStart(shortId);
    REDispatch.sendInt(slot);
    REDispatch.sendShort(s);
    REDispatch.analysisEnd();
  }

  public static void collectInt(final int slot, final int i) {
    printPid();
    REDispatch.analysisStart(intId);
    REDispatch.sendInt(slot);
    REDispatch.sendInt(i);
    REDispatch.analysisEnd();
  }

  public static void collectLong(final int slot, final long l) {
    printPid();
    REDispatch.analysisStart(longId);
    REDispatch.sendInt(slot);
    REDispatch.sendLong(l);
    REDispatch.analysisEnd();
  }

  public static void collectFloat(final int slot, final float f) {
    printPid();
    REDispatch.analysisStart(floatId);
    REDispatch.sendInt(slot);
    REDispatch.sendFloat(f);
    REDispatch.analysisEnd();
  }

  public static void collectDouble(final int slot, final double d) {
    printPid();
    REDispatch.analysisStart(doubleId);
    REDispatch.sendInt(slot);
    REDispatch.sendDouble(d);
    REDispatch.analysisEnd();
  }

  public static void collectBoolean(final int slot, final boolean z) {
    printPid();
    REDispatch.analysisStart(booleanId);
    REDispatch.sendInt(slot);
    REDispatch.sendBoolean(z);
    REDispatch.analysisEnd();
  }

  private static void printPid() {
    System.out.println(
        String.format(messageFormat, processName, "PID: " + ProcessHandle.current().pid()));
  }
}
