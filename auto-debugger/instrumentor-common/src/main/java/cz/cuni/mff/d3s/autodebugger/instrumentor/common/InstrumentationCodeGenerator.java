package cz.cuni.mff.d3s.autodebugger.instrumentor.common;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Model;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor.ModelToCodeVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor
public abstract class InstrumentationCodeGenerator {
    protected Model instrumentationModel;
    public abstract Optional<String> generateCode(ModelToCodeVisitor codeGenerator);
}
