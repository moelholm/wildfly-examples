package hello;

import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class MyClassTransformer implements java.lang.instrument.ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("MyClassTransformer.transform() :: "+className+" ("+protectionDomain.getCodeSource().getLocation()+")");
        return classfileBuffer;
    }

}
