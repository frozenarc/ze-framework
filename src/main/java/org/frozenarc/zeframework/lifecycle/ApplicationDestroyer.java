package org.frozenarc.zeframework.lifecycle;

import javax.servlet.ServletContext;

/**
 * Implement the interface and define that class in config to do some work on application destroy.
 * @author Manan
 */
public interface ApplicationDestroyer {
    
    /**
     * The method is called on applicatin destroy.
     * @param context ServletContext
     * @throws java.lang.Exception
     */
    void destroyApplication(ServletContext context) throws Exception;
}
