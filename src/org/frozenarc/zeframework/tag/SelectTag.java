package org.frozenarc.zeframework.tag;

import org.frozenarc.zeframework.applicationexception.ApplicationException;
import org.frozenarc.zeframework.datatypeconvert.DataTypeConvertor;
import org.frozenarc.zeframework.util.FormatEvaluator;
import org.frozenarc.zeframework.util.FormatInfo;
import org.frozenarc.zeframework.util.ViewValueUtil;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

/**
 * Represents 'select' tag.
 * @author Manan
 */
public abstract class SelectTag extends BasicTag {

    protected String disabled;
    protected String name;
    protected String tabindex;
    
    protected String onblur;
    protected String onchange;
    protected String onfocus;
    protected String onselect;
    protected String onsubmit;
    protected String onload;
    protected String onunload;
    
    protected String valuesfrom;
    protected String displayvaluesfrom;
    protected String selectedvaluesfrom;

    @Override
    public int doEndTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            StringBuilder builder = new StringBuilder();
            builder.append("\n");
            builder.append("</select>");
            handleValueTo(builder);
            out.print(builder.toString());
        } catch(ApplicationException ex) {
            throw new JspException(ex.getMessage(), ex);
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
            builder.append("<select");
            addAtributes(builder);
            builder.append(">");
            insertOptions(builder);
            out.print(builder.toString());
        } catch(ApplicationException ex) {
            throw new JspException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new JspException(ex);
        }
        if (valuesfrom == null) {
            return Tag.EVAL_BODY_INCLUDE;
        } else {
            return super.doStartTag();
        }
    }
    
    public abstract void handleValueTo(StringBuilder builder) throws Exception;

    public void insertOptions(StringBuilder builder) throws Exception {
        if (valuesfrom != null) {
            FormatInfo valuesFromFormatInfo = FormatEvaluator.getFormatInfo(valuesfrom);
            Object objValuesFrom = ViewValueUtil.handleValueFrom(pageContext, valuesfrom);
            String[] values = DataTypeConvertor.convertObjectArray(objValuesFrom, valuesFromFormatInfo);

            String[] displayvalues;
            if (displayvaluesfrom != null) {
                FormatInfo displayValuesFromFormatInfo = FormatEvaluator.getFormatInfo(displayvaluesfrom);
                Object objDisplayValuesFrom = ViewValueUtil.handleValueFrom(pageContext, displayvaluesfrom);
                displayvalues = DataTypeConvertor.convertObjectArray(objDisplayValuesFrom, displayValuesFromFormatInfo);
            } else {
                displayvalues = values;
            }

            String[] selectedvalues = null;
            if (selectedvaluesfrom != null) {
                Object objSelectedValuesFrom = ViewValueUtil.handleValueFrom(pageContext, selectedvaluesfrom);
                selectedvalues = DataTypeConvertor.convertObjectArray(objSelectedValuesFrom, null);
            }

            for (int i = 0; i < values.length; i++) {
                String selected;
                if (selectedvalues != null && selectedvalues[i] != null && selectedvalues[i].equals("true")) {
                    selected = " selected=\"true\"";
                } else {
                    selected = "";
                }
                builder.append("\n");
                builder.append("<option value=\"" + (values[i]==null?"":values[i]) + "\"" + selected + ">" + (displayvalues[i]==null?"":displayvalues[i]) + "</option>");
            }
        }
    }

    @Override
    public void addAtributes(StringBuilder builder) throws ApplicationException {
        super.addAtributes(builder);

        if (disabled != null) {
            builder.append(" disabled=\"" + disabled + "\"");
        }
        if (name != null) {
            builder.append(" name=\"" + name + "\"");
        }

        if (tabindex != null) {
            builder.append(" tabindex=\"" + tabindex + "\"");
        }

        if (onblur != null) {
            builder.append(" onblur=\"" + onblur + "\"");
        }
        if (onchange != null) {
            builder.append(" onchange=\"" + onchange + "\"");
        }
        if (onfocus != null) {
            builder.append(" onfocus=\"" + onfocus + "\"");
        }
        if (onselect != null) {
            builder.append(" onselect=\"" + onselect + "\"");
        }
        if (onsubmit != null) {
            builder.append(" onsubmit=\"" + onsubmit + "\"");
        }
        if (onload != null) {
            builder.append(" onload=\"" + onload + "\"");
        }
        if (onunload != null) {
            builder.append(" onunload=\"" + onunload + "\"");
        }
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnblur() {
        return onblur;
    }

    public void setOnblur(String onblur) {
        this.onblur = onblur;
    }

    public String getOnchange() {
        return onchange;
    }

    public void setOnchange(String onchange) {
        this.onchange = onchange;
    }

    public String getOnfocus() {
        return onfocus;
    }

    public void setOnfocus(String onfocus) {
        this.onfocus = onfocus;
    }

    public String getTabindex() {
        return tabindex;
    }

    public void setTabindex(String tabindex) {
        this.tabindex = tabindex;
    }

    public String getValuesfrom() {
        return valuesfrom;
    }

    public void setValuesfrom(String valuesfrom) {
        this.valuesfrom = valuesfrom;
    }

    public String getDisplayvaluesfrom() {
        return displayvaluesfrom;
    }

    public void setDisplayvaluesfrom(String displayvaluesfrom) {
        this.displayvaluesfrom = displayvaluesfrom;
    }

    public String getSelectedvaluesfrom() {
        return selectedvaluesfrom;
    }

    public void setSelectedvaluesfrom(String selectedvaluesfrom) {
        this.selectedvaluesfrom = selectedvaluesfrom;
    }
    
    public String getOnload() {
        return onload;
    }

    public void setOnload(String onload) {
        this.onload = onload;
    }

    public String getOnselect() {
        return onselect;
    }

    public void setOnselect(String onselect) {
        this.onselect = onselect;
    }

    public String getOnsubmit() {
        return onsubmit;
    }

    public void setOnsubmit(String onsubmit) {
        this.onsubmit = onsubmit;
    }

    public String getOnunload() {
        return onunload;
    }

    public void setOnunload(String onunload) {
        this.onunload = onunload;
    }
}
