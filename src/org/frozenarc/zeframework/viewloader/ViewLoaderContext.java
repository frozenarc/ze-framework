package org.frozenarc.zeframework.viewloader;

import java.util.HashMap;
import java.util.Map;

/**
 * ViewLoader resides in this context.
 * @author Manan
 */
public class ViewLoaderContext {
    
    protected static Map<String, ViewLoader> context=new HashMap<String, ViewLoader>();
    
    public static void putValue(String key, ViewLoader value) {
        context.put(key, value);
    }
    
    public static ViewLoader getValue(String key) {
        return context.get(key);
    }
}
