package cz.cuni.mff.d3s.autodebugger.model.common.identifiers;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class MethodIdentifier implements Identifier {
    protected final String methodName;
    protected final String returnType;
    protected final List<String> parameterTypes;

    public String getName() {
        return methodName;
    }
}
