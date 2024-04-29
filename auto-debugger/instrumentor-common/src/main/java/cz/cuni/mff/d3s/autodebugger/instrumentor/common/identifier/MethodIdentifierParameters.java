package cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier;

import java.util.List;
import lombok.Builder;

@Builder
public class MethodIdentifierParameters {
  public String className;
  public String methodName;
  public String returnType;
  public List<String> parameterTypes;
}
