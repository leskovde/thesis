package cz.cuni.mff.d3s.autodebugger.runner;

import picocli.CommandLine;

public class Arguments {
    @CommandLine.Option(names = { "-j", "--jar" }, paramLabel = "JAR", description = "Path to the application JAR file", required = true)
    public String applicationJarPath;

    @CommandLine.Option(names = { "-r", "--return" }, paramLabel = "RETURN", description = "Return value of the method to be analyzed", required = true)
    public String methodReturnValue;

    @CommandLine.Option(names = { "-c", "--class" }, paramLabel = "CLASS", description = "Class containing the method to be analyzed", required = true)
    public String methodClassName;

    @CommandLine.Option(names = { "-m", "--method" }, paramLabel = "METHOD", description = "Method to be analyzed", required = true)
    public String methodName;

    @CommandLine.Parameters(paramLabel = "TYPES", description = "Method parameter types")
    public String[] parameterTypes;

    @CommandLine.Option(names = { "-h", "--help" }, usageHelp = true, description = "display a help message")
    private boolean helpRequested = false;
}