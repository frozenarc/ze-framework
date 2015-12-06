package org.frozenarc.zeframework.tag;

import org.frozenarc.zeframework.applicationexception.ApplicationException;
import org.frozenarc.zeframework.constants.FWConstants;

/**
 * Represents 'radiobutton' tag.
 * @author Manan
 */
public class RadioButtonTag extends InputButtonTag {
    @Override
    public String getInputType() {
        return "radio";
    }
    
    @Override
    public void addAtributes(StringBuilder builder) throws ApplicationException {
        if(pageContext.getAttribute(FWConstants.RADIO_GROUP_TAG_NAME+groupname)!=null) {
            name=groupname;
            valueto=null;
        }
        super.addAtributes(builder);
    }
}
