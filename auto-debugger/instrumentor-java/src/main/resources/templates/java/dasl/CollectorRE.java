import ch.usi.dag.dislre.REDispatch;

public class CollectorRE {
  private static final String messageFormat = "[%s]: %s";
  private static final String processName = "Sending process";

  private static short intId = REDispatch.registerMethod("Collector.collectInt");
  private static short startEventId = REDispatch.registerMethod("Collector.startEvent");

  public static void startEvent() {
    printPid();
    REDispatch.analysisStart(startEventId);
    REDispatch.analysisEnd();
  }

  public static void collectInt(final int slot, final int i) {
    printPid();
    REDispatch.analysisStart(intId);
    REDispatch.sendInt(slot);
    REDispatch.sendInt(i);
    REDispatch.analysisEnd();
  }

  private static void printPid() {
    String pid = java.lang.management.ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
    System.out.println(String.format(messageFormat, processName, String.format("PID: %s", pid)));
  }
}

