package org.frozenarc.zeframework.tag;

import org.frozenarc.zeframework.applicationexception.ApplicationException;
import org.frozenarc.zeframework.util.ViewValueUtil;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.frozenarc.zeframework.util.ValueUtil;

/**
 * Abstract class to represents variable tag.
 * @author Manan
 */
public abstract class AbstractVariableTag extends TagSupport {    

    protected String variable;
    protected String typeclass;
    protected String valuefrom;
    protected String value;

    @Override
    public int doStartTag() throws JspException {
        try {
            Object valueObj = null;
            if (valuefrom != null) {
                valueObj = ViewValueUtil.handleValueFrom(pageContext, valuefrom);
            } else if (value != null) {
                valueObj = ValueUtil.handleValue(pageContext, value);
                if(valueObj instanceof String) {
                    if(typeclass.equals("java.lang.Integer")) {
                        valueObj=Integer.parseInt((String)valueObj);
                    } else if(typeclass.equals("java.lang.Long")) {
                        valueObj=Long.parseLong((String)valueObj);
                    } else if(typeclass.equals("java.lang.Float")) {
                        valueObj=Float.parseFloat((String)valueObj);
                    } else if(typeclass.equals("java.lang.Double")) {
                        valueObj=Double.parseDouble((String)valueObj);
                    } else if(typeclass.equals("java.lang.Boolean")) {
                        valueObj=Boolean.parseBoolean((String)valueObj);
                    } else if(typeclass.equals("java.lang.Byte")) {
                        valueObj=Byte.parseByte((String)valueObj);
                    } else if(typeclass.equals("java.lang.Short")) {
                        valueObj=Short.parseShort((String)valueObj);
                    } else if(typeclass.equals("java.lang.Character")) {
                        valueObj=((String)valueObj).charAt(0);
                    }
                }
            }
            pageContext.setAttribute(variable, valueObj, PageContext.PAGE_SCOPE);
        } catch (ApplicationException ex) {
            throw new JspException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new JspException(ex);
        }
        return super.doStartTag();
    }

    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getTypeclass() {
        return typeclass;
    }

    public void setTypeclass(String typeclass) {
        this.typeclass = typeclass;
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
