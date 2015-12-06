package org.frozenarc.zeframework.fwconfig;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents whole configuration.
 * @author Manan
 */
public class FWConfig {
    
    protected String appInitializer;
    protected String appDestroyer;
    protected String processingTube;
    protected HashMap<String, FWAction> actionMap;
    protected HashMap<String, FWView> viewMap;
    protected HashMap<String, FWException> exceptionMap;
    protected ArrayList<FWException> exceptionList;
    protected HashMap<String, Boolean> actionViewMap;
    protected String appPropsFilePath;
    protected ArrayList<FWAction> fWActionList;
    protected ArrayList<FWView> fWViewList;
    
    public FWConfig() {
        actionMap=new HashMap<String, FWAction>();
        viewMap=new HashMap<String, FWView>();
        actionViewMap=new HashMap<String, Boolean>();
        exceptionMap=new HashMap<String, FWException>();
        exceptionList=new ArrayList<FWException>();
        fWActionList=new ArrayList<FWAction>();
        fWViewList=new ArrayList<FWView>();
    }

    public String getAppDestroyer() {
        return appDestroyer;
    }

    public void setAppDestroyer(String appDestroyer) {
        this.appDestroyer = appDestroyer;
    }
    
    public String getProcessingTube() {
        return processingTube;
    }

    public void setProcessingTube(String processingTube) {
        this.processingTube = processingTube;
    }

    public String getAppInitializer() {
        return appInitializer;
    }

    public void setAppInitializer(String appInitializer) {
        this.appInitializer = appInitializer;
    }
    
    public void putAction(String actionName, FWAction action) {
        actionMap.put(actionName, action);
    }
    
    public void putView(String viewName, FWView view) {
        viewMap.put(viewName, view);
    }
    
    public FWAction getAction(String actionName) {
        return actionMap.get(actionName);
    }
    
    public FWView getView(String viewName) {
        return viewMap.get(viewName);
    }
    
    public FWException getException(String type) {
        return exceptionMap.get(type);
    }
    
    public void putException(String type, FWException exception) {
        exceptionMap.put(type, exception);
    }
    
    public ArrayList<FWException> getFWExceptionList() {
        return exceptionList;
    }
    
    public Boolean getActionViewSendRedirect(String actionViewName) {
        return actionViewMap.get(actionViewName);
    }
    
    public void putActionViewSendRedirect(String actionViewName, Boolean sendRedirect) {
        actionViewMap.put(actionViewName, sendRedirect);
    }
    
    
    public String getAppPropsFilePath() {
        return appPropsFilePath;
    }

    public void setAppPropsFilePath(String appPropsFilePath) {
        this.appPropsFilePath = appPropsFilePath;
    }
    
    
    public ArrayList<FWAction> getFWActionList() {
        return fWActionList;
    }

    public ArrayList<FWView> getFWViewList() {
        return fWViewList;
    }
    
}
