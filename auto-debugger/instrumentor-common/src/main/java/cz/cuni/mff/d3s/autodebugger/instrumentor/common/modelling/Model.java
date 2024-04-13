package cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor.ModelVisitor;
import lombok.Getter;

@Getter
public abstract class Model {
  protected Metaclass rootClass;
  public abstract void accept(ModelVisitor visitor);
}
