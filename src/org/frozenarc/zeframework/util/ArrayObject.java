package org.frozenarc.zeframework.util;

/**
 * The class used to preserve array object for finding array length.
 * @author Manan
 */
public class ArrayObject {

    public Object getArrayObj() {
        return arrayObj;
    }

    public void setArrayObj(Object arrayObj) {
        this.arrayObj = arrayObj;
    }

    public ArrayObject(Object arrayObj) {
        this.arrayObj = arrayObj;
    }
    
    protected Object arrayObj;
}
