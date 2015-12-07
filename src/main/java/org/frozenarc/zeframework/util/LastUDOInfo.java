package org.frozenarc.zeframework.util;

/**
 * Represents last user defined object.
 * @author Manan
 */
public class LastUDOInfo {

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public LastUDOInfo(Object object, String property) {
        this.object = object;
        this.property = property;
    }
    
    protected Object object;
    protected String property;
}
