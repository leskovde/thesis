package cz.cuni.mff.d3s.autodebugger.runner.orchestrator;

import cz.cuni.mff.d3s.autodebugger.analyzer.common.Analyzer;
import cz.cuni.mff.d3s.autodebugger.analyzer.java.JavaAnalyzer;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.Instrumentor;
import cz.cuni.mff.d3s.autodebugger.instrumentor.common.modelling.Model;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.DiSLInstrumentor;
import cz.cuni.mff.d3s.autodebugger.instrumentor.java.modelling.DiSLModel;
import cz.cuni.mff.d3s.autodebugger.model.java.JavaRunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.common.RunConfiguration;
import cz.cuni.mff.d3s.autodebugger.model.common.TargetLanguage;
import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.ExportableValue;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.MethodIdentifier;
import cz.cuni.mff.d3s.autodebugger.runner.args.Arguments;
import cz.cuni.mff.d3s.autodebugger.runner.parsing.MethodSignatureParsingStrategy;
import cz.cuni.mff.d3s.autodebugger.runner.parsing.MethodSignatureParsingStrategyFactory;
import cz.cuni.mff.d3s.autodebugger.runner.strategies.TestGenerationStrategyProvider;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.TestGenerator;
import cz.cuni.mff.d3s.autodebugger.testgenerator.java.trace.TraceBasedUnitTestGenerator;
import cz.cuni.mff.d3s.autodebugger.testrunner.common.TestRunner;
import cz.cuni.mff.d3s.autodebugger.testrunner.java.JUnitTestRunner;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Java-specific orchestrator implementation that creates and configures
 * Java-based components for instrumentation, analysis, test generation, and test execution.
 */
@Slf4j
public class JavaOrchestrator implements Orchestrator {

    private static final TargetLanguage LANGUAGE = TargetLanguage.JAVA;
    private static final String DEFAULT_TEST_GENERATION_TECHNIQUE = "trace-based-basic";
    
    @Override
    public RunConfiguration createRunConfiguration(Arguments args) {
        log.info("Creating Java run configuration from arguments");
        
        try {
            // Get the Java parsing strategy
            MethodSignatureParsingStrategy strategy = MethodSignatureParsingStrategyFactory.getStrategy(LANGUAGE);
            
            // Parse paths
            Path applicationPath = Path.of(args.applicationJarPath);
            Path sourceCodePath = Path.of(args.sourceCodePath);
            
            // Validate configuration for Java
            strategy.validateConfiguration(applicationPath, sourceCodePath);
            
            // Parse the method reference
            MethodIdentifier methodIdentifier = strategy.parseMethodReference(args.targetMethodReference);
            
            // Convert target parameters and fields to ExportableValues
            List<ExportableValue> parameterValues = strategy.parseTargetParameters(args.targetParameters, methodIdentifier);
            List<ExportableValue> fieldValues = strategy.parseTargetFields(args.targetFields, methodIdentifier);
            
            // Combine all exportable values
            List<ExportableValue> exportableValues = new ArrayList<>();
            exportableValues.addAll(parameterValues);
            exportableValues.addAll(fieldValues);
            
            // Create the Java run configuration
            JavaRunConfiguration configuration = JavaRunConfiguration.builder()
                    .applicationPath(applicationPath)
                    .sourceCodePath(sourceCodePath)
                    .targetMethod(methodIdentifier)
                    .exportableValues(exportableValues)
                    .build();
            
            // Validate the configuration
            configuration.validate();
            
            log.info("Successfully created Java run configuration for method: {}", methodIdentifier.getName());
            return configuration;
            
        } catch (Exception e) {
            log.error("Failed to create Java run configuration", e);
            throw new RuntimeException("Failed to create run configuration", e);
        }
    }
    
