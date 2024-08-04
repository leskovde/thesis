package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.Identifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Metaclass;
import java.util.List;
import lombok.Getter;

@Getter
public class ShadowDiSLInstrumentationLogic extends DiSLInstrumentationLogic {

  public ShadowDiSLInstrumentationLogic(
      Identifier identifier, DiSLAnnotation annotation, List<ExportableValue> exports) {
    super(identifier, annotation, exports);
  }

  @Override
  public String emitCode() {
    append(annotation.emitCode());
    append("\npublic static void ");
    append(identifier.getName());
    append("(DynamicContext di) {\n");
    for (Metaclass variable : exports) {
      append(variable.emitCode());
      append("\n");
    }
    append(
        "System.out.println(\"[Instrumentation process] PID: \" + ProcessHandle.current().pid());\n");
    append(
        "CollectorRE.testingBasic(true, (byte) 125, 's', (short) 50000, 100000, 10000000000L);\n");
    append("}\n");
    return getCode();
  }
}
