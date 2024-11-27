package cz.cuni.mff.d3s.autodebugger.model.java.identifiers;

public class ReturnValueIdentifierParameters extends ExportableValueIdentifierParameters {
    public final MethodIdentifier methodIdentifier;

    public ReturnValueIdentifierParameters(MethodIdentifier methodIdentifier) {
        super(null, methodIdentifier.getReturnType());
        this.methodIdentifier = methodIdentifier;
    }
}