    @Override
    public Model buildInstrumentationModel(RunConfiguration runConfiguration) {
        log.info("Building DiSL instrumentation model");
        
        if (!LANGUAGE.getIdentifier().equals(runConfiguration.getLanguage())) {
            throw new IllegalArgumentException("Expected Java run configuration, got: " + runConfiguration.getLanguage());
        }
        
        // Create a temporary instrumentor to build the model
        // This is a bit of a circular dependency, but the DiSLModel needs an Instrumentor
        Instrumentor tempInstrumentor = DiSLInstrumentor.builder()
                .applicationJarPath(runConfiguration.getApplicationPath())
                .className(runConfiguration.getTargetMethod().getOwnerClassIdentifier())
                .method(runConfiguration.getTargetMethod())
                .exportedValues(runConfiguration.getExportableValues())
                .classpath(runConfiguration.getClasspathEntries())
                .build();
        
        DiSLModel model = new DiSLModel(tempInstrumentor);
        log.info("Successfully built DiSL instrumentation model");
        return model;
    }
    
    @Override
    public Instrumentor createInstrumentor(Model instrumentationModel) {
        log.info("Creating DiSL instrumentor");
        
        if (!(instrumentationModel instanceof DiSLModel)) {
            throw new IllegalArgumentException("Expected DiSLModel, got: " + instrumentationModel.getClass().getSimpleName());
        }
        
        // Extract information from the model to create the instrumentor
        // Note: This is a simplified approach. In practice, the model should contain
        // all the necessary information to reconstruct the instrumentor
        DiSLModel dislModel = (DiSLModel) instrumentationModel;
        
        // For now, we'll need to get the information from somewhere else
        // This is a limitation of the current DiSLModel design
        throw new UnsupportedOperationException("Creating instrumentor from model not yet fully implemented");
    }
    
    @Override
    public Analyzer createAnalyzer(RunConfiguration runConfiguration) {
        log.info("Creating Java analyzer");
        
        JavaAnalyzer analyzer = new JavaAnalyzer();
        analyzer.configure(runConfiguration);
        
        log.info("Successfully created and configured Java analyzer");
        return analyzer;
    }
    
    @Override
    public TestGenerator createTestGenerator(RunConfiguration runConfiguration, String generationTechnique) {
        log.info("Creating Java test generator with technique: {}", generationTechnique);

        try {
            // For now, we only support trace-based generation
            // In the future, this would use a factory pattern based on the technique
            if (!generationTechnique.startsWith("trace-based")) {
                throw new UnsupportedOperationException("Test generation technique not yet supported: " + generationTechnique);
            }

            // Create the trace-based test generator
            // Note: This would need to be enhanced to support different techniques
            TraceBasedUnitTestGenerator generator = new TraceBasedUnitTestGenerator(Path.of("identifiers"));

            log.info("Successfully created Java test generator with technique: {}", generationTechnique);
            return new TestGeneratorAdapter(generator, runConfiguration, generationTechnique);
        } catch (Exception e) {
            log.error("Failed to create Java test generator", e);
            throw new RuntimeException("Failed to create test generator", e);
        }
    }
    
    @Override
    public TestGenerator createTestGenerator(RunConfiguration runConfiguration) {
        return createTestGenerator(runConfiguration, DEFAULT_TEST_GENERATION_TECHNIQUE);
    }
    
    @Override
    public TestRunner createTestRunner(RunConfiguration runConfiguration) {
        log.info("Creating Java test runner");

        try {
            JUnitTestRunner testRunner = new JUnitTestRunner();
            testRunner.configure(runConfiguration);

            log.info("Successfully created Java test runner");
            return testRunner;
        } catch (Exception e) {
            log.error("Failed to create Java test runner", e);
            throw new RuntimeException("Failed to create test runner", e);
        }
    }
    
    @Override
    public TargetLanguage getSupportedLanguage() {
        return LANGUAGE;
    }
    
    @Override
    public String[] getAvailableTestGenerationTechniques() {
        return TestGenerationStrategyProvider.getAvailableStrategies()
                .stream()
                .map(strategy -> strategy.getId())
                .toArray(String[]::new);
    }
    
    @Override
    public String getDefaultTestGenerationTechnique() {
        return DEFAULT_TEST_GENERATION_TECHNIQUE;
    }
}
