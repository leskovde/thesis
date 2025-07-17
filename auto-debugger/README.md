# Auto-Debugger: Automated Test Generation via Dynamic Analysis

## Overview

The `auto-debugger` is a thesis project exploring automated test generation through dynamic program analysis and instrumentation. 
Building upon established techniques in runtime analysis, this framework investigates how captured execution traces can be systematically transformed into meaningful test suites. 
The project extends existing approaches by integrating multiple test generation strategies, from traditional trace-based methods to modern AI-assisted techniques.

Developed as part of academic research, this system demonstrates practical applications of dynamic analysis for test automation.
The implementation leverages the DiSL framework for bytecode instrumentation.
The technology stack includes Gradle for build automation, JUnit 5 for test execution, and IntelliJ Plugin SDK for IDE integration.

The project aims to advance understanding of how runtime behavior analysis can complement traditional testing approaches, exploring the trade-offs between different generation strategies and investigating the practical challenges of automated test creation in real-world Java applications.

## Dependencies and Platform Support

### System Requirements

The Auto-Debugger supports **Linux**, **OS X**, and **macOS** platforms, inheriting platform limitations from the DiSL framework dependency.
Windows is not currently supported.

**Note for macOS users**: While macOS is supported, DiSL may require modifications to build scripts, as `gcc` and `ld` is not present in favor of `clang`.

### Core Dependencies

- **Java 17**: Required for compilation and runtime
- **Gradle 8.5+**: Build automation and dependency management

### Runtime Dependencies (Optional)

The following dependencies are required only for specific functionalities:

- **DiSL Framework**: Required for instrumentation and dynamic analysis features
    - Without DiSL: Test generation from pre-existing traces still functions
    - Mock instrumentation can be used for development and testing
- **Anthropic API Key**: Required for LLM-based test generation
    - Without API key: Trace-based and naive generation strategies remain fully functional
    - Set via `ANTHROPIC_API_KEY` environment variable or `--api-key` command line option

## Building and Running

### Quick Start

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd auto-debugger
   ```

2. **Build the project**:
   ```bash
   ./gradlew build
   ```

3. **Run the application**:
   ```bash
   ./gradlew :runner:run --args="--help"
   ```

### Basic Usage Example

```bash
# Generate tests for a Java application
./gradlew :runner:run --args="
  --jar /path/to/your/application.jar
  --source /path/to/source/code
  --method org.example.YourClass.yourMethod(int,String)
  --disl-home /path/to/disl
"
```

### IntelliJ Plugin Development

To work with the IntelliJ plugin:

```bash
# Run IntelliJ IDEA with the plugin
./gradlew :intellij-plugin:runIde

# Build plugin distribution
./gradlew :intellij-plugin:buildPlugin
```

### Testing

Run the complete test suite:

```bash
# Run all tests
./gradlew test

# Run tests for specific modules
./gradlew :test-generator-java:test
./gradlew :instrumentor-java:test
```

### Development Without DiSL

Developing and running certain parts of the pipeline is possible.
When working on new languages or debating a switch to another dynamic analysis framework (RoadRunner, ASM), create new modules and strategies for selecting them.

## Architecture

The `auto-debugger` follows a modular, language-extensible architecture organized around four core phases:

1. **Instrumentation**: Code instrumentation using DiSL framework for runtime data collection
2. **Analysis**: Execution of instrumented applications with trace collection
3. **Test Generation**: Transformation of traces into test cases using various strategies
4. **Test Execution**: Compilation and execution of generated tests with result analysis

The system is designed with language extensibility in mind, currently supporting Java with a clear path for adding support for other programming languages.

## Module Overview

### Core Modules

#### `runner`
The orchestration layer that coordinates the entire `auto-debugger` workflow. Contains the main entry point and factory classes for creating language-specific components.

**Key Classes:**
- `Runner`: Main application entry point with command-line interface
- `Orchestrator`: Central coordinator managing the four-phase workflow
- `Arguments`: Command-line argument parser with PicoCLI integration
- Factory classes (`RunConfigurationFactory`, `InstrumentorFactory`, `AnalyzerFactory`, etc.)

#### `model-common` & `model-java`
Language-agnostic and Java-specific data models for representing program elements, configurations, and traces.

**Key Classes:**
- `RunConfiguration`: Interface for language-specific execution configurations
- `TargetLanguage`: Enumeration of supported programming languages
- `TemporalTrace`: Event-based trace representation following temporal semantics
- `JavaRunConfiguration`: Java-specific implementation with classpath and method targeting
- `JavaMethodIdentifier`, `JavaValueIdentifier`: Java-specific identifier implementations

#### `instrumentor-common` & `instrumentor-java`
Code instrumentation components responsible for generating instrumentation artifacts.

**Key Classes:**
- `Instrumentor`: Interface for language-specific instrumentors
- `DiSLInstrumentor`: Java implementation using DiSL framework
- `DiSLCompiler`: Compiler for DiSL instrumentation classes
- `InstrumentationModel`: Abstract representation of instrumentation logic

#### `analyzer-common` & `analyzer-java`
Runtime analysis components that execute instrumented applications and collect traces.

**Key Classes:**
- `Analyzer`: Interface for executing instrumented applications
- `DiSLAnalyzer`: Java-specific analyzer with process management and trace deserialization

#### `test-generator-common` & `test-generator-java`
Test generation engines supporting multiple strategies from simple trace replay to AI-powered generation.

**Key Classes:**
- `TestGenerator`: Interface for trace-to-test transformation
- `NaiveTraceBasedGenerator`: Simple trace replay test generation
- `TemporalTraceBasedGenerator`: Enhanced generator using temporal trace semantics
- `LLMBasedTestGenerator`: AI-powered test generation using large language models
- `CodeValidator`: Validation of generated test code for compilation and JUnit compliance

#### `test-runner-common` & `test-runner-java`
Test execution components for compiling and running generated tests.

**Key Classes:**
- `TestRunner`: Interface for test execution
- `JUnitTestRunner`: JUnit 5 test execution with instrumentation support
- `TestCompiler`: Eclipse JDT-based compilation of generated test sources

#### `intellij-plugin`
IntelliJ IDEA plugin providing IDE integration with graphical configuration and execution.

**Key Classes:**
- `DebuggerToolWindowContent`: Main plugin UI with run configuration selection and method targeting
- `DebuggerToolWindowFactory`: Tool window factory for IDE integration
- Service classes for settings persistence, output management, and instrumentation coordination

## Interesting Test Examples

### Generated Test Output

The system generates comprehensive test suites from runtime traces. Here's an example of automatically generated test code:

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Generated test class for unknownMethod
 * Generated on: 2025-07-16T19:38:55.079954
 * Generation strategy: Naive Trace-Based
 */
public class UnknownClassTest {

    private UnknownClass unknownclass;

    @BeforeEach
    void setUp() {
        unknownclass = new UnknownClass();
    }

    @Test
    void testUnknownMethod_Scenario1() {
        // Arrange
        int param1 = 20;
        int param2 = 2;

        // Act
        var result = unknownclass.unknownMethod(param1, param2);

        // Assert
        assertEquals(22, result);
    }

    @Test
    void testUnknownMethod_Scenario2() {
        // Arrange
        int param1 = 20;
        int param2 = 4;

        // Act
        var result = unknownclass.unknownMethod(param1, param2);

        // Assert
        assertEquals(24, result);
    }

    @Test
    void testUnknownMethod_Scenario3() {
        // Arrange
        int param1 = 10;
        int param2 = 2;

        // Act
        var result = unknownclass.unknownMethod(param1, param2);

        // Assert
        assertEquals(12, result);
    }

    @Test
    void testUnknownMethod_Scenario4() {
        // Arrange
        int param1 = 10;
        int param2 = 4;

        // Act
        var result = unknownclass.unknownMethod(param1, param2);

        // Assert
        assertEquals(14, result);
    }
}
```

