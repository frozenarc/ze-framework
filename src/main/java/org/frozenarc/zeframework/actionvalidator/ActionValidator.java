package org.frozenarc.zeframework.actionvalidator;

import org.frozenarc.zeframework.evententities.EventEntities;
import org.frozenarc.zeframework.model.Model;
import java.util.List;

/**
 * The class is used to validate the incoming data. This is a singleton. 
 * @author Manan
 */
public interface ActionValidator {
    
    /**
     * The method is called in action event, before calling handler to validate incoming data.
     * @param models Model[]
     * @param validations List<ActionValidationError>
     * @param entities EventEntities
     * @return true if next validator would be called, otherwise false.
     */
    public boolean validate(Model[] models, List<ActionValidationError> validations, EventEntities entities);
}
