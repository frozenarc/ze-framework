package org.frozenarc.zeframework.tag;

import org.frozenarc.zeframework.datatypeconvert.DataTypeConvertor;
import org.frozenarc.zeframework.util.FormatEvaluator;
import org.frozenarc.zeframework.util.FormatInfo;
import org.frozenarc.zeframework.util.ValueUtil;
import org.frozenarc.zeframework.util.ViewValueUtil;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import org.frozenarc.zeframework.applicationexception.ApplicationException;

/**
 * Represents 'option' tag.
 * @author Manan
 */
public class OptionTag extends BasicTag {

    protected String disabled;
    protected String label;
    protected String selected;
    protected String value;
    protected String displayvalue;
    protected String valuefrom;
    protected String displayvaluefrom;
    protected String selectedvaluefrom;

    @Override
    public int doEndTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            StringBuilder builder = new StringBuilder();
            builder.append("</option>");
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
            builder.append("<option");
            addAtributes(builder);
            handleValueFrom(builder);
            builder.append(">");
            out.print(builder.toString());
        } catch(ApplicationException ex) {
            throw new JspException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new JspException(ex);
        }
        return Tag.EVAL_BODY_INCLUDE;
    }

    public void handleValueFrom(StringBuilder builder) throws Exception {
        if (valuefrom != null) {
            Object objFromValue = ViewValueUtil.handleValueFrom(pageContext, valuefrom);
            FormatInfo info = FormatEvaluator.getFormatInfo(valuefrom);
            String fromvalue = DataTypeConvertor.convertObject(objFromValue, info);
            String selectedvalue = null;
            if (selectedvaluefrom == null) {
                Object objSelectedValueFrom = ViewValueUtil.handleValueFrom(pageContext, selectedvaluefrom);
                selectedvalue = DataTypeConvertor.convertObject(objSelectedValueFrom, null);
            }
            String selectedval;
            if (selectedvalue != null && selectedvalue.equals("true")) {
                selectedval = " selected=\"true\"";
            } else {
                selectedval = "";
            }
            builder.append(" value=\"" + fromvalue + "\"" + selectedval + "");
        }
    }

    @Override
    public void addAtributes(StringBuilder builder) throws ApplicationException {
        super.addAtributes(builder);

        if (disabled != null) {
            builder.append(" disabled=\"" + disabled + "\"");
        }
        if (label != null) {
            builder.append(" label=\"" + label + "\"");
        }
        if (selected != null) {
            builder.append(" selected=\"" + selected + "\"");
        }
        if (value != null && valuefrom == null) {
            value = handleValue(value);
            builder.append(" value=\"" + value + "\"");
        }
    }

    public String handleValue(String value) throws ApplicationException {
        Object objValue = ValueUtil.handleValue(pageContext, value);
        FormatInfo info = FormatEvaluator.getFormatInfo(value);
        return DataTypeConvertor.convertObject(objValue, info);
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDisplayvalue() {
        return displayvalue;
    }

    public void setDisplayvalue(String displayvalue) {
        this.displayvalue = displayvalue;
    }

    public String getDisplayvaluefrom() {
        return displayvaluefrom;
    }

    public void setDisplayvaluefrom(String displayvaluefrom) {
        this.displayvaluefrom = displayvaluefrom;
    }

    public String getValuefrom() {
        return valuefrom;
    }

    public void setValuefrom(String valuefrom) {
        this.valuefrom = valuefrom;
    }

    public String getSelectedvaluefrom() {
        return selectedvaluefrom;
    }

    public void setSelectedvaluefrom(String selectedvaluefrom) {
        this.selectedvaluefrom = selectedvaluefrom;
    }
}
