package org.frozenarc.zeframework.exceptionhandler;

import org.frozenarc.zeframework.connector.ActionViewConnector;
import org.frozenarc.zeframework.evententities.EventEntities;

/**
 * The is used to handle a exception what is defined in config to be handled.
 * This is a singleton.
 * @author Manan
 */
public interface ExceptionHandler {
    
    /**
     * The mehtod is called when there is a need to handle an exception.
     * @param ex Exception to be handled
     * @param connector ActionViewConnector
     * @param entities EventEntities
     */
    void handleException(Exception ex, ActionViewConnector connector, EventEntities entities);
}
