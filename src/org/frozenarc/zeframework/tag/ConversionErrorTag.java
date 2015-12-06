package org.frozenarc.zeframework.tag;

import org.frozenarc.zeframework.constants.FWConstants;
import org.frozenarc.zeframework.datatypeconvert.ConversionError;
import org.frozenarc.zeframework.datatypeconvert.ConversionErrors;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Represents 'conversionerror' tag.
 * @author manan
 */
public class ConversionErrorTag extends TagSupport {

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    protected String param;
    protected String message;

    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            StringBuilder builder = new StringBuilder();
            populateConversionError(builder);
            pageContext.getOut().print(builder.toString());
        } catch (IOException ex) {
            throw new JspException(ex);
        }
        return super.doStartTag();
    }

    public void populateConversionError(StringBuilder builder) {
        ConversionErrors conversionErrors = (ConversionErrors) pageContext.getRequest().getAttribute(FWConstants.CONVERSION_ERRORS);
        if (conversionErrors != null) {
            ConversionError error = conversionErrors.getConversionErrorMap().get(param);
            if (error != null) {
                String value=error.getValue();
                if(message.indexOf(FWConstants.CONVERSION_VALUE_REPLACE)!=-1) {
                    String beforeValue=message.substring(0, message.indexOf(FWConstants.CONVERSION_VALUE_REPLACE));
                    String afterValue=message.substring(message.indexOf(FWConstants.CONVERSION_VALUE_REPLACE)+FWConstants.CONVERSION_VALUE_REPLACE.length());
                    message=beforeValue+value+afterValue;
                }
                //message=message.replaceAll("errorForValue", value);
                builder.append(message);
            }
        }
    }
}
