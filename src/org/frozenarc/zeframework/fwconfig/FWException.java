package org.frozenarc.zeframework.fwconfig;

/**
 * Represents exception what defined in config.
 * @author Manan
 */
public class FWException {
    
    protected String type;
    protected String page;
    protected String handler;
    
    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.type != null ? this.type.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FWException) {
            if(type!=null && ((FWException)obj).getType()!=null) {
                return type.equals(((FWException)obj).getType());
            } 
        }
        return false;
    }
    
}
