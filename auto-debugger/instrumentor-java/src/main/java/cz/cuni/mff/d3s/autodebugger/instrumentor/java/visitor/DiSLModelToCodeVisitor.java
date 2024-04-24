package cz.cuni.mff.d3s.autodebugger.instrumentor.java.visitor;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.*;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor.ModelToCodeVisitor;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling.*;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.util.Optional;

@Slf4j
public class DiSLModelToCodeVisitor extends ModelToCodeVisitor {

    @Override
    public void visit(PackageClass generalPackage) {
        JavaPackage pack = (JavaPackage) generalPackage;
        append("package ");
        append(pack.getPackageIdentifier().getName());
        append(";");
    }

    @Override
    public void visit(PackageImportClass generalImport) {
        JavaPackageImport imp = (JavaPackageImport) generalImport;
        append("import ");
        append(imp.getImportedPackage().getPackageIdentifier().getName());
        append(";");
    }

    @Override
    public void visit(KlassClass generalClass) {
        DiSLClass dislClass = (DiSLClass) generalClass;
        dislClass.getClassPackage().accept(this);
        append("\n\n");
        for (JavaPackageImport imp : dislClass.getImports()) {
            imp.accept(this);
            append("\n");
        }
        append("\npublic class " + dislClass.CLASS_NAME + " {\n");
        indentLevel++;
        for (DiSLInstrumentationLogic method : dislClass.getLogic()) {
            append("\n");
            method.accept(this);
        }
        indentLevel--;
        append("\n}\n");
    }

    @Override
    public void visit(InstrumentationLogicClass generalLogic) {
        DiSLInstrumentationLogic method = (DiSLInstrumentationLogic) generalLogic;
        method.getAnnotation().accept(this);
        append("\npublic void ");
        append(method.getIdentifier().getName());
        append("(DynamicContext di) {\n");
        indentLevel++;
        for (JavaVariable variable : method.getExports()) {
            variable.accept(this);
        }
        indentLevel--;
        append("\n}");
    }

    @Override
    public void visit(AnnotationClass generalAnnotation) {
        DiSLAnnotation annotation = (DiSLAnnotation) generalAnnotation;
        append("@");
        append(annotation.getActivationTime().annotationCode);
        append("(");
        annotation.getMarker().accept(this);
        append(", ");
        annotation.getTargetMethod().accept(this);
        append(")");
    }

    @Override
    public void visit(InstrumentationMarkerClass generalMarker) {
        DiSLMarker marker = (DiSLMarker) generalMarker;
        append("marker = ");
        append(marker.getMarkerType().markerName);
        append(".class");
    }

    @Override
    public void visit(ScopeClass generalScope) {
        DiSLScope scope = (DiSLScope) generalScope;
        append("scope = \"");
        append(scope.getMethodIdentifier().getName());
        append("\"");
    }

    @Override
    public void visit(VariableClass generalVariable) {
        JavaVariable variable = (JavaVariable) generalVariable;
        append("\n");
        append(variable.getType());
        append(" ");
        append(variable.getVariableIdentifier().getName());
        append(" = ");
        // TODO: Get arguments instead of locals
        append("di.getLocalVariableValue(");
        append(Integer.toString(variable.getFrameSlot()));
        append(", ");
        append(variable.getType());
        append(".class);");
    }

    @Override
    public void visit(Metaclass visitable) {
        throw new UnsupportedOperationException("Metaclass should not be visited directly.");
    }

    @Override
    public Optional<String> writeGeneratedCode() {
        var code = stringBuilder.toString();
        try {
            log.info("Generating DiSL class");

            String path = "analyzer-disl/src/main/java/cz/cuni/mff/d3s/autodebugger/analyzer/disl/";
            String fileName = "DiSLClass.java";
            File dislClassFile = new File(path, fileName);
            try (FileWriter writer = new FileWriter(dislClassFile)) {
                writer.write(code);
            }
            log.info("DiSL class generated");
            return Optional.of(dislClassFile.getParentFile().getAbsolutePath());
        } catch (Exception e) {
            log.error("Failed to generate DiSL class", e);
            return Optional.empty();
        }
    }
}
