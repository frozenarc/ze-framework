package org.frozenarc.zeframework.connector;

import java.util.HashMap;

/**
 * The class is used to connect action and view. when user submit a form the action occurs 
 * and it will be handled by ActionHandler, after that the view loading starts. The two process handling
 * action and loading view should be connected by somthing. That purpose the class solves.
 * @author Manan
 */
public class ActionViewConnector {
    
    protected HashMap map;
    protected String action;
    protected String view;
    
    /**
     * Constructor
     */
    public ActionViewConnector() {
        map=new HashMap();
    }
    
    /**
     * Preserves some value to use after.
     * @param key
     * @param value
     */
    public void putValue(Object key, Object value) {
        map.put(key, value);
    }
    
    /**
     * Use value which is preserved before.
     * @param key
     * @return value
     */
    public Object getValue(Object key) {
        return map.get(key);
    }
    
    /**
     * Remove preserved value.
     * @param key
     */
    public void removeValue(Object key) {
        map.remove(key);
    } 
    
    /**
     * The class connects action and view, so the method returns action nane of the action.
     * @return actionname
     */
    public String getAction() {
        return action;
    }

    /**
     * The class connects action and view, so the method set action nane of the action.
     * The method is called by framework
     * @param action
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * The class connects action and view, so the method returns view nane of the view.
     * @return viewname
     */
    public String getView() {
        return view;
    }

    /**
     * The class connects action and view, so the method set view nane of the view.
     * The method is called by framework
     * @param view
     */
    public void setView(String view) {
        this.view = view;
    }
}
