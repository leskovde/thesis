package org.mff;

public interface BytecodeInstrumentor {
    String loadBytecode(String filePath);
    String instrumentCode(String bytecode);
    void saveBytecode(String instrumentedBytecode, String filePath);
}