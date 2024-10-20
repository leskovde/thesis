import ch.usi.dag.dislreserver.remoteanalysis.RemoteAnalysis;
import ch.usi.dag.dislreserver.shadow.ShadowObject;
import ch.usi.dag.dislreserver.shadow.ShadowString;
import cz.cuni.mff.d3s.autodebugger.analyzer.Trace;
import cz.cuni.mff.d3s.autodebugger.testgenerator.java.trace.TraceBasedUnitTestGenerator;

public class Collector extends RemoteAnalysis {
  private final String messageFormat = "[%s]: %s";
  private final String processName = "Receiving process";

  private Trace trace = new Trace();

  public void collectByteArg(final int slot, final byte b) {
    printPid();
    trace.addByteArgValue(slot, b);
    System.out.println("Collected byte: " + b + " from slot: " + slot);
  }

  public void collectCharArg(final int slot, final char c) {
    printPid();
    trace.addCharArgValue(slot, c);
    System.out.println("Collected char: " + c + " from slot: " + slot);
  }

  public void collectShortArg(final int slot, final short s) {
    printPid();
    trace.addShortArgValue(slot, s);
    System.out.println("Collected short: " + s + " from slot: " + slot);
  }

  public void collectIntArg(final int slot, final int i) {
    printPid();
    trace.addIntArgValue(slot, i);
    System.out.println("Collected int: " + i + " from slot: " + slot);
  }

  public void collectLongArg(final int slot, final long l) {
    printPid();
    trace.addLongArgValue(slot, l);
    System.out.println("Collected long: " + l + " from slot: " + slot);
  }

  public void collectFloatArg(final int slot, final float f) {
    printPid();
    trace.addFloatArgValue(slot, f);
    System.out.println("Collected float: " + f + " from slot: " + slot);
  }

  public void collectDoubleArg(final int slot, final double d) {
    printPid();
    trace.addDoubleArgValue(slot, d);
    System.out.println("Collected double: " + d + " from slot: " + slot);
  }

  public void collectBooleanArg(final int slot, final boolean z) {
    printPid();
    trace.addBooleanArgValue(slot, z);
    System.out.println("Collected boolean: " + z + " from slot: " + slot);
  }

  public void collectIntInstanceField(
      final ShadowObject ownerType, final ShadowObject fieldName, final int value) {
    printPid();

    if (!(ownerType instanceof ShadowString)) {
      throw new RuntimeException("ShadowObject ownerType should be transfered as string");
    }
    if (!(fieldName instanceof ShadowString)) {
      throw new RuntimeException("ShadowObject fieldName should be transfered as string");
    }

    String ownerTypeString = ownerType.toString();
    String fieldNameString = fieldName.toString();
    trace.addIntInstanceFieldValue(ownerTypeString, fieldNameString, value);
    System.out.println(
        "Collected int: "
            + value
            + " from instance field: "
            + fieldNameString
            + " in object: "
            + ownerTypeString);
  }

  private void printPid() {
    System.out.println(
        String.format(messageFormat, processName, "PID: " + ProcessHandle.current().pid()));
  }

  @Override
  public void atExit() {
    System.out.println(String.format(messageFormat, processName, "Exiting analysis..."));
    trace.printSlotValues();
    trace.printInstanceFieldValues();
    TraceBasedUnitTestGenerator generator = new TraceBasedUnitTestGenerator();
    generator.generateTests(trace);
  }

  @Override
  public void objectFree(final ShadowObject netRef) {
    System.out.println("Object free for id " + netRef.getId());
  }
}
