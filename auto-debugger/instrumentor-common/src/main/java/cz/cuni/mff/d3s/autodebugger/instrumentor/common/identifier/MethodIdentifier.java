package cz.cuni.mff.d3s.autodebugger.instrumentor.common.identifier;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MethodIdentifier implements Identifier {
    private final String className;
    private final String methodName;
    private final String returnType;
    private final List<String> parameterTypes;

    public String getName() {
        return className + "." + methodName;
    }
}
