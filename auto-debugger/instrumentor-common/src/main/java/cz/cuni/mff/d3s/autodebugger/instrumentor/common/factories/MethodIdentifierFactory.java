package cz.cuni.mff.d3s.autodebugger.instrumentor.common.factories;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.MethodIdentifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.MethodIdentifierParameters;
import lombok.Getter;

public class MethodIdentifierFactory extends IdentifierFactory {
    @Getter
    private static final MethodIdentifierFactory instance = new MethodIdentifierFactory();

    private MethodIdentifierFactory() {
    }

    public MethodIdentifier createIdentifier(MethodIdentifierParameters parameters) {
//      return MethodIdentifier.builder()
//              .returnType(name.substring(0, name.indexOf(' ')))
//              .className(name.substring(name.indexOf(' ') + 1, name.lastIndexOf('.')))
//              .methodName(name.substring(name.lastIndexOf('.') + 1, name.indexOf('(')))
//              .parameterTypes(Arrays.asList(Arrays.stream(name.substring(name.indexOf('(') + 1, name.indexOf(')')).split(",")).filter(s -> !s.isEmpty()).toArray(String[]::new)))
//              .build();
        return new MethodIdentifier(parameters);
    }

    public MethodIdentifier generateIdentifier(MethodIdentifierParameters parameters) {
        parameters.methodName = "generatedMethod" + id++;
        return new MethodIdentifier(parameters);
    }
}
