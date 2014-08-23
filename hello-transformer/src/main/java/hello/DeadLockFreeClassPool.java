package hello;

import java.util.HashSet;
import java.util.Set;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;
import javassist.NotFoundException;

public class DeadLockFreeClassPool extends ClassPool {

    private static final Set<ClassLoader> loaders = new HashSet<ClassLoader>();

    public DeadLockFreeClassPool() {
        super(null);
        appendSystemPath();
    }

    public void addClassLoaderClassPath(ClassLoader cl) {
        synchronized (loaders) {
            if (!loaders.contains(cl)) {
                insertClassPath(new LoaderClassPath(cl));
                loaders.add(cl);
            }
        }
    }

    /**
     * This causes deadlock in parent pool. Hence it is overwritten here without the <code>synchronized</code> modifier.
     * (inspired from some exciting open source code :))
     */
    @Override
    protected CtClass get0(String classname, boolean useCache) throws NotFoundException {

        CtClass clazz = null;

        if (useCache) {
            clazz = getCached(classname);
            if (clazz != null) {
                return clazz;
            }
        }

        clazz = createCtClass(classname, useCache);

        if (clazz != null) {

            if (useCache) {
                cacheCtClass(clazz.getName(), clazz, false);
            }

            return clazz;
        }

        return clazz;
    }
}