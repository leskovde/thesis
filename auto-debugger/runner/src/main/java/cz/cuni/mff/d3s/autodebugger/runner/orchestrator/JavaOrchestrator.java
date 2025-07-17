package cz.cuni.mff.d3s.autodebugger.runner.orchestrator;

import cz.cuni.mff.d3s.autodebugger.runner.args.Arguments;

/**
 * Java-specific orchestrator implementation.
 * This is a specialized version of the Orchestrator for Java language support.
 * Currently, it delegates to the base Orchestrator class but provides a specific
 * type for testing and future Java-specific customizations.
 */
public class JavaOrchestrator extends Orchestrator {

    public JavaOrchestrator(Arguments arguments) {
        super(arguments);
    }

    /**
     * Default constructor for testing purposes.
     * Creates a JavaOrchestrator with dummy arguments.
     */
    public JavaOrchestrator() {
        super(createDummyJavaArguments());
    }

    /**
     * Creates dummy arguments for Java testing.
     */
    private static Arguments createDummyJavaArguments() {
        Arguments args = new Arguments();
        args.language = cz.cuni.mff.d3s.autodebugger.model.common.TargetLanguage.JAVA;
        args.applicationJarPath = "dummy.jar";
        args.sourceCodePath = "src";
        args.dislHomePath = "disl";
        args.targetMethodReference = "org.example.Test.method()";
        args.testGenerationStrategy = "trace-based-basic";
        args.classpath = java.util.List.of();
        args.targetParameters = java.util.List.of();
        args.targetFields = java.util.List.of();
        args.runtimeArguments = java.util.List.of();
        return args;
    }
}
