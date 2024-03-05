package cz.cuni.mff.d3s.autodebugger.instrumentor.common.enums;

import lombok.Getter;

import java.util.List;

@Getter
public enum Visibility {
    PUBLIC,
    PROTECTED,
    PACKAGE,
    PRIVATE;

    private static final List<Visibility> all = List.of(PUBLIC, PROTECTED, PACKAGE, PRIVATE);
    private static final List<Visibility> none = List.of();
}
