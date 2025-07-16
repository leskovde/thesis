package cz.cuni.mff.d3s.autodebugger.testgenerator.java.trace;

import cz.cuni.mff.d3s.autodebugger.model.common.trace.Trace;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.JavaArgumentIdentifier;
import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.ExportableValue;
import cz.cuni.mff.d3s.autodebugger.model.java.identifiers.JavaFieldIdentifier;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Naive trace-based test generator that creates simple unit tests
 * by replicating observed method calls with collected runtime values.
 */
@Slf4j
public class NaiveTraceBasedGenerator implements TraceBasedGenerator {
    
    private final Map<Integer, ExportableValue> identifierMapping;
    private TestGenerationContext context;
    
    public NaiveTraceBasedGenerator(Map<Integer, ExportableValue> identifierMapping) {
        this.identifierMapping = identifierMapping;
    }
    
    @Override
    public List<Path> generateTests(Trace trace) {
        return generateTests(trace, createDefaultContext());
    }
    
    public List<Path> generateTests(Trace trace, TestGenerationContext context) {
        this.context = context;
        log.info("Generating naive trace-based tests for method: {}", context.getTargetMethodSignature());
        
        try {
            TraceIdentifierMapper mapper = new TraceIdentifierMapper(trace, identifierMapping);
            
            // Group values by execution scenarios
            List<TestScenario> scenarios = extractTestScenarios(mapper);
            
            // Generate test class
            String testClassContent = generateTestClass(scenarios);
            
            // Write test file
            Path testFile = writeTestFile(testClassContent);
            
            log.info("Generated {} test scenarios in file: {}", scenarios.size(), testFile);
            return List.of(testFile);
            
        } catch (Exception e) {
            log.error("Failed to generate naive trace-based tests", e);
            return List.of();
        }
    }
    
    private List<TestScenario> extractTestScenarios(TraceIdentifierMapper mapper) {
        List<TestScenario> scenarios = new ArrayList<>();
        
        // Get all argument and field identifiers
        Map<Integer, JavaArgumentIdentifier> arguments = new HashMap<>();
        Map<Integer, JavaFieldIdentifier> fields = new HashMap<>();
        
        for (Integer slot : mapper.getSlots()) {
            ExportableValue value = mapper.getExportableValue(slot);
            if (value instanceof JavaArgumentIdentifier arg) {
                arguments.put(slot, arg);
            } else if (value instanceof JavaFieldIdentifier field) {
                fields.put(slot, field);
            }
        }
        
        // Create test scenarios based on unique combinations of values
        Set<String> uniqueScenarios = new HashSet<>();
        
        // For simplicity, create one scenario per unique combination of argument values
        if (!arguments.isEmpty()) {
            List<Map<Integer, Object>> argumentCombinations = generateArgumentCombinations(arguments, mapper);
            
            for (Map<Integer, Object> argCombo : argumentCombinations) {
                Map<Integer, Object> fieldCombo = extractFieldValues(fields, mapper);
                
                String scenarioKey = createScenarioKey(argCombo, fieldCombo);
                if (!uniqueScenarios.contains(scenarioKey)) {
                    uniqueScenarios.add(scenarioKey);
                    scenarios.add(new TestScenario(argCombo, fieldCombo, scenarios.size() + 1));
                }
            }
        }
        
        return scenarios;
    }
    
    private List<Map<Integer, Object>> generateArgumentCombinations(Map<Integer, JavaArgumentIdentifier> arguments,
                                                                   TraceIdentifierMapper mapper) {
        List<Map<Integer, Object>> combinations = new ArrayList<>();
        
        // Get all possible values for each argument
        Map<Integer, List<Object>> argumentValues = new HashMap<>();
        for (Map.Entry<Integer, JavaArgumentIdentifier> entry : arguments.entrySet()) {
            Integer slot = entry.getKey();
            Set<?> values = mapper.getSlotValues(slot);
            argumentValues.put(slot, new ArrayList<>(values));
        }
        
        // Generate combinations (for now, just take first few unique combinations)
        if (!argumentValues.isEmpty()) {
            List<Integer> slots = new ArrayList<>(argumentValues.keySet());
            generateCombinationsRecursive(slots, 0, new HashMap<>(), argumentValues, combinations, 10);
        }
        
        return combinations;
    }
    
