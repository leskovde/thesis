import ch.usi.dag.dislre.REDispatch;

public class CollectorRE {
  private static final String messageFormat = "[%s]: %s";
  private static final String processName = "Sending process";

  private static short intId = REDispatch.registerMethod("Collector.collectIntArg");

  public static void collectIntArg(final int slot, final int i) {
    printPid();
    REDispatch.analysisStart(intId);
    REDispatch.sendInt(slot);
    REDispatch.sendInt(i);
    REDispatch.analysisEnd();
  }

  private static void printPid() {
    System.out.println(String.format(messageFormat, processName, "PID: " + ProcessHandle.current().pid()));
  }
}
