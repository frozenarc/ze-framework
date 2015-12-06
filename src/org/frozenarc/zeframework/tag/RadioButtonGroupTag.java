package org.frozenarc.zeframework.tag;

import org.frozenarc.zeframework.applicationexception.ApplicationException;
import org.frozenarc.zeframework.constants.FWConstants;
import org.frozenarc.zeframework.util.ViewValueUtil;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Represents 'radiobuttongroup' tag.
 * @author Manan
 */
public class RadioButtonGroupTag extends TagSupport {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValueto() {
        return valueto;
    }

    public void setValueto(String valueto) {
        this.valueto = valueto;
    }

    protected String name;
    protected String valueto;

    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.setAttribute(FWConstants.RADIO_GROUP_TAG_NAME+name, "");
            StringBuilder builder = new StringBuilder();
            handleValueTo(builder);
            pageContext.getOut().print(builder.toString());
        } catch(ApplicationException ex) {
            throw new JspException(ex.getMessage(), ex);
        } catch (IOException ex) {
            throw new JspException(ex);
        }
        return super.doStartTag();
    }

    public void handleValueTo(StringBuilder builder) throws ApplicationException {
        if (valueto != null) {
            ViewValueUtil.handleValueTo(pageContext, builder, name, valueto, false);
        }
    }
}
