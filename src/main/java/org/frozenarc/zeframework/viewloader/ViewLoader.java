package org.frozenarc.zeframework.viewloader;

import org.frozenarc.zeframework.connector.ActionViewConnector;
import org.frozenarc.zeframework.evententities.EventEntities;
import org.frozenarc.zeframework.model.Model;
import org.frozenarc.zeframework.eventprocessor.EventProcessor;

/**
 * The class is used to load model which's data will be displayed on page. 
 * This is a singleton.
 * @author Manan
 */
public interface ViewLoader extends EventProcessor {
    
    /**
     * 
     * @param models Model[] what should load with data.
     * @param connector ActionViewConnector comes with information of succeeded action, null if the view is called directly.
     * @param entities EventEntities have HttpServletRequest and HttpServletResponse object.
     * @throws java.lang.Exception
     */
    void loadView(Model[] models, ActionViewConnector connector, EventEntities entities) throws Exception;
}
