package cz.cuni.mff.d3s.autodebugger.instrumentor.common;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.InstrumentationModel;

import java.nio.file.Path;
import java.util.List;

public interface Instrumentor {

    List<Path> generateInstrumentation(InstrumentationModel model);
}
