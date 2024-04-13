package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Metaclass;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DiSLClass extends Metaclass {
    private Package classPackage;
    private List<Import> imports;
    private List<InstrumentationMethod> logic;
}
