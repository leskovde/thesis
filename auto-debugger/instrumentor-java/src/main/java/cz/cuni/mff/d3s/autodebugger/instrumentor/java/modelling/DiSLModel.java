package cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.Instrumentor;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.factories.IdentifierFactory;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.MethodIdentifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier.VariableIdentifier;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Model;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor.ModelVisitor;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling.enums.ActivationTime;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling.enums.MarkerType;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class DiSLModel extends Model {
  private final String DEFAULT_PACKAGE_NAME = "cz.cuni.mff.d3s.autodebugger.analyzer.disl";
  private final JavaPackage DEFAULT_PACKAGE =
      new JavaPackage(IdentifierFactory.createFrom(DEFAULT_PACKAGE_NAME));
  private final List<String> DISL_LIBRARY_IMPORTS =
      List.of(
          "ch.usi.dag.disl.annotation.After",
          "ch.usi.dag.disl.annotation.Before",
          "ch.usi.dag.disl.dynamiccontext.DynamicContext",
          "ch.usi.dag.disl.marker.BodyMarker");
  private final List<String> JAVA_IMPORTS =
      List.of("java.io.FileNotFoundException", "java.io.IOException", "java.io.RandomAccessFile");

  public DiSLModel(Instrumentor instrumentor) {
    var classBuilder = DiSLClass.builder().classPackage(DEFAULT_PACKAGE);
    List<JavaPackageImport> imports =
        Stream.concat(DISL_LIBRARY_IMPORTS.stream(), JAVA_IMPORTS.stream())
            .map(IdentifierFactory::createFrom)
            .map(JavaPackageImport::new)
            .toList();
    classBuilder.imports(imports);
    List<JavaVariable> exports = new ArrayList<>();
    for (var identifier : instrumentor.getVariables()) {
      if (identifier instanceof VariableIdentifier variableIdentifier) {
        String variableType = variableIdentifier.getType();
        // TODO: Determine frame slot
        exports.add(new JavaVariable(identifier, variableType, 0));
        } else {
        log.error("Variable {} is not a VariableIdentifier", identifier);
      }
    }
    List<DiSLInstrumentationLogic> logic = new ArrayList<>();
    for (var i = 0; i < instrumentor.getMethods().size(); i++) {
      var method = instrumentor.getMethods().get(i);
      var methodIdentifier = MethodIdentifier.builder()
              .returnType("void")
              .className("DiSLClass")
              .methodName("instrumentationMethod" + i)
              .parameterTypes(List.of("DynamicContext"))
              .build();
      var annotation =
          new DiSLAnnotation(ActivationTime.BEFORE, new DiSLMarker(MarkerType.BODY), new DiSLScope(method));
        logic.add(new DiSLInstrumentationLogic(methodIdentifier, annotation, exports));
    }
    classBuilder.logic(logic);
    rootClass = classBuilder.build();
  }

  public void accept(ModelVisitor visitor) {
    visitor.visit(rootClass);
  }
}
