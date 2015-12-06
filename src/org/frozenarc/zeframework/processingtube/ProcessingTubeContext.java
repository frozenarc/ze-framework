package org.frozenarc.zeframework.processingtube;

import java.util.HashMap;
import java.util.Map;

/**
 * Context in which ProcessingTube resides.
 * @author Manan
 */
public class ProcessingTubeContext {
    
    protected static Map<String, AbstractProcessingTube> context=new HashMap<String, AbstractProcessingTube>();
    
    public static void putValue(String key, AbstractProcessingTube value) {
        context.put(key, value);
    }
    
    public static AbstractProcessingTube getValue(String key) {
        return context.get(key);
    }
}
