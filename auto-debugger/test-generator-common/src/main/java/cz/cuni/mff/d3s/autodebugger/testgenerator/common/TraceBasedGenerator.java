package cz.cuni.mff.d3s.autodebugger.testgenerator.common;

import cz.cuni.mff.d3s.autodebugger.model.common.trace.Trace;

import java.nio.file.Path;
import java.util.List;

public interface TraceBasedGenerator {
    List<Path> generateTests(Trace trace);
}
