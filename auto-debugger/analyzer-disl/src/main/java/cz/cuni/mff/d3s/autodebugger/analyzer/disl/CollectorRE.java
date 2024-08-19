import ch.usi.dag.dislre.REDispatch;

public class CollectorRE {
  private static final String messageFormat = "[%s]: %s";
  private static final String processName = "Receiving process";

  private static short intId = REDispatch.registerMethod("Collector.collectInt");

  public static void collectInt(final int i) {
    printPid();

    REDispatch.analysisStart(intId);
    REDispatch.sendInt(i);
    REDispatch.analysisEnd();
  }

  private static void printPid() {
    System.out.println(String.format(messageFormat, processName, "PID: " + ProcessHandle.current().pid()));
  }
}
