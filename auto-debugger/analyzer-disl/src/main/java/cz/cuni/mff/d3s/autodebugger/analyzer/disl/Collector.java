import ch.usi.dag.dislreserver.remoteanalysis.RemoteAnalysis;
import ch.usi.dag.dislreserver.shadow.ShadowClass;
import ch.usi.dag.dislreserver.shadow.ShadowObject;
import ch.usi.dag.dislreserver.shadow.ShadowString;
import ch.usi.dag.dislreserver.shadow.ShadowThread;

public class Collector extends RemoteAnalysis {
  private final String messageFormat = "[%s]: %s";
  private final String processName = "Receiving process";

  public void collectInt(final int i) {
    printPid();
    System.out.println("Collected int: " + i);
  }

  private void printPid() {
    System.out.println(String.format(messageFormat, processName, "PID: " + ProcessHandle.current().pid()));
  }

  @Override
  public void atExit() {
    System.out.println(String.format(messageFormat, processName, "Exiting analysis..."));
  }

  @Override
  public void objectFree(final ShadowObject netRef) {
    System.out.println("Object free for id " + netRef.getId());
  }
}
