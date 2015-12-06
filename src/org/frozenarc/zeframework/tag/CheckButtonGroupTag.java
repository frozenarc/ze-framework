package org.frozenarc.zeframework.tag;

import org.frozenarc.zeframework.applicationexception.ApplicationException;
import org.frozenarc.zeframework.constants.FWConstants;
import org.frozenarc.zeframework.util.ViewValueUtil;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Represents 'checkbuttongroup' tag.
 * @author Manan
 */
public class CheckButtonGroupTag extends TagSupport {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValuesto() {
        return valuesto;
    }

    public void setValuesto(String valuesto) {
        this.valuesto = valuesto;
    }

    protected String name;
    protected String valuesto;

    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.setAttribute(FWConstants.CHECK_GROUP_TAG_NAME+name, "");
            StringBuilder builder = new StringBuilder();
            handleValuesTo(builder);
            pageContext.getOut().print(builder.toString());
        }  catch(ApplicationException ex) {
            throw new JspException(ex.getMessage(), ex);
        } catch (IOException ex) {
            throw new JspException(ex);
        }
        return super.doStartTag();
    }

    public void handleValuesTo(StringBuilder builder) throws ApplicationException {
        if (valuesto != null) {
            ViewValueUtil.handleValueTo(pageContext, builder, name, valuesto, true);
        }
    }
}
