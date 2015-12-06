package org.frozenarc.zeframework.applicationproperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletContext;

/**
 * Uses to fetch application properties.
 * @author Manan
 */
public class ApplicationProperties {
    
    protected static Properties properties;
    
    static {
        properties=new Properties();
    }
    
    /**
     * Call the method to get property defined in property file of application.
     * @param key
     * @return property value
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public static void loadProperties(ServletContext context, String filePath) throws IOException {
        FileInputStream inputStream=null;
        try {
            inputStream=new FileInputStream(filePath);
            properties.load(inputStream);
        } finally {
            if(inputStream!=null) {
                inputStream.close();
            }
        }
        
    }
}
