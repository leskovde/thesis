import ch.usi.dag.dislre.REDispatch;

public class CollectorRE {
  private static final String messageFormat = "[%s]: %s";
  private static final String processName = "Sending process";

  private static short byteArgId = REDispatch.registerMethod("Collector.collectByteArg");
  private static short charArgId = REDispatch.registerMethod("Collector.collectCharArg");
  private static short shortArgId = REDispatch.registerMethod("Collector.collectShortArg");
  private static short intArgId = REDispatch.registerMethod("Collector.collectIntArg");
  private static short longArgId = REDispatch.registerMethod("Collector.collectLongArg");
  private static short floatArgId = REDispatch.registerMethod("Collector.collectFloatArg");
  private static short doubleArgId = REDispatch.registerMethod("Collector.collectDoubleArg");
  private static short booleanArgId = REDispatch.registerMethod("Collector.collectBooleanArg");

  public static void collectByteArg(final int slot, final byte b) {
    printPid();
    REDispatch.analysisStart(byteArgId);
    REDispatch.sendInt(slot);
    REDispatch.sendByte(b);
    REDispatch.analysisEnd();
  }

  public static void collectCharArg(final int slot, final char c) {
    printPid();
    REDispatch.analysisStart(charArgId);
    REDispatch.sendInt(slot);
    REDispatch.sendChar(c);
    REDispatch.analysisEnd();
  }

  public static void collectShortArg(final int slot, final short s) {
    printPid();
    REDispatch.analysisStart(shortArgId);
    REDispatch.sendInt(slot);
    REDispatch.sendShort(s);
    REDispatch.analysisEnd();
  }

  public static void collectIntArg(final int slot, final int i) {
    printPid();
    REDispatch.analysisStart(intArgId);
    REDispatch.sendInt(slot);
    REDispatch.sendInt(i);
    REDispatch.analysisEnd();
  }

  public static void collectLongArg(final int slot, final long l) {
    printPid();
    REDispatch.analysisStart(longArgId);
    REDispatch.sendInt(slot);
    REDispatch.sendLong(l);
    REDispatch.analysisEnd();
  }

  public static void collectFloatArg(final int slot, final float f) {
    printPid();
    REDispatch.analysisStart(floatArgId);
    REDispatch.sendInt(slot);
    REDispatch.sendFloat(f);
    REDispatch.analysisEnd();
  }

  public static void collectDoubleArg(final int slot, final double d) {
    printPid();
    REDispatch.analysisStart(doubleArgId);
    REDispatch.sendInt(slot);
    REDispatch.sendDouble(d);
    REDispatch.analysisEnd();
  }

  public static void collectBooleanArg(final int slot, final boolean z) {
    printPid();
    REDispatch.analysisStart(booleanArgId);
    REDispatch.sendInt(slot);
    REDispatch.sendBoolean(z);
    REDispatch.analysisEnd();
  }

  private static void printPid() {
    System.out.println(
        String.format(messageFormat, processName, "PID: " + ProcessHandle.current().pid()));
  }
}
