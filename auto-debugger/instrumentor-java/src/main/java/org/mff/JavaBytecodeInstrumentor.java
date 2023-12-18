package org.mff;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JavaBytecodeInstrumentor implements BytecodeInstrumentor {
    @Override
    public String loadBytecode(String filePath) {
        try {
            byte[] bytecode = Files.readAllBytes(Paths.get(filePath));
            return new String(bytecode);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load bytecode", e);
        }
    }

    @Override
    public String instrumentCode(String bytecode) {
//        DislInstrumenter instrumenter = new DislInstrumenter();
//        return instrumenter.instrument(bytecode);
        return null;
    }

    @Override
    public void saveBytecode(String instrumentedBytecode, String filePath) {
        try {
            Files.write(Paths.get(filePath), instrumentedBytecode.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save bytecode", e);
        }
    }
}
