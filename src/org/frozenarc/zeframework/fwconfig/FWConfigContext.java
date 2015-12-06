package org.frozenarc.zeframework.fwconfig;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Framework context two preserve various values.
 * @author Manan
 */
public class FWConfigContext {
    
    protected static Map<String, Object> context=Collections.synchronizedMap(new HashMap<String, Object>());
    
    public static void putValue(String key, Object value) {
        context.put(key, value);
    }
    
    public static Object getValue(String key) {
        return context.get(key);
    }
}
