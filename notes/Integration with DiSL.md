## Running DiSL
DiSL is made out of multiple projects and running an instrumentation requires executing these projects as separate processes.
This is done in the `bin/disl.py` script, which sets up the correct configuration for the desired instrumentation. The script offers a plethora of options for configuring where the processes communicate and where they output data.

There are three processes that DiSL can run: 
- DiSL application, 
- DiSL instrumentation server  
- Shadow VM server launcher

By default, the script starts a DiSL instrumentation server VM and a client application
VM that will be instrumented using DiSL. For instrumentations using remote analysis
execution in Shadow VM, the Shadow VM server needs to be started as well, which needs
to be specified explicitly.

The user provides two Java applications (`*.jar`). One is a build of the instrumentation, which is written as a separate application that uses the DiSL libraries. The other is the build of the application that we want to instrument. This build can be untouched, since all the instrumentation logic is done in the instrumentation build. 

## Integrating DiSL into the project
Initially, we thought it would make sense to run DiSL as a library call instead of launching it via an external script. By doing this, we would get the result of the instrumentation as an in-memory object, instead of having to scrape it from the output of the tool (a file).

However, due to the need to spawn multiple processes and having them communicate via an already established means set by DiSL, library calls from a single application would not suffice.

The next simplest solution is having the instrumentation application communicate with our project using some sort of inter-process communication (IPC).