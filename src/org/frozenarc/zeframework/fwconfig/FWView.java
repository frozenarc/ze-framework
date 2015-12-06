package org.frozenarc.zeframework.fwconfig;

import java.util.List;
import java.util.Map;

/**
 * Represents view what defined in config.
 * @author Manan
 */
public class FWView {
    
    protected String name;
    protected String page;
    protected String loader;
    protected List<FWModel> models;
    protected Map<String, FWModel> modelMap;
    protected Map<String, FWException> exceptionMap;
    protected List<FWException> exceptionList;
    protected List<FWViewValidator> validators;

    public Map<String, FWModel> getModelMap() {
        return modelMap;
    }

    public void setModelMap(Map<String, FWModel> modelMap) {
        this.modelMap = modelMap;
    }

    public String getLoader() {
        return loader;
    }

    public void setLoader(String loader) {
        this.loader = loader;
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
    
    public List<FWViewValidator> getValidators() {
        return validators;
    }

    public void setValidators(List<FWViewValidator> validators) {
        this.validators = validators;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FWView) {
            if(name!=null && ((FWView)obj).getName()!=null) {
                return name.equals(((FWView)obj).getName());
            } 
        }
        return false;
    }
    
}
