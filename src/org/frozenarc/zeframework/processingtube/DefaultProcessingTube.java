package org.frozenarc.zeframework.processingtube;

import org.frozenarc.zeframework.connector.ActionViewConnector;
import org.frozenarc.zeframework.evententities.EventEntities;
import org.frozenarc.zeframework.model.Model;

/**
 * The class is used if no class is defined in config.
 * @author Manan
 */
public class DefaultProcessingTube extends AbstractProcessingTube {
    
    @Override
    public void beforeProcessAction(Model[] models, ActionViewConnector connector, EventEntities entities) {
        
    }

    @Override
    public void afterProcessAction(Model[] models, ActionViewConnector connector, EventEntities entities) {
        
    }

    @Override
    public void beforeProcessView(Model[] models, ActionViewConnector connector, EventEntities entities) {
        
    }
    
    @Override
    public void afterProcessView(Model[] models, ActionViewConnector connector, EventEntities entities) {
        
    }

}
