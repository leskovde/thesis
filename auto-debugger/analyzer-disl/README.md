# analyzer-disl

This module contains the DiSL analysis-side code that runs inside the ShadowVM (evaluation JVM).

## Collector template (Collector.jt)

Collector.jt is the template used to generate the analysis-side Collector.java class.
Placeholders are substituted at instrumentation time by the instrumentor.

Placeholders:
- ${PATH}: absolute path to the identifier mapping JSON used by trace-based generators
- ${RESULTS}: absolute path to the results list file (one generated test path per line)
- ${TARGET_PACKAGE}, ${TARGET_CLASS}, ${TARGET_METHOD}: optional hints for generators
- ${STRATEGY}: compile-time injected generation strategy id (e.g. "trace-based-naive", "ai-assisted")

Notes:
- ${STRATEGY} is baked into the generated Collector; the ShadowVM does not read it from system properties.
- Sensitive values such as API keys are not embedded; they are read from environment variables at runtime.

## Environment variables

The Collector process (ShadowVM) recognizes the following environment variables:

- ANTHROPIC_API_KEY: preferred key for the LLM-based generator
- OPENAI_API_KEY: fallback key if ANTHROPIC_API_KEY is not set
- AUTODEBUGGER_STUB: if set to "1" or "true", the Collector writes a minimal stub test and exits, used for testing

These variables are read via System.getenv(...) inside the Collector.

## Outputs

At analysis exit, the Collector writes the absolute paths of generated test files into the results list file (${RESULTS}).
The parent process reads the path to this file from its own system property (autodebugger.results) and loads the list.

