package org.frozenarc.zeframework.model;

import java.util.HashMap;

/**
 * The class is used to pass values from jsp page to model when action occurs.
 * @author Manan
 */
public class ModelInfo {
    
    protected HashMap<String, String> map;
    
    public ModelInfo() {
       map=new HashMap<String, String>();
    }
    
    /**
     * You can use the method to get value what is passed from jsp page.
     * @param key 
     * @return value
     */
    public String getValue(String key) {
        return map.get(key);
    }
    
    /**
     * The method is used by framework to set value what is passed from jsp page.
     * @param key
     * @param value
     */
    public void putValue(String key, String value) {
        map.put(key, value);
    }
}
