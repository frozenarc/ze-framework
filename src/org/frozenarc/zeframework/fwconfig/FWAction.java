package org.frozenarc.zeframework.fwconfig;

import java.util.List;
import java.util.Map;

/**
 * Represents action what defined in config.
 * @author Manan
 */
public class FWAction {

    protected String name;
    protected String handler;
    protected List<FWModel> models;
    protected Map<String, FWModel> modelMap;
    protected Map<String, FWException> exceptionMap;
    protected List<FWException> exceptionList;
    protected List<FWActionValidator> validators;

    public Map<String, FWModel> getModelMap() {
        return modelMap;
    }

    public void setModelMap(Map<String, FWModel> modelMap) {
        this.modelMap = modelMap;
    }
    
    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public List<FWModel> getModels() {
        return models;
    }

    public void setModels(List<FWModel> models) {
        this.models = models;
    }
    
    public Map<String, FWException> getExceptionMap() {
        return exceptionMap;
    }

    public void setExceptionMap(Map<String, FWException> exceptionMap) {
        this.exceptionMap = exceptionMap;
    }
    
    
    public List<FWException> getExceptionList() {
        return exceptionList;
    }

    public void setExceptionList(List<FWException> exceptionList) {
        this.exceptionList = exceptionList;
    }
    
    public List<FWActionValidator> getValidators() {
        return validators;
    }

    public void setValidators(List<FWActionValidator> validators) {
        this.validators = validators;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FWAction) {
            if(name!=null && ((FWAction)obj).getName()!=null) {
                return name.equals(((FWAction)obj).getName());
            } 
        }
        return false;
    }
    
}
