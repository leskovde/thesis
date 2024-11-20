package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.Identifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Metaclass;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public abstract class DiSLInstrumentationLogic extends Metaclass {
    protected final Identifier identifier;
    protected final DiSLAnnotation annotation;
    protected List<JavaValue> exports;
}
