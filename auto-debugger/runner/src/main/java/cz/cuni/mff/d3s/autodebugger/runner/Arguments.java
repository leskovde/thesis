package cz.cuni.mff.d3s.autodebugger.runner;

import picocli.CommandLine;
import java.util.List;

public class Arguments {
    @CommandLine.Option(names = { "-j", "--jar" }, paramLabel = "JAR", description = "Path to the application JAR file", required = true)
    public String applicationJarPath;

    @CommandLine.Option(names = { "-s", "--source" }, paramLabel = "SOURCE", description = "Path to the target application's source code", required = true)
    public String sourceCodePath;

    @CommandLine.Option(names = { "-m", "--method" }, paramLabel = "METHOD", description = "Target method reference (e.g., org.example.Main.main(String[]))", required = true)
    public String targetMethodReference;

    @CommandLine.Option(names = { "-p", "--parameters" }, paramLabel = "PARAMETERS", description = "Target method parameters (format: type:name or slot:type)", split = ",")
    public List<String> targetParameters;

    @CommandLine.Option(names = { "-f", "--fields" }, paramLabel = "FIELDS", description = "Target class fields (format: type:name)", split = ",")
    public List<String> targetFields;

    @CommandLine.Option(names = { "-h", "--help" }, usageHelp = true, description = "display a help message")
    private boolean helpRequested = false;
}