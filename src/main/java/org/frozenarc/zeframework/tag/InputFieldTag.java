package org.frozenarc.zeframework.tag;

import org.frozenarc.zeframework.applicationexception.ApplicationException;
import org.frozenarc.zeframework.constants.FWConstants;

/**
 * Super class for input text field.
 * @author Manan
 */
public abstract class InputFieldTag extends InputTag {
    
    protected String maxlength;
    protected String size;
    protected String validations;
    
    @Override
    public void handleValueTo(StringBuilder builder) throws ApplicationException {
        super.handleValueTo(builder);
        builder.append("<input type=\"hidden\" name=\"" + FWConstants.VALIDATIONS_SIGN + name + "\" value=\"" + validations==null?"":validations + "\" />");
    }
    
    @Override
    public void addAtributes(StringBuilder builder) throws ApplicationException {
        super.addAtributes(builder);
        
        if(maxlength!=null) {
            builder.append(" maxlength=\""+maxlength+"\"");
        }
        if(size!=null) {
            builder.append(" size=\""+size+"\"");
        }

    }

    public String getMaxlength() {
        return maxlength;
    }

    public void setMaxlength(String maxlength) {
        this.maxlength = maxlength;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    
    public String getValidations() {
        return validations;
    }

    public void setValidations(String validations) {
        this.validations = validations;
    }
    
}
