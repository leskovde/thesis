package cz.cuni.mff.d3s.autodebugger.testgenerator.java.trace;

import cz.cuni.mff.d3s.autodebugger.model.java.Trace;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.ArgumentIdentifier;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.ExportableValue;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@Slf4j
public class TraceIdentifierMapper {
    private final Trace trace;
    private final Map<Integer, ExportableValue> identifierMapping;
    private final Map<String, Function<Integer, Set<?>>> valueGetters;

    public TraceIdentifierMapper(Trace trace, Map<Integer, ExportableValue> identifierMapping) {
        this.trace = trace;
        this.identifierMapping = identifierMapping;
        this.valueGetters = Map.of(
                "byte", trace::getByteValues,
                "char", trace::getCharValues,
                "short", trace::getShortValues,
                "int", trace::getIntValues,
                "long", trace::getLongValues,
                "float", trace::getFloatValues,
                "double", trace::getDoubleValues,
                "boolean", trace::getBooleanValues
        );
    }

    public Set<Integer> getSlots() {
        return identifierMapping.keySet();
    }

    public ExportableValue getExportableValue(int id) {
        var value = identifierMapping.get(id);
        if (value instanceof ArgumentIdentifier arg) {
            log.info("Argument identifier: {}, {}, {}, {}", arg.getArgumentSlot(), arg.getInternalId(), arg.getType(), arg.getName());
        }
        return value;
    }

    public Set<?> getSlotValues(int slot) {
        var type = getExportableValue(slot).getType();
        var getter = valueGetters.get(type);
        if (getter != null) {
            return getter.apply(slot);
        } else {
            throw new IllegalArgumentException("Unsupported type: " + type);
        }
    }
}
