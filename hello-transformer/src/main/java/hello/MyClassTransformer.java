package hello;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * A simple hello world class transformer that advices the HelloBean EJB's sayHello method with before and after
 * <code>System.out.println</code> statements.<br>
 * <br>
 *
 * This transformer is registered in the WAR's <code>META-INF/jboss-deployment-structure.xml</code> file. That is specifically
 * the class transformation hook we have been given by the wildfly developers.
 *
 * @author nickymolholm
 */
public class MyClassTransformer implements ClassFileTransformer {

    /**
     * Create a bytecode manipulator that will insert <code>System.out.println</code> statements before and after
     * <code>hello.HelloBean</code>'s <code>sayHello</code> method.
     */
    private final HelloByteCodeManipulator byteCodeTransformer = new HelloByteCodeManipulator("hello.HelloBean", "sayHello");

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        if (!byteCodeTransformer.shouldAccept(className)) {
            return classfileBuffer;
        }

        return byteCodeTransformer.transform(loader, className, classfileBuffer);
    }

}