    private void generateCombinationsRecursive(List<Integer> slots, int index, 
                                             Map<Integer, Object> current,
                                             Map<Integer, List<Object>> allValues,
                                             List<Map<Integer, Object>> combinations,
                                             int maxCombinations) {
        if (combinations.size() >= maxCombinations) {
            return;
        }
        
        if (index >= slots.size()) {
            combinations.add(new HashMap<>(current));
            return;
        }
        
        Integer slot = slots.get(index);
        List<Object> values = allValues.get(slot);
        
        for (Object value : values) {
            current.put(slot, value);
            generateCombinationsRecursive(slots, index + 1, current, allValues, combinations, maxCombinations);
            current.remove(slot);
            
            if (combinations.size() >= maxCombinations) {
                break;
            }
        }
    }
    
    private Map<Integer, Object> extractFieldValues(Map<Integer, JavaFieldIdentifier> fields,
                                                   TraceIdentifierMapper mapper) {
        Map<Integer, Object> fieldValues = new HashMap<>();
        
        for (Map.Entry<Integer, JavaFieldIdentifier> entry : fields.entrySet()) {
            Integer slot = entry.getKey();
            Set<?> values = mapper.getSlotValues(slot);
            if (!values.isEmpty()) {
                fieldValues.put(slot, values.iterator().next()); // Take first value
            }
        }
        
        return fieldValues;
    }
    
    private String createScenarioKey(Map<Integer, Object> arguments, Map<Integer, Object> fields) {
        StringBuilder key = new StringBuilder();
        key.append("args:").append(arguments.toString());
        key.append("|fields:").append(fields.toString());
        return key.toString();
    }
    
    private String generateTestClass(List<TestScenario> scenarios) {
        StringBuilder sb = new StringBuilder();
        
        // Package declaration
        if (context.getPackageName() != null && !context.getPackageName().isEmpty()) {
            sb.append("package ").append(context.getPackageName()).append(";\n\n");
        }
        
        // Imports
        sb.append("import org.junit.jupiter.api.Test;\n");
        sb.append("import org.junit.jupiter.api.BeforeEach;\n");
        sb.append("import static org.junit.jupiter.api.Assertions.*;\n\n");
        
        // Class declaration
        String testClassName = extractClassNameFromMethod(context.getTargetMethodSignature()) + "Test";
        sb.append("/**\n");
        sb.append(" * Generated test class for ").append(context.getTargetMethodSignature()).append("\n");
        sb.append(" * Generated on: ").append(LocalDateTime.now()).append("\n");
        sb.append(" * Generation strategy: Naive Trace-Based\n");
        sb.append(" */\n");
        sb.append("public class ").append(testClassName).append(" {\n\n");
        
        // Instance variable for the class under test
        String targetClass = context.getTargetClassName();
        String instanceName = targetClass.substring(targetClass.lastIndexOf('.') + 1).toLowerCase();
        sb.append("    private ").append(targetClass).append(" ").append(instanceName).append(";\n\n");
        
        // Setup method
        sb.append("    @BeforeEach\n");
        sb.append("    void setUp() {\n");
        sb.append("        // TODO: Initialize ").append(instanceName).append(" with appropriate constructor\n");
        sb.append("        // ").append(instanceName).append(" = new ").append(targetClass).append("();\n");
        sb.append("    }\n\n");
        
        // Generate test methods
        for (TestScenario scenario : scenarios) {
            sb.append(generateTestMethod(scenario, instanceName));
            sb.append("\n");
        }
        
        sb.append("}\n");
        
        return sb.toString();
    }
    
    private String generateTestMethod(TestScenario scenario, String instanceName) {
        StringBuilder sb = new StringBuilder();
        
        String methodName = generateTestMethodName(scenario);
        
        sb.append("    @Test\n");
        sb.append("    void ").append(methodName).append("() {\n");
        sb.append("        // Arrange\n");
        
        // Set up field values if any
        for (Map.Entry<Integer, Object> field : scenario.fieldValues.entrySet()) {
            ExportableValue fieldId = identifierMapping.get(field.getKey());
            if (fieldId instanceof JavaFieldIdentifier fieldIdentifier) {
                sb.append("        // TODO: Set field ").append(fieldIdentifier.getFieldName())
                  .append(" to ").append(field.getValue()).append("\n");
            }
        }
        
        sb.append("\n        // Act\n");
        
        // Generate method call
        String methodCall = generateMethodCall(scenario, instanceName);
        sb.append("        ").append(methodCall).append("\n");
        
        sb.append("\n        // Assert\n");
        sb.append("        // TODO: Add appropriate assertions based on expected behavior\n");
        sb.append("        // Example: assertEquals(expectedValue, result);\n");
        
        sb.append("    }\n");
        
        return sb.toString();
    }
    
