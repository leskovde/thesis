import ch.usi.dag.dislreserver.remoteanalysis.RemoteAnalysis;
import ch.usi.dag.dislreserver.shadow.ShadowObject;
import cz.cuni.mff.d3s.autodebugger.analyzer.Trace
import cz.cuni.mff.d3s.autodebugger.testgenerator.java.trace.TraceBasedUnitTestGenerator;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Collector extends RemoteAnalysis {
  private final String messageFormat = "[%s]: %s";
  private final String processName = "Receiving process";

  private Map<Integer, Set<Byte>> byteSlotValues = new HashMap<>();
  private Map<Integer, Set<Character>> charSlotValues = new HashMap<>();
  private Map<Integer, Set<Short>> shortSlotValues = new HashMap<>();
  private Map<Integer, Set<Integer>> intSlotValues = new HashMap<>();
  private Map<Integer, Set<Long>> longSlotValues = new HashMap<>();
  private Map<Integer, Set<Float>> floatSlotValues = new HashMap<>();
  private Map<Integer, Set<Double>> doubleSlotValues = new HashMap<>();
  private Map<Integer, Set<Boolean>> booleanSlotValues = new HashMap<>();

  public void collectByteArg(final int slot, final byte b) {
    printPid();
    if (byteSlotValues.containsKey(slot)) {
      byteSlotValues.get(slot).add(b);
    } else {
      byteSlotValues.put(slot, new HashSet<>(Arrays.asList(b)));
    }
    System.out.println("Collected byte: " + b + " from slot: " + slot);
  }

  public void collectCharArg(final int slot, final char c) {
    printPid();
    if (charSlotValues.containsKey(slot)) {
      charSlotValues.get(slot).add(c);
    } else {
      charSlotValues.put(slot, new HashSet<>(Arrays.asList(c)));
    }
    System.out.println("Collected char: " + c + " from slot: " + slot);
  }

  public void collectShortArg(final int slot, final short s) {
    printPid();
    if (shortSlotValues.containsKey(slot)) {
      shortSlotValues.get(slot).add(s);
    } else {
      shortSlotValues.put(slot, new HashSet<>(Arrays.asList(s)));
    }
    System.out.println("Collected short: " + s + " from slot: " + slot);
  }

  public void collectIntArg(final int slot, final int i) {
    printPid();
    if (intSlotValues.containsKey(slot)) {
      intSlotValues.get(slot).add(i);
    } else {
      intSlotValues.put(slot, new HashSet<>(Arrays.asList(i)));
    }
    System.out.println("Collected int: " + i + " from slot: " + slot);
  }

  public void collectLongArg(final int slot, final long l) {
    printPid();
    if (longSlotValues.containsKey(slot)) {
      longSlotValues.get(slot).add(l);
    } else {
      longSlotValues.put(slot, new HashSet<>(Arrays.asList(l)));
    }
    System.out.println("Collected long: " + l + " from slot: " + slot);
  }

  public void collectFloatArg(final int slot, final float f) {
    printPid();
    if (floatSlotValues.containsKey(slot)) {
      floatSlotValues.get(slot).add(f);
    } else {
      floatSlotValues.put(slot, new HashSet<>(Arrays.asList(f)));
    }
    System.out.println("Collected float: " + f + " from slot: " + slot);
  }

  public void collectDoubleArg(final int slot, final double d) {
    printPid();
    if (doubleSlotValues.containsKey(slot)) {
      doubleSlotValues.get(slot).add(d);
    } else {
      doubleSlotValues.put(slot, new HashSet<>(Arrays.asList(d)));
    }
    System.out.println("Collected double: " + d + " from slot: " + slot);
  }

  public void collectBooleanArg(final int slot, final boolean z) {
    printPid();
    if (booleanSlotValues.containsKey(slot)) {
      booleanSlotValues.get(slot).add(z);
    } else {
      booleanSlotValues.put(slot, new HashSet<>(Arrays.asList(z)));
    }
    System.out.println("Collected boolean: " + z + " from slot: " + slot);
  }

  private void printPid() {
    System.out.println(
        String.format(messageFormat, processName, "PID: " + ProcessHandle.current().pid()));
  }

  private void printSlotValues() {
    printSlotValues(byteSlotValues);
    printSlotValues(charSlotValues);
    printSlotValues(shortSlotValues);
    printSlotValues(intSlotValues);
    printSlotValues(longSlotValues);
    printSlotValues(floatSlotValues);
    printSlotValues(doubleSlotValues);
    printSlotValues(booleanSlotValues);
  }

  private <T> void printSlotValues(final Map<Integer, Set<T>> slotValues) {
    for (Map.Entry<Integer, Set<T>> entry : slotValues.entrySet()) {
      System.out.println("Slot: " + entry.getKey() + " values: " + entry.getValue());
    }
  }

  @Override
  public void atExit() {
    System.out.println(String.format(messageFormat, processName, "Exiting analysis..."));
    printSlotValues();
    TraceBasedUnitTestGenerator generator = new TraceBasedUnitTestGenerator();
    generator.generateUnitTests();
  }

  @Override
  public void objectFree(final ShadowObject netRef) {
    System.out.println("Object free for id " + netRef.getId());
  }
}
