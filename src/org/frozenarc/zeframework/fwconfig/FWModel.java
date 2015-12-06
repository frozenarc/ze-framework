package org.frozenarc.zeframework.fwconfig;

/**
 * Represents model what defined in config.
 * @author Manan
 */
public class FWModel {
    
    protected String name;
    protected String type;
    protected String scope;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    } 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FWModel) {
            if(name!=null && ((FWModel)obj).getName()!=null) {
                return name.equals(((FWModel)obj).getName());
            } 
        }
        return false;
    }
}
