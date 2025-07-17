package cz.cuni.mff.d3s.autodebugger.model.common.identifiers;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Abstract base class for method identifiers across different programming languages.
 * Provides common method identification properties including name, return type,
 * and parameter types while allowing language-specific extensions.
 */
@Getter
@RequiredArgsConstructor
public abstract class MethodIdentifier implements Identifier, Serializable {
    protected final String methodName;
    protected final String returnType;
    protected final List<String> parameterTypes;

    public String getName() {
        return methodName;
    }
}
