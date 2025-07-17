package cz.cuni.mff.d3s.autodebugger.runner.factories;

import cz.cuni.mff.d3s.autodebugger.model.common.RunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.common.TargetLanguage;
import cz.cuni.mff.d3s.autodebugger.model.java.JavaRunConfiguration;
import cz.cuni.mff.d3s.autodebugger.runner.orchestrator.TestGeneratorAdapter;
import cz.cuni.mff.d3s.autodebugger.runner.strategies.TestGenerationStrategyProvider;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TestGenerator;
import cz.cuni.mff.d3s.autodebugger.testgenerator.java.trace.TraceBasedUnitTestGenerator;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;

@Slf4j
public class TestGeneratorFactory {

    public static TestGenerator createTestGenerator(RunConfiguration runConfiguration, String strategyId) {
        TargetLanguage language = runConfiguration.getLanguage();
        if (language == TargetLanguage.JAVA) {
            return createJavaTestGenerator(runConfiguration, strategyId);
        }

        throw new IllegalArgumentException("Unsupported language: " + language);
    }

    private static TestGenerator createJavaTestGenerator(RunConfiguration runConfiguration, String strategyId) {
        log.info("Creating Java test generator with strategy: {}", strategyId);

        // Validate that the strategy exists
        if (!TestGenerationStrategyProvider.hasStrategy(strategyId)) {
            throw new IllegalArgumentException("Unknown test generation strategy: " + strategyId);
        }

        if (runConfiguration instanceof JavaRunConfiguration javaRunConfiguration) {
            try {
                // For now, we only support trace-based generation
                // In the future, this would use a factory pattern based on the technique
                if (!strategyId.startsWith("trace-based")) {
                    throw new UnsupportedOperationException("Test generation technique not yet supported: " + strategyId);
                }

                // Create the trace-based test generator
                // Note: This would need to be enhanced to support different techniques
                Path identifiersPath = javaRunConfiguration.getSourceCodePath().resolve("identifiers");
                TraceBasedUnitTestGenerator generator = new TraceBasedUnitTestGenerator(identifiersPath);

                log.info("Successfully created Java test generator with strategy: {}", strategyId);
                return new TestGeneratorAdapter(generator, runConfiguration, strategyId);
            } catch (Exception e) {
                log.error("Failed to create Java test generator", e);
                throw new RuntimeException("Failed to create test generator", e);
            }
        }

        throw new IllegalArgumentException("Expected JavaRunConfiguration, got: " + runConfiguration.getClass().getSimpleName());
    }
}
