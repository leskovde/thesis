package cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling;

import lombok.Getter;

@Getter
public abstract class Model {
  protected Metaclass rootClass;

  public String transform() {
    return rootClass.emitCode();
  }
}
