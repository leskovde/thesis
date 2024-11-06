package cz.cuni.mff.d3s.autodebugger.instrumentor.java;

import cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifiers.ExportableValue;

import java.util.Map;

public class CollectorMethodRegistry {
    private static final Map<String, String> collectorMethods = Map.of(
            "int", "collectInt"
    );

    public static String getCollectorMethodName(ExportableValue value) {
        return collectorMethods.getOrDefault(value.getType(), "collectObject");
    }
}
