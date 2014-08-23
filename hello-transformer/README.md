WildFly Examples: Applying ClassTransformers to your Java EE archive 
========================
This is a simple WAR archive showing you that Wildfly allows you to apply a ClassTransformer to the classes in your enterprise archive.
 
Deploy the WAR 
========================
* Start Wildfly 8.1.0.Final (or newer)
* mvn clean install wildfly:deploy

Run the SOAP client 
========================
* mvn compile exec:java

Inspect the server log  
========================
Notice that a single EJB was installed: HelloBean. It exposes a SOAP based interface, which is used by the client application.

Upon deployment you should see something like: "Instrumenting hello.HelloBean" and "Successfully instrumented hello.HelloBean".
That is the example class transformer that tells you it has instrumented the HelloBean EJB.

Upon calling the EJB's web service endpoint you should see something like: "INTERCEPTED MethodInvocation".
One line before the method invocation. And one line after the method invocation. 

Do make sure to verify that these lines hasn't just been printed by the EJB's sayHello() method.

And then check out the class "HelloByteCodeManipulator" which do the manipulation on behalf of the class transformer.

Finally: note that the transformer is registered in the WAR' archives META-INF/jboss-deployment-structure.xml file.
This is the handy hook provided to us by the Wildfly.

/Enjoy what is otherwise normally only performed by advanced Java Agents.  
