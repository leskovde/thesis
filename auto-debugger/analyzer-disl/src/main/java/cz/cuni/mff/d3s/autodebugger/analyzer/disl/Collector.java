import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import ch.usi.dag.dislreserver.remoteanalysis.RemoteAnalysis;
import ch.usi.dag.dislreserver.shadow.ShadowObject;
import cz.cuni.mff.d3s.autodebugger.testgenerator.java.trace.TraceBasedUnitTestGenerator;

public class Collector extends RemoteAnalysis {
  private final String messageFormat = "[%s]: %s";
  private final String processName = "Receiving process";

  private Map<Integer, Set<Integer>> integerSlotValues = new HashMap<>();

  public void collectByteArg(final int slot, final byte b) {
    printPid();
    System.out.println("Collected byte: " + b + " from slot: " + slot);
  }

  public void collectCharArg(final int slot, final char c) {
    printPid();
    System.out.println("Collected char: " + c + " from slot: " + slot);
  }

  public void collectShortArg(final int slot, final short s) {
    printPid();
    System.out.println("Collected short: " + s + " from slot: " + slot);
  }

  public void collectIntArg(final int slot, final int i) {
    printPid();
    if (integerSlotValues.containsKey(slot)) {
      integerSlotValues.get(slot).add(i);
    } else {
      integerSlotValues.put(slot, new HashSet<>(Arrays.asList(i)));
    }
    System.out.println("Collected int: " + i + " from slot: " + slot);
  }

  public void collectLongArg(final int slot, final long l) {
    printPid();
    System.out.println("Collected long: " + l + " from slot: " + slot);
  }

  public void collectFloatArg(final int slot, final float f) {
    printPid();
    System.out.println("Collected float: " + f + " from slot: " + slot);
  }

  public void collectDoubleArg(final int slot, final double d) {
    printPid();
    System.out.println("Collected double: " + d + " from slot: " + slot);
  }

  public void collectBooleanArg(final int slot, final boolean z) {
    printPid();
    System.out.println("Collected boolean: " + z + " from slot: " + slot);
  }

  private void printPid() {
    System.out.println(
        String.format(messageFormat, processName, "PID: " + ProcessHandle.current().pid()));
  }

  @Override
  public void atExit() {
    System.out.println(String.format(messageFormat, processName, "Exiting analysis..."));

    for (Map.Entry<Integer, Set<Integer>> entry : integerSlotValues.entrySet()) {
      System.out.println("Slot: " + entry.getKey() + " values: " + entry.getValue());
    }

    TraceBasedUnitTestGenerator generator = new TraceBasedUnitTestGenerator();
    generator.generateUnitTests();
  }

  @Override
  public void objectFree(final ShadowObject netRef) {
    System.out.println("Object free for id " + netRef.getId());
  }
}
