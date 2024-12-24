## Not started
### Trivial
- [ ] Pick an UI framework: https://github.com/JetBrains/jewel?tab=readme-ov-file
- [ ] Make DISL_PATH from Gradle accessible from the code
- [ ] Remove all generated sources before building (via Gradle task)
- [ ] Move serialization into separate packages, leave them in the project (do not delete them)
- [ ] Configure Gradle to compile test resources into separate JARs
- [ ] Use jvm options to pass in classpath to ShadowVM
### Tests
- [ ] Create examples from the dacapo benchmark
- [ ] Test effects of field writes
### DiSL Integration
- [ ] Represent Trace as an interface that allows to query values and method that were called at a certain point in time - if there's nothing to return, handle it as the Optional monad
- [ ] DiSL scope annotation - see how to inject scope based on a value of a variable
- [ ] Allow the user to specify field names using wildcards
- Work with different types of variables
	- [ ] Objects
- Generating tests based on values - all parameter values used for running tests
	- [ ] Static methods
	- [ ] Instance methods
		- [ ] Setup an object based on field values etc.
- Running generated tests
	- [ ] Use junit API to run tests from Java
### Writing
- [ ] Write a section on Gradle
	- [ ] Spring selection - yes/no
	- [ ] UI framework selection
- Write down a section of encountered problems
	- [ ] Modeling (metaclasses etc.)
	- [ ] Exporting values with non-primitive types
		- [ ] External types specific to just the target app
	- [ ] Tech debt
	- [ ] Development testing  
	- [ ] Access to sources
		- [ ] Makes things easier, for example getting fields from a class makes use of imports
## Done
### Trivial
- [x] create a repo
- [x] move notes to the repo
- [x] Mock all parts of the project
- [x] rename packages to cz.cuni.mff
- [x] Create a step-by-step list for all the required features of the project (backlog)
### Tests
- [x] Add UTs for code generation
### DiSL Integration
- [x] Explore ShadowVM for ShadowObject API and RemoteAnalysis
- [x] Map variable names to slot indices
- [x] Method parameters
	- At the time of the method call
- [x] Return values
- [x] Field writes
	- Don't care about the line numbers of the writes, only the possible values 
- [x] Static field writes
	- Don't care about the line numbers of the writes, only the possible values 
- [x] Sending values from `Instrumentation` to `auto-debugger`
- Enhance the instrumentation 
	- [x] Run instrumentation before and after a method call
	- Work with different types of variables
		- [x] Primitives
		- [x] Strings
	- [x] Take values of fields of an object
- [x] Generating tests based on values - call the instrumented method with a value
- [x] Take the return value of the method
### Writing
- [x] design and document the possible skeleton of the project
- [x] Create a PNG folder in notes for individual diagrams
- [x] Take a template (from somebody or from the bachelor thesis)
- [x] Send a link with invite 
- [x] Use official template instead of the alternative one

