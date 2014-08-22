package hello;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.net.URL;
import java.security.ProtectionDomain;

public class MyClassTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        // This is where you would transform the bytecode !

        URL classLocation = protectionDomain.getCodeSource().getLocation();
        System.out.format("MyClassTransformer.transform() :: %s (%s)%n", className, classLocation);

        return classfileBuffer;
    }

}
