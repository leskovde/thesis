import ch.usi.dag.disl.annotation.After;
import ch.usi.dag.disl.marker.BodyMarker;

public class DiSLClass {

  @After(marker = BodyMarker.class, scope = "Main.main")
  public static void aftermain() {
    System.out.println("Instrumentation: After method main");
    CollectorRE.testingBasic(true, (byte) 125, 's', (short) 50000, 100000, 10000000000L);
    CollectorRE.testingAdvanced(
        "Corect transfer of String", "test", Object.class, Thread.currentThread());
  }
}
