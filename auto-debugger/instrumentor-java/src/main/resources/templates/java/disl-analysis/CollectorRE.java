import ch.usi.dag.dislre.REDispatch;

public class CollectorRE {
  private static short startEventId = REDispatch.registerMethod("Collector.startEvent");
  private static short collectIntId = REDispatch.registerMethod("Collector.collectInt");
  private static short collectDoubleId = REDispatch.registerMethod("Collector.collectDouble");
  private static short collectObjectId = REDispatch.registerMethod("Collector.collectObject");

  public static void startEvent() {
    REDispatch.analysisStart(startEventId);
    REDispatch.analysisEnd();
  }

  public static void collectInt(final int slot, final int i) {
    REDispatch.analysisStart(collectIntId);
    REDispatch.sendInt(slot);
    REDispatch.sendInt(i);
    REDispatch.analysisEnd();
  }

  public static void collectDouble(final int slot, final double d) {
    REDispatch.analysisStart(collectDoubleId);
    REDispatch.sendInt(slot);
    REDispatch.sendDouble(d);
    REDispatch.analysisEnd();
  }

  public static void collectObject(final int slot, final Object obj) {
    REDispatch.analysisStart(collectObjectId);
    REDispatch.sendInt(slot);
    REDispatch.sendObject(obj);
    REDispatch.analysisEnd();
  }
}
