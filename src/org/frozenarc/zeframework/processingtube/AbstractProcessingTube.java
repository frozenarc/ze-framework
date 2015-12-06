package org.frozenarc.zeframework.processingtube;

import org.frozenarc.zeframework.actionhandler.ActionHandler;
import org.frozenarc.zeframework.connector.ActionViewConnector;
import org.frozenarc.zeframework.evententities.EventEntities;
import org.frozenarc.zeframework.model.Model;
import org.frozenarc.zeframework.viewloader.ViewLoader;

/**
 * The class should be extended and defined in config to do some specific work on each request.
 * @author Manan
 */
public abstract class AbstractProcessingTube implements ProcessingTube {
    
    /**
     * The method is called before action processing started.
     * @param models Model[]
     * @param connector ActionViewConnector
     * @param entities EventEntities
     */
    public abstract void beforeProcessAction(Model[] models, ActionViewConnector connector, EventEntities entities);
    
    /**
     * The method is called after action processing finished.
     * @param models Model[]
     * @param connector ActionViewConnector
     * @param entities EventEntities
     */
    public abstract void afterProcessAction(Model[] models, ActionViewConnector connector, EventEntities entities);
    
    /**
     * The method is called before view processing started.
     * @param models Model[]
     * @param connector ActionViewConnector
     * @param entities EventEntities
     */
    public abstract void beforeProcessView(Model[] models, ActionViewConnector connector, EventEntities entities);
    
    /**
     * The method is called after view processing finished.
     * @param models Model[]
     * @param connector ActionViewConnector
     * @param entities EventEntities
     */
    public abstract void afterProcessView(Model[] models, ActionViewConnector connector, EventEntities entities);
    
    public final String processActionTube(ActionHandler handler, Model[] models, ActionViewConnector connector, EventEntities entities) throws Exception {
        beforeProcessAction(models, connector, entities);
        String viewName= handler.handleAction(models, connector, entities);
        afterProcessAction(models, connector, entities);
        return viewName;
    }
    
    public final void processViewTube(ViewLoader loader, Model[] models, ActionViewConnector connector,EventEntities entities) throws Exception {
        beforeProcessView(models, connector, entities);
        loader.loadView(models, connector, entities);
        afterProcessView(models, connector, entities);
    }
    
}
