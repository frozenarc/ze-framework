package org.frozenarc.zeframework.tag;

import org.frozenarc.zeframework.applicationexception.ApplicationException;
import org.frozenarc.zeframework.util.ViewValueUtil;

/**
 * Represents 'combobox' tag.
 * @author Manan
 */
public class ComboBoxTag extends SelectTag {

    public void handleValueTo(StringBuilder builder) throws ApplicationException {
        if (valueto != null) {
            ViewValueUtil.handleValueTo(pageContext, builder, name, valueto, false);
        }
    }
    
    public String getValueto() {
        return valueto;
    }

    public void setValueto(String valueto) {
        this.valueto = valueto;
    }
    
    protected String valueto;
}
