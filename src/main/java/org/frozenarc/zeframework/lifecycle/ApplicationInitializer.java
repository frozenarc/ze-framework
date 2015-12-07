package org.frozenarc.zeframework.lifecycle;

import javax.servlet.ServletContext;

/**
 * Implement the interface and define that class in config to do some work on application init.
 * @author Manan
 */
public interface ApplicationInitializer {
    
    /**
     * The method is called on applicatin init.
     * @param context ServletContext
     * @throws java.lang.Exception
     */
    void initializeApplication(ServletContext context) throws Exception;
}
