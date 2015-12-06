package org.frozenarc.zeframework.tag;

import org.frozenarc.zeframework.applicationexception.ApplicationException;
import org.frozenarc.zeframework.datatypeconvert.DataTypeConvertor;
import org.frozenarc.zeframework.util.FormatEvaluator;
import org.frozenarc.zeframework.util.FormatInfo;
import org.frozenarc.zeframework.util.ValueUtil;
import org.frozenarc.zeframework.util.ViewValueUtil;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Represents 'out' tag.
 * @author Manan
 */
public class OutTag extends TagSupport {

    protected String value;
    protected String valuefrom;

    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            StringBuilder builder = new StringBuilder();
            if (value != null && valuefrom == null) {
                value = handleValue(value);
                builder.append(value);
            }
            handleValueFrom(builder);
            pageContext.getOut().print(builder.toString());
        } catch (ApplicationException ex) {
            throw new JspException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new JspException(ex);
        }
        return super.doStartTag();
    }

    public String handleValue(String value) throws ApplicationException {
        Object objValue = ValueUtil.handleValue(pageContext, value);
        FormatInfo info = FormatEvaluator.getFormatInfo(value);
        return DataTypeConvertor.convertObject(objValue, info);
    }

    public void handleValueFrom(StringBuilder builder) throws Exception {
        if (valuefrom != null) {
            Object objFromValue = ViewValueUtil.handleValueFrom(pageContext, valuefrom);
            FormatInfo info = FormatEvaluator.getFormatInfo(valuefrom);
            String fromvalue = DataTypeConvertor.convertObject(objFromValue, info);
            builder.append(fromvalue);
        }
    }

    public String getValuefrom() {
        return valuefrom;
    }

    public void setValuefrom(String valuefrom) {
        this.valuefrom = valuefrom;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
