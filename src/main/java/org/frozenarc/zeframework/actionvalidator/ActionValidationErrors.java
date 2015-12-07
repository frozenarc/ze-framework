package org.frozenarc.zeframework.actionvalidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents validation errors generated in validator.
 * @author Manan
 */
public class ActionValidationErrors {

    public List<ActionValidationError> getValidationErrorList() {
        return validationErrorList;
    }

    public Map<Integer, ActionValidationError> getValidationErrorMap() {
        return validationErrorMap;
    }
    
    protected Map<Integer, ActionValidationError> validationErrorMap;
    protected List<ActionValidationError> validationErrorList;
    
    public ActionValidationErrors(List<ActionValidationError> validationErrorList) {
        this.validationErrorList=validationErrorList;
        validationErrorMap=new HashMap<Integer, ActionValidationError>();
        for(ActionValidationError error : validationErrorList) {
            validationErrorMap.put(error.getErrorCode(), error);
        }
    }
}
