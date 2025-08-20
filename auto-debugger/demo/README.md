# Auto-Debugger Demo Apps

This demo shows how to run the auto-debugger end-to-end against small toy Java applications, without requiring a real DiSL installation. We use a mock DiSL script and pre-populated stub results so the pipeline launches, runs analysis, and proceeds to test execution.

Contents:
- apps/args: demonstrates argument capture (naive vs temporal)
- apps/fields: demonstrates instance fields
- apps/static: demonstrates static fields
- scripts/mock-disl.py: a tiny Python script that stands in for DiSL's disl.py
- scripts/prepare_stub_results.(sh|py): creates a stub test and a results list in the output directory

Prerequisites:
- Java 21 (javac, jar)

Optional Gradle subproject
- This repo can optionally include a :demo subproject. It is disabled by default and not part of the main build.
- To include it, pass -PincludeDemo (or -DincludeDemo=true) to Gradle:

```bash
./gradlew -PincludeDemo :demo:buildDemoApps
```

This creates JARs in demo/: calc-app.jar, fields-app.jar, static-app.jar.

- Python 3 (for mock-disl.py and optional prepare script)
- macOS/Linux shell (for the .sh helper); on Windows use the Python variant

## 1) Build the demo apps

Each application is a tiny Java program that invokes one target method multiple times.
You can build them with javac + jar. For example:

```bash
# From repo root
cd demo/apps/args
rm -rf out && mkdir -p out
javac -d out $(find src -name '*.java')
jar --create --file calc-app.jar --main-class com.example.CalcApp -C out .

cd ../fields
rm -rf out && mkdir -p out
javac -d out $(find src -name '*.java')
jar --create --file fields-app.jar --main-class com.example.FieldApp -C out .

cd ../static
rm -rf out && mkdir -p out
javac -d out $(find src -name '*.java')
jar --create --file static-app.jar --main-class com.example.StaticApp -C out .
```

The resulting JARs will be placed next to each app.

## 2) Prepare a mock DiSL home

The analyzer expects a `disl.py` script under `<DISL_HOME>/bin`. We provide a small mock script that simulates DiSL output.

```bash
# From repo root
cd demo/scripts
chmod +x mock-disl.py
# Create a mock DiSL home structure
export DEMO_DISL_HOME=$(pwd)/mock-disl-home
mkdir -p "$DEMO_DISL_HOME/bin" "$DEMO_DISL_HOME/output" "$DEMO_DISL_HOME/output/lib"
cp mock-disl.py "$DEMO_DISL_HOME/bin/disl.py"
chmod +x "$DEMO_DISL_HOME/bin/disl.py"
```

## 3) Pre-populate stub results (so analysis has tests to read)

Since we are not running real instrumentation yet, create a stub test and the results list in the chosen output directory. Use shell or Python variant.

```bash
# Using shell
./prepare_stub_results.sh /absolute/path/to/output

# Or Python
python3 prepare_stub_results.py /absolute/path/to/output
```

Notes:
- The analyzer will look for a generated results list path; if not present, it falls back to scanning the output directory for a file matching `generated-tests-*.lst` or uses `generated-tests.lst` if present.
- The helper script writes: output/stub-tests/StubTest.java and output/generated-tests.lst

## 4) Run the auto-debugger runner

General form:

```bash
./gradlew :runner:run --args=" \
  --jar <app.jar> \
  --source <path-to-source> \
  --output-dir <path-to-output> \
  --method <fully.qualified.TargetClass.targetMethod(signature)> \
  --disl-home $DEMO_DISL_HOME \
  --trace-mode <naive|temporal> \
  --test-strategy trace-based-basic \
"
```

Examples:

### A) Args app (Calculator.add)
- Target: `com.example.Calculator.add(int,int)` called multiple times from `CalcApp`.
- Demonstrates argument export. Run in both naive and temporal modes.

```bash
# Naive
./gradlew :runner:run --args=" \
  --jar $(pwd)/demo/calc-app.jar \
  --source $(pwd)/demo/apps/args/src \
  --output-dir $(pwd)/demo/output/args-naive \
  --method com.example.Calculator.add(int,int) \
  --disl-home $DEMO_DISL_HOME \
  --trace-mode naive \
  --test-strategy trace-based-basic \
"

# Temporal
./gradlew :runner:run --args=" \
  --jar $(pwd)/demo/calc-app.jar \
  --source $(pwd)/demo/apps/args/src \
  --output-dir $(pwd)/demo/output/args-temporal \
  --method com.example.Calculator.add(int,int) \
  --disl-home $DEMO_DISL_HOME \
  --trace-mode temporal \
  --test-strategy trace-based-basic \
"
```

### B) Fields app (Counter.increment)
- Target: `com.example.Counter.increment()` called multiple times from `FieldApp`.
- Demonstrates instance field export.

```bash
./gradlew :runner:run --args=" \
  --jar $(pwd)/demo/fields-app.jar \
  --source $(pwd)/demo/apps/fields/src \
  --output-dir $(pwd)/demo/output/fields-naive \
  --method com.example.Counter.increment() \
  --disl-home $DEMO_DISL_HOME \
  --trace-mode naive \
  --test-strategy trace-based-basic \
"
```

### C) Static fields app (Globals.bump)
- Target: `com.example.Globals.bump()` called multiple times from `StaticApp`.
- Demonstrates static field export.

```bash
./gradlew :runner:run --args=" \
  --jar $(pwd)/demo/static-app.jar \
  --source $(pwd)/demo/apps/static/src \
  --output-dir $(pwd)/demo/output/static-temporal \
  --method com.example.Globals.bump() \
  --disl-home $DEMO_DISL_HOME \
  --trace-mode temporal \
  --test-strategy trace-based-basic \
"
```

Tips:
- Remember to pre-create stub results for each output directory you plan to use (step 3). The analyzer will read generated test paths from that list.
- Once DiSL integration is added, you can skip the stub preparation and observe real generated tests.

## App sources
- apps/args/src/com/example/CalcApp.java
- apps/args/src/com/example/Calculator.java
- apps/fields/src/com/example/FieldApp.java
- apps/fields/src/com/example/Counter.java
- apps/static/src/com/example/StaticApp.java
- apps/static/src/com/example/Globals.java

