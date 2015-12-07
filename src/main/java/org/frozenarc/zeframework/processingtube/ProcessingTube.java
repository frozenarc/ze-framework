package org.frozenarc.zeframework.processingtube;

import org.frozenarc.zeframework.actionhandler.ActionHandler;
import org.frozenarc.zeframework.connector.ActionViewConnector;
import org.frozenarc.zeframework.evententities.EventEntities;
import org.frozenarc.zeframework.model.Model;
import org.frozenarc.zeframework.viewloader.ViewLoader;

/**
 * Super interface. 
 * It is recommend not to implement this interface directly. 
 * Use AbstractProcessingTube abstract class instead.
 * @author Manan
 */
public interface ProcessingTube {
    
    public String processActionTube(ActionHandler handler, Model[] models, ActionViewConnector connector, EventEntities entities) throws Exception;
    
    public void processViewTube(ViewLoader loader, Model[] models, ActionViewConnector connector, EventEntities entities) throws Exception;
}
