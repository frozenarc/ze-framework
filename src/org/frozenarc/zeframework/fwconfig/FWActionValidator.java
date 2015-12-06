package org.frozenarc.zeframework.fwconfig;

/**
 * Represents validator what defined in config.
 * @author Manan
 */
public class FWActionValidator {
    
    protected String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.type != null ? this.type.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FWActionValidator) {
            if(type!=null && ((FWActionValidator)obj).getType()!=null) {
                return type.equals(((FWActionValidator)obj).getType());
            } 
        }
        return false;
    }
    
}
