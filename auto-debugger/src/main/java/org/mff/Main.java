package org.mff;

public class Main {
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("Please provide program path and procedure as arguments.");
      return;
    }
    Arguments arguments = new Arguments(args[0], args[1]);

    // TODO: Select the implementation based on the language
    BytecodeInstrumentor instrumentor = new JavaBytecodeInstrumentor();
    String bytecode = instrumentor.loadBytecode(arguments.getProgramPath());
    String instrumentedBytecode = instrumentor.instrumentCode(bytecode);
    instrumentor.saveBytecode(instrumentedBytecode, arguments.getProgramPath());
  }
}
