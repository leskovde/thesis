import ch.usi.dag.dislre.REDispatch;

public class CollectorRE {

  private static short tbId = REDispatch.registerMethod("Collector.testingBasic");

  private static short taId = REDispatch.registerMethod("Collector.testingAdvanced");

  public static void testingBasic(
      final boolean b, final byte by, final char c, final short s, final int i, final long l) {
    REDispatch.analysisStart(tbId);

    REDispatch.sendBoolean(b);
    REDispatch.sendByte(by);
    REDispatch.sendChar(c);
    REDispatch.sendShort(s);
    REDispatch.sendInt(i);
    REDispatch.sendLong(l);

    REDispatch.analysisEnd();
  }

  public static void testingAdvanced(
      final String s, final Object o, final Class<?> c, final Thread t) {
    REDispatch.analysisStart(taId);

    REDispatch.sendObjectPlusData(s);
    REDispatch.sendObject(o);
    REDispatch.sendObject(c);
    REDispatch.sendObjectPlusData(t);

    REDispatch.analysisEnd();
  }
}
