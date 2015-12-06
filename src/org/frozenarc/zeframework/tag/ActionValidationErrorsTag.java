package org.frozenarc.zeframework.tag;

import org.frozenarc.zeframework.constants.FWConstants;
import org.frozenarc.zeframework.actionvalidator.ActionValidationError;
import org.frozenarc.zeframework.actionvalidator.ActionValidationErrors;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Represents 'actionvalidationerrors' tag.
 * @author Manan
 */
public class ActionValidationErrorsTag extends TagSupport {

    public Integer getSeverity() {
        return severity;
    }

    public void setSeverity(Integer severity) {
        this.severity = severity;
    }
    protected Integer severity;

    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            StringBuilder builder = new StringBuilder();
            populateValidationErrors(builder);
            pageContext.getOut().print(builder.toString());
        } catch (IOException ex) {
            throw new JspException(ex);
        }
        return super.doStartTag();
    }

    public void populateValidationErrors(StringBuilder builder) {
        ActionValidationErrors validationErrors = (ActionValidationErrors) pageContext.getRequest().getAttribute(FWConstants.VALIDATION_ERRORS);
        if (validationErrors != null) {
            for (ActionValidationError error : validationErrors.getValidationErrorList()) {
                if (severity == null) {
                    builder.append(error.getErrorMessage());
                    builder.append("<br/>");
                } else if (error.getSeverity() == severity) {
                    builder.append(error.getErrorMessage());
                    builder.append("<br/>");
                }
            }
        }
    }
}
