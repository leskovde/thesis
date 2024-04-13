package cz.cuni.mff.d3s.autodebugger.instrumentor.common.factories;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.Identifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.MethodIdentifier;

import java.util.Arrays;

public class MethodIdentifierFactory extends IdentifierFactory {
    @Override
    public Identifier createIdentifier(String name) {
      return MethodIdentifier.builder()
              .className(name.substring(0, name.lastIndexOf('.')))
              .methodName(name.substring(name.lastIndexOf('.') + 1, name.indexOf('(')))
              .returnType(name.substring(name.indexOf(')') + 1))
              .parameterTypes(Arrays.asList(name.substring(name.indexOf('(') + 1, name.indexOf(')')).split(",")))
              .build();
    }
}
