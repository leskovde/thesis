package cz.cuni.mff.d3s.autodebugger.instrumentor.java;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.InstrumentationCodeGenerator;

import java.io.File;
import java.io.FileWriter;
import java.util.Optional;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Model;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.visitor.ModelToCodeVisitor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DiSLClassGenerator extends InstrumentationCodeGenerator {

  public DiSLClassGenerator(Model instrumentationModel) {
    super(instrumentationModel);
  }

  @Override
  public Optional<String> generateCode(ModelToCodeVisitor codeGenerator) {
    codeGenerator.visit(instrumentationModel);
    var code = codeGenerator.getGeneratedCode();
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
