package org.frozenarc.zeframework.exceptionhandler;

import org.frozenarc.zeframework.util.ReflectionUtil;

/**
 * The class creates ExceptionHandler.
 * @author Manan
 */
public class ExceptionHandlerFactory {

    public static ExceptionHandler getExceptionHandler(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (ExceptionHandlerContext.getValue(className) != null) {
            return ExceptionHandlerContext.getValue(className);
        } else {
            return createExceptionHandlerSingleton(className);
        }
    }

    protected static synchronized ExceptionHandler createExceptionHandlerSingleton(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (ExceptionHandlerContext.getValue(className) == null) {
            ExceptionHandler exceptionHandler = (ExceptionHandler) ReflectionUtil.createInstance(className);
            ExceptionHandlerContext.putValue(className, exceptionHandler);
        }
        return ExceptionHandlerContext.getValue(className);
    }
}
