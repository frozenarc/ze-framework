package org.frozenarc.zeframework.tag;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.frozenarc.zeframework.applicationmessage.ApplicationMessage;
import org.frozenarc.zeframework.applicationmessage.ApplicationMessageType;
import org.frozenarc.zeframework.applicationmessage.ApplicationMessages;
import org.frozenarc.zeframework.constants.FWConstants;

/**
 * Represents 'applicationmessages' tag.
 * @author Manan
 */
public class ApplicationMessagesTag extends TagSupport {

    public String getMessagetype() {
        return messagetype.toString();
    }

    public void setMessagetype(String messagetype) {
        this.messagetype=ApplicationMessageType.valueOf(messagetype);
    }
    
    protected ApplicationMessageType messagetype;
    
    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            StringBuilder builder = new StringBuilder();
            populateApplicationMessages(builder);
            pageContext.getOut().print(builder.toString());
        } catch (IOException ex) {
            throw new JspException(ex);
        }
        return super.doStartTag();
    }

    public void populateApplicationMessages(StringBuilder builder) {
        ApplicationMessages applicationMessages = (ApplicationMessages) ((HttpServletRequest)pageContext.getRequest()).getSession().getAttribute(FWConstants.APPLICATION_MESSAGES);
        if (applicationMessages != null) {
            for (ApplicationMessage message : applicationMessages.getApplicationMessageList()) {
                if (messagetype==null) {
                    builder.append(message.getMessage());
                    builder.append("<br/>");
                } else if (message.getMessageType().equals(messagetype)) {
                    builder.append(message.getMessage());
                    builder.append("<br/>");
                }
            }
        }
    }
    
}
