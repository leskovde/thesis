import ch.usi.dag.dislreserver.remoteanalysis.REDispatch;

public class CollectorRE {
  private static short startEventId = REDispatch.registerMethod("Collector.startEvent");
  private static short collectByteId = REDispatch.registerMethod("Collector.collectByte");
  private static short collectCharId = REDispatch.registerMethod("Collector.collectChar");
  private static short collectShortId = REDispatch.registerMethod("Collector.collectShort");
  private static short collectIntId = REDispatch.registerMethod("Collector.collectInt");
  private static short collectLongId = REDispatch.registerMethod("Collector.collectLong");
  private static short collectFloatId = REDispatch.registerMethod("Collector.collectFloat");
  private static short collectDoubleId = REDispatch.registerMethod("Collector.collectDouble");
  private static short collectBooleanId = REDispatch.registerMethod("Collector.collectBoolean");
  private static short collectStringId = REDispatch.registerMethod("Collector.collectString");
  private static short collectObjectId = REDispatch.registerMethod("Collector.collectObject");

  public static void startEvent() {
    REDispatch.analysisStart(startEventId);
    REDispatch.analysisEnd();
  }

  public static void collectByte(final int slot, final byte b) {
    REDispatch.analysisStart(collectByteId);
    REDispatch.sendInt(slot);
    REDispatch.sendByte(b);
    REDispatch.analysisEnd();
  }

  public static void collectChar(final int slot, final char c) {
    REDispatch.analysisStart(collectCharId);
    REDispatch.sendInt(slot);
    REDispatch.sendChar(c);
    REDispatch.analysisEnd();
  }

  public static void collectShort(final int slot, final short s) {
    REDispatch.analysisStart(collectShortId);
    REDispatch.sendInt(slot);
    REDispatch.sendShort(s);
    REDispatch.analysisEnd();
  }

  public static void collectInt(final int slot, final int i) {
    REDispatch.analysisStart(collectIntId);
    REDispatch.sendInt(slot);
    REDispatch.sendInt(i);
    REDispatch.analysisEnd();
  }

  public static void collectLong(final int slot, final long l) {
    REDispatch.analysisStart(collectLongId);
    REDispatch.sendInt(slot);
    REDispatch.sendLong(l);
    REDispatch.analysisEnd();
  }

  public static void collectFloat(final int slot, final float f) {
    REDispatch.analysisStart(collectFloatId);
    REDispatch.sendInt(slot);
    REDispatch.sendFloat(f);
    REDispatch.analysisEnd();
  }

  public static void collectDouble(final int slot, final double d) {
    REDispatch.analysisStart(collectDoubleId);
    REDispatch.sendInt(slot);
    REDispatch.sendDouble(d);
    REDispatch.analysisEnd();
  }

  public static void collectBoolean(final int slot, final boolean z) {
    REDispatch.analysisStart(collectBooleanId);
    REDispatch.sendInt(slot);
    REDispatch.sendBoolean(z);
    REDispatch.analysisEnd();
  }

  public static void collectString(final int slot, final Object s) {
    REDispatch.analysisStart(collectStringId);
    REDispatch.sendInt(slot);
    REDispatch.sendObject(s);
    REDispatch.analysisEnd();
  }

  public static void collectObject(final int slot, final Object obj) {
    REDispatch.analysisStart(collectObjectId);
    REDispatch.sendInt(slot);
    REDispatch.sendObject(obj);
    REDispatch.analysisEnd();
  }
}
