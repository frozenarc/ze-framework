package org.frozenarc.zeframework.model;

/**
 * The class is used with action or view.
 * When a view starts loading, it loads data into the models.
 * When a action starts handling, it populates request data into the models.
 * The model can be in one of theree type of scope (request, session, application).
 * @author Manan
 */
public interface Model {
    
    /**
     * The model can be in any scope, so request generation doesn't mean that new model.
     * So, when action request is generated the method will be called.
     * @param info Can be passed from jsp page via 'modelinfo' tag.
     */
    void actionInit(ModelInfo info);
    
    /**
     * The model can be in any scope, so request generation doesn't mean that new model.
     * So, when view request is generated the method will be called.
     */
    void viewInit();
}
