package org.frozenarc.zeframework.fwconfig;

/**
 *
 * @author Manan
 */
public class FWViewValidator {
    
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
        if(obj instanceof FWViewValidator) {
            if(type!=null && ((FWViewValidator)obj).getType()!=null) {
                return type.equals(((FWViewValidator)obj).getType());
            } 
        }
        return false;
    }
}
