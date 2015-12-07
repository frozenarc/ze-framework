package org.frozenarc.zeframework.applicationmessage;

import org.frozenarc.zeframework.constants.FWConstants;
import org.frozenarc.zeframework.evententities.EventEntities;

/**
 * Stores application messages in session.
 * @author Manan
 */
public class ApplicationMessagesStore {
    
    /**
     * Call this method to clear message from session.
     * @param entities
     */
    public static void clear(EventEntities entities) {
        entities.getRequest().getSession().removeAttribute(FWConstants.APPLICATION_MESSAGES);
    }
    
    /**
     * Call this method to add message into session.
     * @param entities
     * @param message
     */
    public static void addMessage(EventEntities entities, ApplicationMessage message) {
        ApplicationMessages applicationMessages= (ApplicationMessages)entities.getRequest().getSession().getAttribute(FWConstants.APPLICATION_MESSAGES);
        if(applicationMessages==null) {
            entities.getRequest().getSession().setAttribute(FWConstants.APPLICATION_MESSAGES, applicationMessages=new ApplicationMessages());
        }
        applicationMessages.addMessage(message);
    }
    
}
