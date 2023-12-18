package org.mff;

public class Arguments {
    private String programPath;
    private String procedure;

    public Arguments(String programPath, String procedure) {
        this.programPath = programPath;
        this.procedure = procedure;
    }

    public String getProgramPath() {
        return programPath;
    }

    public String getProcedure() {
        return procedure;
    }
}