package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.Identifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Metaclass;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
public class DiSLInstrumentationLogic extends Metaclass {
    private final Identifier identifier;
    private final DiSLAnnotation annotation;
    protected List<ExportableValue> exports;

    @Override
    public String emitCode(int indentLevel) {
        append(annotation.emitCode(indentLevel));
        append("\npublic static void ");
        append(identifier.getName());
        append("(DynamicContext di) {\n");
        for (Metaclass variable : exports) {
            append(variable.emitCode(indentLevel + 1));
        }
        append("\n}");
        return getCode();
    }
}
