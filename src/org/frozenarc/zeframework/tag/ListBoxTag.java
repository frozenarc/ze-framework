package org.frozenarc.zeframework.tag;

import org.frozenarc.zeframework.applicationexception.ApplicationException;
import org.frozenarc.zeframework.util.ViewValueUtil;

/**
 * Represents 'listbox' tag.
 * @author Manan
 */
public class ListBoxTag extends SelectTag {
    
    protected String size;
    protected String valuesto;

    @Override
    public void addAtributes(StringBuilder builder) throws ApplicationException {
        super.addAtributes(builder);
        builder.append(" multiple=\"multiple\"");
        if (size != null) {
            builder.append(" size=\"" + size + "\"");
        } else {
            builder.append(" size=\"4\"");
        }
    }
    
    public void handleValueTo(StringBuilder builder) throws ApplicationException {
        if (valuesto != null) {
            ViewValueUtil.handleValueTo(pageContext, builder, name, valuesto, true);
        }
    }
    
    public String getValuesto() {
        return valuesto;
    }

    public void setValuesto(String valuesto) {
        this.valuesto = valuesto;
    }
    
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
