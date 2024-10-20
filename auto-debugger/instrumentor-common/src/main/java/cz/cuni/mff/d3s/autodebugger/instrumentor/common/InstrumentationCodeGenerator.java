package cz.cuni.mff.d3s.autodebugger.instrumentor.common;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.nio.file.Path;
import java.util.Optional;

@Getter
@AllArgsConstructor
public abstract class InstrumentationCodeGenerator {
    protected Path outputDirectory;
    protected Model instrumentationModel;
    public abstract Optional<Path> generateCode();
}
