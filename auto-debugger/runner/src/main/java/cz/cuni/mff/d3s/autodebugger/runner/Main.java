package cz.cuni.mff.d3s.autodebugger.runner;

import cz.cuni.mff.d3s.autodebugger.instrumentor.java.DiSLInstrumentor;

public class Main {
  public static void main(String[] args) {
//    if (args.length != 2) {
//      System.out.println("Please provide program path and procedure as arguments.");
//      return;
//    }
//    Arguments arguments = new Arguments(args[0], args[1]);

    // TODO: Select the implementation based on the language
    DiSLInstrumentor instrumentor = DiSLInstrumentor.builder().build();
    instrumentor.runInstrumentation();
  }
}
