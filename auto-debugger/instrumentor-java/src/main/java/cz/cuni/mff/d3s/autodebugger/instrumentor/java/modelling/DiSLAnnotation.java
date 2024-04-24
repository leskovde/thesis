package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.AnnotationClass;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor.ModelVisitor;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling.enums.ActivationTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiSLAnnotation extends AnnotationClass {
    private ActivationTime activationTime;
    private DiSLMarker marker;
    private DiSLScope targetMethod;

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }
}
