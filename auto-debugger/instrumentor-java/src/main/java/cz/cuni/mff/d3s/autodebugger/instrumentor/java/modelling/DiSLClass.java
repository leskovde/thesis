package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Metaclass;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DiSLClass extends Metaclass {
    private final String CLASS_NAME = "DiSLClass";
    private JavaPackage classPackage;
    private List<JavaPackageImport> imports;
    protected List<DiSLInstrumentationLogic> logic;

    @Override
    public String emitCode() {
        append(classPackage.emitCode());
        append("\n\n");
        for (JavaPackageImport imp : imports) {
            append(imp.emitCode());
            append("\n");
        }
        append("\npublic class " + CLASS_NAME + " {\n");
        for (DiSLInstrumentationLogic method : logic) {
            append("\n");
            append(method.emitCode());
        }
        append("\n}\n");
        return getCode();
    }
}
