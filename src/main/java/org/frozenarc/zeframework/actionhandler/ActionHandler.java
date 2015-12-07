package org.frozenarc.zeframework.actionhandler;

import org.frozenarc.zeframework.connector.ActionViewConnector;
import org.frozenarc.zeframework.evententities.EventEntities;
import org.frozenarc.zeframework.model.Model;
import org.frozenarc.zeframework.eventprocessor.EventProcessor;

/**
 * The class is used to handle the submition of form. This is a singleton.
 * @author Manan
 */
public interface ActionHandler extends EventProcessor {
    /**
     * The method is called when submition of form occurs.
     * @param models Model[] what you have defined in config for this action. You will get it in that same order.
     * @param connector what can connect the current action to following view.
     * @param entities have HttpServletRequest and HttpServletResponse object.
     * @return The method returns view name what would be displayed after the handling is complete.
     * @throws java.lang.Exception
     */
    String handleAction(Model[] models, ActionViewConnector connector, EventEntities entities) throws Exception;
}
