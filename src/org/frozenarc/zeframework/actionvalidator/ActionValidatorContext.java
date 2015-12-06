package org.frozenarc.zeframework.actionvalidator;

import java.util.HashMap;
import java.util.Map;

/**
 * Validator resides in this context.
 * @author Manan
 */
public class ActionValidatorContext {
    
    protected static Map<String, ActionValidator> context=new HashMap<String, ActionValidator>();
    
    public static void putValue(String key, ActionValidator value) {
        context.put(key, value);
    }
    
    public static ActionValidator getValue(String key) {
        return context.get(key);
    }
}
