package org.frozenarc.zeframework.tag;

import org.frozenarc.zeframework.constants.FWConstants;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.frozenarc.zeframework.util.ViewValueUtil;

/**
 * Represents 'modelinfo' tag.
 * @author Manan
 */
public class ActionModelInfoTag extends TagSupport {

    protected String modelname;
    protected String key;
    protected String value;
    protected String valuefrom;

    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            StringBuilder builder = new StringBuilder();
            Object fromvalue;
            if (valuefrom != null) {
                fromvalue = ViewValueUtil.handleValueFrom(pageContext, valuefrom);
            } else {
                fromvalue = value;
            }
            builder.append("<input type=\"hidden\" name=\"" + FWConstants.MODEL_INFO_SIGN + modelname + "\" value=\"" + key + ":" + fromvalue + "\"");
            builder.append(" />");
            out.print(builder.toString());
        } catch (Exception ex) {
            throw new JspException(ex);
        }
        return super.doStartTag();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public String getValuefrom() {
        return valuefrom;
    }

    public void setValuefrom(String valuefrom) {
        this.valuefrom = valuefrom;
    }
}
