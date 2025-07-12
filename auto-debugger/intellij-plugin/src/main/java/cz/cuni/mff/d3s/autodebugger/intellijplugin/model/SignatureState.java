package cz.cuni.mff.d3s.autodebugger.intellijplugin.model;

public enum SignatureState {
    EMPTY,
    INVALID,
    PACKAGE_ONLY,
    CLASS_ONLY,
    METHOD_NAME_ONLY,
    FULL_METHOD
}