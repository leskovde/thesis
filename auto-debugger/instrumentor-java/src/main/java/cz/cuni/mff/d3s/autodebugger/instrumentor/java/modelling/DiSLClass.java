package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Metaclass;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DiSLClass extends Metaclass {
  private final String CLASS_NAME = "DiSLClass";
  protected List<DiSLInstrumentationLogic> instrumentationMethods;
  private List<JavaPackageImport> imports;

  @Override
  public String emitCode() {
    for (JavaPackageImport imp : imports) {
      append(imp.emitCode());
      append("\n");
    }
    append("\npublic class " + CLASS_NAME + " {\n");
    for (DiSLInstrumentationLogic method : instrumentationMethods) {
      append(method.emitCode());
    }
    append("\n}\n");
    return getCode();
  }
}
