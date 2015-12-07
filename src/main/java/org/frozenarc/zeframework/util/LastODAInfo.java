package org.frozenarc.zeframework.util;

/**
 * Represents last one dimension array.
 * @author Manan
 */
public class LastODAInfo {
    
    protected Object array;
    protected int index;

    public Object getArray() {
        return array;
    }

    public void setArray(Object array) {
        this.array = array;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public LastODAInfo(Object array, int index) {
        this.array = array;
        this.index = index;
    }
    
}
