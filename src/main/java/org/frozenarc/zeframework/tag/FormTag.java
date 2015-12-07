package org.frozenarc.zeframework.tag;

import org.frozenarc.zeframework.applicationexception.ApplicationException;
import org.frozenarc.zeframework.constants.FWConstants;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

/**
 * Represents 'form' tag.
 * @author Manan
 */
public class FormTag extends BasicTag {

    protected String action;
    protected String accept;
    protected String acceptcharset;
    protected String enctype;
    protected String method;
    protected String name;
    protected String onreset;
    protected String onsubmit;

    @Override
    public int doEndTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            StringBuilder builder = new StringBuilder();
            builder.append("</form>");
            out.print(builder.toString());
        } catch (Exception ex) {
            throw new JspException(ex);
        }
        return super.doEndTag();
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            StringBuilder builder = new StringBuilder();
            builder.append("<form");
            addAtributes(builder);
            builder.append(">");
            addViewName(builder);
            out.print(builder.toString());
        } catch (Exception ex) {
            throw new JspException(ex);
        }
        return Tag.EVAL_BODY_INCLUDE;
    }

    public void addViewName(StringBuilder builder) {
        String viewName = (String) pageContext.getRequest().getAttribute(FWConstants.VIEW_NAME);
        viewName= viewName == null ? "" : viewName;
        builder.append("<input type=\"hidden\" name=\"" + FWConstants.VIEW_NAME_VALUE + "\" value=\"" + viewName + "\" />");
    }

    public void addAtributes(StringBuilder builder) throws ApplicationException {
        super.addAtributes(builder);
        if (name != null) {
            builder.append(" name=\"" + name + "\"");
        }
        if (action != null) {
            builder.append(" action=\"" + action + FWConstants.ACTION_SUFFIX + "\"");
        }
        if (accept != null) {
            builder.append(" accept=\"" + accept + "\"");
        }
        if (acceptcharset != null) {
            builder.append(" accept-charset=\"" + acceptcharset + "\"");
        }
        if (enctype != null) {
            builder.append(" enctype=\"" + enctype + "\"");
        }
        if (method != null) {
            builder.append(" method=\"" + method + "\"");
        }
        if (onreset != null) {
            builder.append(" onreset=\"" + onreset + "\"");
        }
        if (onsubmit != null) {
            builder.append(" onsubmit=\"" + onsubmit + "\"");
        }
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getAcceptcharset() {
        return acceptcharset;
    }

    public void setAcceptcharset(String acceptcharset) {
        this.acceptcharset = acceptcharset;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getEnctype() {
        return enctype;
    }

    public void setEnctype(String enctype) {
        this.enctype = enctype;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnreset() {
        return onreset;
    }

    public void setOnreset(String onreset) {
        this.onreset = onreset;
    }

    public String getOnsubmit() {
        return onsubmit;
    }

    public void setOnsubmit(String onsubmit) {
        this.onsubmit = onsubmit;
    }
    
}
