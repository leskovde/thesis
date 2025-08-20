package cz.cuni.mff.d3s.autodebugger.runner.factories;

import cz.cuni.mff.d3s.autodebugger.model.common.RunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.common.TargetLanguage;
import cz.cuni.mff.d3s.autodebugger.model.java.JavaRunConfiguration;
import cz.cuni.mff.d3s.autodebugger.testrunner.common.TestRunner;
import cz.cuni.mff.d3s.autodebugger.testrunner.java.JUnitTestRunner;
import cz.cuni.mff.d3s.autodebugger.testrunner.common.TestRunnerConfiguration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestRunnerFactory {

    public static TestRunner createTestRunner(RunConfiguration runConfiguration) {
        TargetLanguage language = runConfiguration.getLanguage();
        if (language == TargetLanguage.JAVA) {
            return createJavaTestRunner(runConfiguration);
        }

        throw new IllegalArgumentException("Unsupported language: " + language);
    }

    private static TestRunner createJavaTestRunner(RunConfiguration runConfiguration) {
        log.info("Creating Java test runner");

        if (runConfiguration instanceof JavaRunConfiguration javaRunConfiguration) {
            try {
                JUnitTestRunner testRunner = new JUnitTestRunner();
                TestRunnerConfiguration config = TestRunnerConfiguration.builder()
                        .workingDirectory(javaRunConfiguration.getOutputDirectory())
                        .build();
                testRunner.configure(config);
                log.info("Successfully created Java test runner");
                return testRunner;
            } catch (Exception e) {
                log.error("Failed to create Java test runner", e);
                throw new RuntimeException("Failed to create test runner", e);
            }
        }

        throw new IllegalArgumentException("Expected JavaRunConfiguration, got: " + runConfiguration.getClass().getSimpleName());
    }
}
