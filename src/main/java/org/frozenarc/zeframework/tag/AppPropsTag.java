package org.frozenarc.zeframework.tag;

import org.frozenarc.zeframework.applicationproperties.ApplicationProperties;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Represents 'appprops' tag.
 * @author Manan
 */
public class AppPropsTag extends TagSupport {

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
    protected String key;
    
    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            StringBuilder builder=new StringBuilder();
            populateProps(builder);
            pageContext.getOut().print(builder.toString());
        } catch (IOException ex) {
            throw new JspException(ex);
        }
        return super.doStartTag();
    }
    
    public void populateProps(StringBuilder builder) {
        String value=ApplicationProperties.getProperty(key);
        builder.append(value==null?"":value);
    }
}
