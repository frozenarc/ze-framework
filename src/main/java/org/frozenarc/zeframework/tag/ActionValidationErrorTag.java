package org.frozenarc.zeframework.tag;

import org.frozenarc.zeframework.constants.FWConstants;
import org.frozenarc.zeframework.actionvalidator.ActionValidationError;
import org.frozenarc.zeframework.actionvalidator.ActionValidationErrors;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Represents 'actionvalidationerros' tag.
 * @author Manan
 */
public class ActionValidationErrorTag extends TagSupport {

    public Integer getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(Integer errorcode) {
        this.errorcode = errorcode;
    }
    protected Integer errorcode;

    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            StringBuilder builder = new StringBuilder();
            populateValidationError(builder);
            pageContext.getOut().print(builder.toString());
        } catch (IOException ex) {
            throw new JspException(ex);
        }
        return super.doStartTag();
    }

    public void populateValidationError(StringBuilder builder) {
        ActionValidationErrors validationErrors = (ActionValidationErrors) pageContext.getRequest().getAttribute(FWConstants.VALIDATION_ERRORS);
        if (validationErrors != null) {
            ActionValidationError error = validationErrors.getValidationErrorMap().get(errorcode);
            if (error != null) {
                builder.append(error.getErrorMessage());
            }
        }
    }
}
