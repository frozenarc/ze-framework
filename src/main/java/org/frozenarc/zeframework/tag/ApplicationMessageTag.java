package org.frozenarc.zeframework.tag;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.frozenarc.zeframework.applicationmessage.ApplicationMessage;
import org.frozenarc.zeframework.applicationmessage.ApplicationMessages;
import org.frozenarc.zeframework.constants.FWConstants;

/**
 * Represents 'applicationmessage' tag.
 * @author Manan
 */
public class ApplicationMessageTag extends TagSupport {

    public Integer getMessagecode() {
        return messagecode;
    }

    public void setMessagecode(Integer messagecode) {
        this.messagecode = messagecode;
    }
    
    protected Integer messagecode;
    
    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            StringBuilder builder = new StringBuilder();
            populateApplicationMessage(builder);
            pageContext.getOut().print(builder.toString());
        } catch (IOException ex) {
            throw new JspException(ex);
        }
        return super.doStartTag();
    }

    public void populateApplicationMessage(StringBuilder builder) {
        ApplicationMessages applicationMessages = (ApplicationMessages) ((HttpServletRequest)pageContext.getRequest()).getSession().getAttribute(FWConstants.APPLICATION_MESSAGES);
        if (applicationMessages != null) {
            ApplicationMessage message = applicationMessages.getApplicationMessageMap().get(messagecode);
            if (message != null) {
                builder.append(message.getMessage());
            }
        }
    }

}
