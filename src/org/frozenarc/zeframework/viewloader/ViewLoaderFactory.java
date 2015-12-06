package org.frozenarc.zeframework.viewloader;

import org.frozenarc.zeframework.util.ReflectionUtil;

/**
 * Creates ViewLoader
 * @author Manan
 */
public class ViewLoaderFactory {

    public static ViewLoader getViewLoader(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (ViewLoaderContext.getValue(className) != null) {
            return ViewLoaderContext.getValue(className);
        } else {
            return createViewLoaderSingleton(className);
        }
    }

    protected static synchronized ViewLoader createViewLoaderSingleton(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (ViewLoaderContext.getValue(className) == null) {
            ViewLoader viewLoader = (ViewLoader) ReflectionUtil.createInstance(className);
            ViewLoaderContext.putValue(className, viewLoader);
        }
        return ViewLoaderContext.getValue(className);
    }
}
