package org.frozenarc.zeframework.actionhandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Once ActionHandler is created, it resides in the context.
 * @author Manan
 */
public class ActionHandlerContext {
    
    protected static Map<String, ActionHandler> context=new HashMap<String, ActionHandler>();
    
    public static void putValue(String key, ActionHandler value) {
        context.put(key, value);
    }
    
    public static ActionHandler getValue(String key) {
        return context.get(key);
    }
}
