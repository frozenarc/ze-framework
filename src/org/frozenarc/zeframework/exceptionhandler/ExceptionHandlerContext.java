package org.frozenarc.zeframework.exceptionhandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Once the exception handler is created, it resides in the context.
 * @author Manan
 */
public class ExceptionHandlerContext {
    
    protected static Map<String, ExceptionHandler> context=new HashMap<String, ExceptionHandler>();
    
    public static void putValue(String key, ExceptionHandler value) {
        context.put(key, value);
    }
    
    public static ExceptionHandler getValue(String key) {
        return context.get(key);
    }
}