    private String generateTestMethodName(TestScenario scenario) {
        if (context.getNamingStrategy() == TestNamingStrategy.SIMPLE) {
            return "test" + scenario.scenarioNumber;
        }
        
        StringBuilder name = new StringBuilder("test");
        String methodName = extractMethodNameFromSignature(context.getTargetMethodSignature());
        name.append(capitalize(methodName));
        
        if (context.getNamingStrategy() == TestNamingStrategy.PARAMETER_BASED) {
            name.append("_");
            for (Object value : scenario.argumentValues.values()) {
                name.append(sanitizeValueForMethodName(value)).append("_");
            }
            if (name.toString().endsWith("_")) {
                name.setLength(name.length() - 1);
            }
        } else {
            name.append("_Scenario").append(scenario.scenarioNumber);
        }
        
        return name.toString();
    }
    
    private String generateMethodCall(TestScenario scenario, String instanceName) {
        String methodName = extractMethodNameFromSignature(context.getTargetMethodSignature());
        
        StringBuilder call = new StringBuilder();
        call.append("var result = ").append(instanceName).append(".").append(methodName).append("(");
        
        // Add arguments in order
        List<String> args = new ArrayList<>();
        for (Map.Entry<Integer, Object> arg : scenario.argumentValues.entrySet()) {
            ExportableValue argId = identifierMapping.get(arg.getKey());
            if (argId instanceof JavaArgumentIdentifier) {
                args.add(formatValueForCode(arg.getValue()));
            }
        }
        
        call.append(String.join(", ", args));
        call.append(");");
        
        return call.toString();
    }
    
    private String formatValueForCode(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return "\"" + value.toString().replace("\"", "\\\"") + "\"";
        } else if (value instanceof Character) {
            return "'" + value + "'";
        } else {
            return value.toString();
        }
    }
    
    private Path writeTestFile(String testClassContent) throws IOException {
        String testClassName = extractClassNameFromMethod(context.getTargetMethodSignature()) + "Test";
        Path testFile = context.getOutputDirectory().resolve(testClassName + ".java");
        
        Files.createDirectories(testFile.getParent());
        Files.write(testFile, testClassContent.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        
        return testFile;
    }
    
    private TestGenerationContext createDefaultContext() {
        return TestGenerationContext.builder()
                .targetMethodSignature("unknownMethod")
                .targetClassName("UnknownClass")
                .packageName("")
                .outputDirectory(Path.of("generated-tests"))
                .build();
    }
    
    private String extractClassNameFromMethod(String methodSignature) {
        // Simple extraction - assumes format like "ClassName.methodName"
        if (methodSignature.contains(".")) {
            return methodSignature.substring(0, methodSignature.lastIndexOf('.'));
        }
        return "UnknownClass";
    }
    
    private String extractMethodNameFromSignature(String methodSignature) {
        if (methodSignature.contains(".")) {
            String methodPart = methodSignature.substring(methodSignature.lastIndexOf('.') + 1);
            if (methodPart.contains("(")) {
                return methodPart.substring(0, methodPart.indexOf('('));
            }
            return methodPart;
        }
        return methodSignature;
    }
    
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    
    private String sanitizeValueForMethodName(Object value) {
        if (value == null) {
            return "Null";
        }
        return value.toString().replaceAll("[^a-zA-Z0-9]", "");
    }
    
    private static class TestScenario {
        final Map<Integer, Object> argumentValues;
        final Map<Integer, Object> fieldValues;
        final int scenarioNumber;
        
        TestScenario(Map<Integer, Object> argumentValues, Map<Integer, Object> fieldValues, int scenarioNumber) {
            this.argumentValues = argumentValues;
            this.fieldValues = fieldValues;
            this.scenarioNumber = scenarioNumber;
        }
    }
}
