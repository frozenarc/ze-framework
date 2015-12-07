package org.frozenarc.zeframework.viewvalidator;

import java.util.HashMap;
import java.util.Map;

/**
 * Validator resides in this context.
 * @author Manan
 */
public class ViewValidatorContext {
    
    protected static Map<String, ViewValidator> context=new HashMap<String, ViewValidator>();
    
    public static void putValue(String key, ViewValidator value) {
        context.put(key, value);
    }
    
    public static ViewValidator getValue(String key) {
        return context.get(key);
    }
}