### DiSL Instrumentor Integration Test

The system includes comprehensive integration tests demonstrating the complete instrumentation workflow:

```java
@Test
public void givenValidInstrumentationModel_whenGeneratingInstrumentation_thenInstrumentationJarIsGenerated() {
    // given - Complex instrumentation model with multiple target methods and exportable values
    JavaRunConfiguration runConfiguration = JavaRunConfiguration.builder()
        .applicationPath(Constants.targetJarPath)
        .sourceCodePath(Path.of("src/test/resources/targets/extraction"))
        .targetMethod(Constants.testMethodIdentifier)
        .exportableValues(List.of(
            Constants.stringArgumentIdentifier,
            Constants.integerArgumentIdentifier,
            Constants.stringFieldIdentifier,
            Constants.integerFieldIdentifier
        ))
        .build();

    DiSLModel model = new DiSLModel();
    model.buildInstrumentationModel(runConfiguration);

    Path instrumentationJarPath = Path.of("../analyzer-disl/build/libs/instrumentation.jar");
    DiSLInstrumentor instrumentor = DiSLInstrumentor.builder()
        .instrumentationClassName(new JavaClassIdentifier(
                ClassIdentifierParameters.builder()
                        .packageIdentifier(JavaPackageIdentifier.DEFAULT_PACKAGE)
                        .className("DiSLClass")
                        .build()))
        .runConfiguration(runConfiguration)
        .generatedCodeOutputDirectory(
            Path.of("../analyzer-disl/src/main/java/cz/cuni/mff/d3s/autodebugger/analyzer/disl/"))
        .jarOutputPath(instrumentationJarPath)
        .build();

    // when - Generate complete instrumentation package
    var resultPaths = instrumentor.generateInstrumentation(model);
    
    // then - Verify instrumentation JAR is created with proper DiSL configuration
    assertEquals(1, resultPaths.size());
    assertEquals(instrumentationJarPath, resultPaths.getFirst());
    assertTrue(Files.exists(instrumentationJarPath));
    
    System.out.println("✅ Generated instrumentation JAR: " + instrumentationJarPath);
    System.out.println("📦 JAR size: " + Files.size(instrumentationJarPath) + " bytes");
    System.out.println("🔧 Instrumentation includes: method entry/exit hooks, field access tracking, parameter capture");
}
```

### DiSL Instrumentation Example

The system generates DiSL instrumentation classes for runtime data collection:

```java
import ch.usi.dag.disl.annotation.After;
import ch.usi.dag.disl.marker.BodyMarker;

public class DiSLClass {

  @After(marker = BodyMarker.class, scope = "Main.main")
  public static void aftermain() {
    System.out.println("Instrumentation: After method main");
    CollectorRE.testingBasic(true, (byte) 125, 's', (short) 50000, 100000, 10000000000L);
    CollectorRE.testingAdvanced(
        "Correct transfer of String", "test", Object.class, Thread.currentThread());
  }
}
```

## Test Generation Strategies

The framework supports (or plans to support soon) multiple test generation approaches:

1. **Naive Trace-Based**: Direct replay of observed method calls with captured parameters
2. **Enhanced Temporal**: Sophisticated generation using temporal trace semantics with state reconstruction
3. **LLM-Powered**: AI-assisted test generation using large language models for semantic understanding
4. **Hybrid Approaches**: Combination of trace-based and AI techniques for comprehensive coverage
