package org.frozenarc.zeframework.viewvalidator;

import org.frozenarc.zeframework.connector.ActionViewConnector;
import org.frozenarc.zeframework.evententities.EventEntities;

/**
 * Validating the view to be loaded or not
 * @author Manan
 */
public interface ViewValidator {
    
    /**
     * The method is called in view event, before calling loader to validate the view.
     * @param connector ActionViewConnector
     * @param entities EventEntities
     * @return view name of next view if current view is not to be loaded.
     */
    public String validate(ActionViewConnector connector, EventEntities entities);
    
}
