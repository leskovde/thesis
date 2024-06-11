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
### Extracting runtime values
As of now, we have multiple different concurrently-running processes that perform the instrumentation. Their communication looks like this:
![[processes.png]]
The DiSL instrumentation queries the values of the instrumented app and is able to perform any logic on top of them. In order to get the values from the DiSL instrumentation JAR, we need some sort of an IPC mechanism. The simplest way is to store the values in a file and read them later on in the auto-debugger process. By choosing this approach, we run into several issues, mainly with types and their recognition. 

We can still make this work by going for a more standardized way of storing the data, which is serialization. 
#### Serializing values
After we collect the necessary values in the instrumentation, we dump them into a file using existing means of serialization. These files are then loaded back in auto-debugger and the values are reconstructed into runtime variables that can be used for further steps of the auto-debugger pipeline.

We use the `java.io.ObjectOutputStream` to write values to files, creating one file per value. The full generated instrumentation code looks as follows:
```java
@Before(marker = BodyMarker.class, scope = "test")  
public static void generatedMethod0(DynamicContext di) {  
    int generatedVariable0 = di.getLocalVariableValue(0, int.class);  
    try {  
       FileOutputStream fileOut = new FileOutputStream("variable.ser");  
       ObjectOutputStream out = new ObjectOutputStream(fileOut);  
       out.writeObject(generatedVariable0);  
       out.close();  
       fileOut.close();  
    } catch (IOException e) {  
       e.printStackTrace();  
    }  
}
```

#### GRPC

#### ShadowVM