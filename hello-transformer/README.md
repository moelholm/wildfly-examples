WildFly Examples: Applying ClassTransformers to your Java EE archive 
========================
This is a simple EJB JAR archive showing you that Wildfly allows you to apply a ClassTransformer to the classes in your enterprise archive.
 
Deploy the EJB JAR 
========================
* Start Wildfly 8.1.0.Final (or newer)
* mvn clean install wildfly:deploy

Inspect the server log  
========================
Notice that a single EJB was installed: HelloBean.

Notice the lines just before the JNDI information: They all start with MyClassTransformer.transform(). 
These lines shows the classes as they are about to be loaded into the JVM classloader for your module.
You may be a bit disappointed now, because we haven't really transformed the classes - sorry!
That is an exercise for the brilliant reader :).

TIP: If you really want to manipulate the bytecode, I urge you to take a look at byteman, cglib, javassist or the like. 
They will help you do something on a higher level than 'bytes' (which you have access to within the transformer).

/Enjoy what is otherwise normally only performed by advanced Java Agents.  

About the EJB client  
========================
Still needs some work in order to work properly. 
You can use soapUI meanwhile to access the EJB (it exposes a SOAP endpoint).
I do :-) 