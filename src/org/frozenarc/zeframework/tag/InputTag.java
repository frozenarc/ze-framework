package org.frozenarc.zeframework.tag;

import org.frozenarc.zeframework.datatypeconvert.DataTypeConvertor;
import org.frozenarc.zeframework.util.FormatEvaluator;
import org.frozenarc.zeframework.util.FormatInfo;
import org.frozenarc.zeframework.util.ValueUtil;
import org.frozenarc.zeframework.util.ViewValueUtil;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import org.frozenarc.zeframework.applicationexception.ApplicationException;

/**
 * Super classs for input tags.
 * @author Manan
 */
public abstract class InputTag extends BasicTag {
    
    protected String name;
    protected String value;
    
    protected String readonly;
    protected String disabled;
    
    protected String accesskey;
    protected String tabindex;
    
    protected String onblur;
    protected String onchange;
    protected String onfocus;
    protected String onselect;
    protected String onsubmit;
    protected String onload;
    protected String onunload;
    
    protected String valuefrom;
    protected String valueto;
    
    public abstract String getInputType();
    
    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            StringBuilder builder = new StringBuilder();
            builder.append("<input type=\""+getInputType()+"\" ");
            addAtributes(builder);
            handleValueFrom(builder);
            builder.append(" />");
            handleValueTo(builder);
            out.print(builder.toString());
        }  catch(ApplicationException ex) {
            throw new JspException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new JspException(ex);
        }
        return super.doStartTag();
    }
    
    public void handleValueFrom(StringBuilder builder) throws Exception {
        if (valuefrom != null) {
            Object objFromValue = ViewValueUtil.handleValueFrom(pageContext, valuefrom);
            FormatInfo info = FormatEvaluator.getFormatInfo(valuefrom);
            String fromvalue = DataTypeConvertor.convertObject(objFromValue, info);
            builder.append(" value=\"" + fromvalue + "\"");
        }
    }
    
    public void handleValueTo(StringBuilder builder) throws ApplicationException {
        if(valueto!=null) {
            ViewValueUtil.handleValueTo(pageContext, builder, name, valueto, false);
        }
    }
    
    public String handleValue(String value) throws ApplicationException {
        Object objValue = ValueUtil.handleValue(pageContext, value);
        FormatInfo info = FormatEvaluator.getFormatInfo(value);
        return DataTypeConvertor.convertObject(objValue, info);
    }
    
    @Override
    public void addAtributes(StringBuilder builder) throws ApplicationException {
        super.addAtributes(builder);
        
        if(name!=null) {
            builder.append(" name=\""+name+"\"");
        }
        if(value!=null && valuefrom==null) {
            value=handleValue(value);
            builder.append(" value=\""+value+"\"");
        }
        if(readonly!=null) {
            builder.append(" readonly=\""+readonly+"\"");
        }
        if(disabled!=null) {
            builder.append(" disabled=\""+disabled+"\"");
        }
        
        if(accesskey!=null) {
            builder.append(" accesskey=\""+accesskey+"\"");
        }
        if(tabindex!=null) {
            builder.append(" tabindex=\""+tabindex+"\"");
        }
        
        if(onblur!=null) {
            builder.append(" onblur=\""+onblur+"\"");
        }
        if(onchange!=null) {
            builder.append(" onchange=\""+onchange+"\"");
        }
        if(onfocus!=null) {
            builder.append(" onfocus=\""+onfocus+"\"");
        }
        if(onselect!=null) {
            builder.append(" onselect=\""+onselect+"\"");
        }
        if(onsubmit!=null) {
            builder.append(" onsubmit=\""+onsubmit+"\"");
        }
        if(onload!=null) {
            builder.append(" onload=\""+onload+"\"");
        }
        if(onunload!=null) {
            builder.append(" onunload=\""+onunload+"\"");
        }
    }

    public String getReadonly() {
        return readonly;
    }

    public void setReadonly(String readonly) {
        this.readonly = readonly;
    }

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
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

    public String getOnselect() {
        return onselect;
    }

    public void setOnselect(String onselect) {
        this.onselect = onselect;
    }

    public String getTabindex() {
        return tabindex;
    }

    public void setTabindex(String tabindex) {
        this.tabindex = tabindex;
    }
    
    public String getValuefrom() {
        return valuefrom;
    }

    public void setValuefrom(String valuefrom) {
        this.valuefrom = valuefrom;
    }

    public String getValueto() {
        return valueto;
    }

    public void setValueto(String valueto) {
        this.valueto = valueto;
    }
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public String getOnload() {
        return onload;
    }

    public void setOnload(String onload) {
        this.onload = onload;
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
