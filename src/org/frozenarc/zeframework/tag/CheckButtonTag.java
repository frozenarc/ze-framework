package org.frozenarc.zeframework.tag;

import org.frozenarc.zeframework.applicationexception.ApplicationException;
import org.frozenarc.zeframework.constants.FWConstants;

/**
 * Represents 'checkbutton' tag.
 * @author Manan
 */
public class CheckButtonTag extends InputButtonTag {
    @Override
    public String getInputType() {
        return "checkbox";
    }
    
    @Override
    public void addAtributes(StringBuilder builder) throws ApplicationException {
        if(pageContext.getAttribute(FWConstants.CHECK_GROUP_TAG_NAME+groupname)!=null) {
            name=groupname;
            valueto=null;
        }
        super.addAtributes(builder);
    }
}
