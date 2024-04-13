package cz.cuni.mff.d3s.autodebugger.instrumentor.java.visitor;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor.ModelToCodeVisitor;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling.*;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling.Package;

public class DiSLModelToCodeVisitor extends ModelToCodeVisitor {

    public void visit(Package pack) {
        append("package ");
        append(pack.getPackageIdentifier().getName());
        append(";\n\n");
    }

    public void visit(Import imp) {
        append("import ");
        append(imp.getImportedPackage().getPackageIdentifier().getName());
        append(";\n");
    }

    public void visit(DiSLClass dislClass) {
        dislClass.getClassPackage().accept(this);
        for (Import imp : dislClass.getImports()) {
            imp.accept(this);
        }
        append("\npublic class DiSLClass {\n");
        for (InstrumentationMethod method : dislClass.getLogic()) {
            method.accept(this);
        }
        append("}\n");
    }

    public void visit(InstrumentationMethod method) {
        method.getAnnotation().accept(this);
        append("public void ");
        append(method.getIdentifier().getName());
        append("(DynamicContext di) {\n");
        for (Variable variable : method.getExports()) {
            variable.accept(this);
        }
        append("}\n");
    }

    public void visit(Annotation annotation) {
        append("@");
        append(annotation.getActivationTime().annotationCode);
        append("(");
        annotation.getMarker().accept(this);
        annotation.getTargetMethod().accept(this);
        append(")");
    }

    public void visit(Marker marker) {
        append("marker = ");
        append(marker.getMarkerType().markerName);
        append(".class, ");
    }

    public void visit(Scope scope) {
        append("scope = \"");
        append(scope.getMethodIdentifier().getName());
        append("\", ");
    }

    public void visit(Variable variable) {
        append(variable.getType().getSimpleName());
        append(" ");
        append(variable.getVariableIdentifier().getName());
        append(" = ");
        // TODO: Get arguments instead of locals
        append(" di.getLocalVariableValue(");
        append(Integer.toString(variable.getFrameSlot()));
        append(", ");
        append(variable.getType().getSimpleName());
        append(");\n");
    }
}
