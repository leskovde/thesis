package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import lombok.Getter;

@Getter
public class JavaField extends JavaValue {
    private final String name;
    private final String ownerType;

    public JavaField(String type, String name, String ownerType) {
        super(type);
        this.name = name;
        // TODO: Use fully qualified name for ownerType
        this.ownerType = ownerType;
    }

    // TODO: Static fields
    @Override
    public String emitCode() {
    // dc.getThis (), TargetClass.class, "instName", String.class
        append(type);
        append(" ");
        append(instrumentationVariableIdentifier.getName());
        append(" = ");
        append("di.getInstanceFieldValue(di.getThis(), ");
        append(ownerType);
        append(".class, \"");
        append(name);
        append("\", ");
        append(type);
        append(".class);");
        return getCode();
    }
}
