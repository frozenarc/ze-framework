package org.frozenarc.zeframework.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.IterationTag;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import org.frozenarc.zeframework.applicationexception.ApplicationException;
import org.frozenarc.zeframework.util.ValueUtil;
import org.frozenarc.zeframework.util.ViewValueUtil;

/**
 * Represents 'forloop' tag.
 * @author Manan
 */
public class ForLoopTag extends TagSupport {

    public String getDecrementbyvalue() {
        return decrementbyvalue;
    }

    public void setDecrementbyvalue(String decreamentbyvalue) {
        this.decrementbyvalue = decreamentbyvalue;
    }

    public String getDecrementbyvaluefrom() {
        return decrementbyvaluefrom;
    }

    public void setDecrementbyvaluefrom(String decreamentbyvaluefrom) {
        this.decrementbyvaluefrom = decreamentbyvaluefrom;
    }

    public String getGraterthanvalue() {
        return graterthanvalue;
    }

    public void setGraterthanvalue(String graterhanvalue) {
        this.graterthanvalue = graterhanvalue;
    }

    public String getGraterthanequaltovalue() {
        return graterthanequaltovalue;
    }

    public void setGraterthanequaltovalue(String graterthanequaltovalue) {
        this.graterthanequaltovalue = graterthanequaltovalue;
    }

    public String getGraterthanequaltovaluefrom() {
        return graterthanequaltovaluefrom;
    }

    public void setGraterthanequaltovaluefrom(String graterthanequaltovaluefrom) {
        this.graterthanequaltovaluefrom = graterthanequaltovaluefrom;
    }

    public String getGraterthanvaluefrom() {
        return graterthanvaluefrom;
    }

    public void setGraterthanvaluefrom(String graterthanvaluefrom) {
        this.graterthanvaluefrom = graterthanvaluefrom;
    }

    public String getIncrementbyvalue() {
        return incrementbyvalue;
    }

    public void setIncrementbyvalue(String increamentbyvalue) {
        this.incrementbyvalue = increamentbyvalue;
    }

    public String getIncrementbyvaluefrom() {
        return incrementbyvaluefrom;
    }

    public void setIncrementbyvaluefrom(String increamentbyvaluefrom) {
        this.incrementbyvaluefrom = increamentbyvaluefrom;
    }

    public String getLessthanequaltovalue() {
        return lessthanequaltovalue;
    }

    public void setLessthanequaltovalue(String lessthanequaltovalue) {
        this.lessthanequaltovalue = lessthanequaltovalue;
    }

    public String getLessthanequaltovaluefrom() {
        return lessthanequaltovaluefrom;
    }

    public void setLessthanequaltovaluefrom(String lessthanequaltovaluefrom) {
        this.lessthanequaltovaluefrom = lessthanequaltovaluefrom;
    }

    public String getLessthanvalue() {
        return lessthanvalue;
    }

    public void setLessthanvalue(String lessthanvalue) {
        this.lessthanvalue = lessthanvalue;
    }

    public String getLessthanvaluefrom() {
        return lessthanvaluefrom;
    }

    public void setLessthanvaluefrom(String lessthanvaluefrom) {
        this.lessthanvaluefrom = lessthanvaluefrom;
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

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }
    protected String variable;
    protected String valuefrom;
    protected String value;
    
    protected String lessthanvalue;
    protected String lessthanvaluefrom;
    protected String lessthanequaltovalue;
    protected String lessthanequaltovaluefrom;
    protected String graterthanvalue;
    protected String graterthanvaluefrom;
    protected String graterthanequaltovalue;
    protected String graterthanequaltovaluefrom;
    
    protected String incrementbyvalue;
    protected String incrementbyvaluefrom;
    protected String decrementbyvalue;
    protected String decrementbyvaluefrom;
    
    protected Integer longVariable;
    protected Integer conditionalVariable;
    protected Integer icrdcrVaribale;
    

    @Override
    public int doStartTag() throws JspException {
        try {
            if (valuefrom != null) {
                longVariable = (Integer)ViewValueUtil.handleValueFrom(pageContext, valuefrom);
            } else if (value != null) {
                Object valueObj = ValueUtil.handleValue(pageContext, value);
                longVariable=getWrapperIntegerForValue(valueObj);
            }
            pageContext.setAttribute(variable, longVariable, PageContext.PAGE_SCOPE);

            if (getNotNullConditionalVariableValueFrom() != null) {
                conditionalVariable = (Integer)ViewValueUtil.handleValueFrom(pageContext, getNotNullConditionalVariableValueFrom());
            } else if (getNotNullConditionalVariableValue() != null) {
                Object valueObj = ValueUtil.handleValue(pageContext, getNotNullConditionalVariableValue());
                conditionalVariable=getWrapperIntegerForValue(valueObj);
            }
            
            if (getNotNullIcrDcrVariableValueFrom() != null) {
                icrdcrVaribale = (Integer)ViewValueUtil.handleValueFrom(pageContext, getNotNullIcrDcrVariableValueFrom());
            } else if (getNotNullIcrDcrVariableValue() != null) {
                Object valueObj = ValueUtil.handleValue(pageContext, getNotNullIcrDcrVariableValue());
                icrdcrVaribale=getWrapperIntegerForValue(valueObj);
            }
        } catch (ApplicationException ex) {
            throw new JspException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new JspException(ex);
        }
        if (lessthanvalue != null || lessthanvaluefrom != null) {
            Integer var=(Integer)pageContext.getAttribute(variable);
            if(var < conditionalVariable) {
                return Tag.EVAL_BODY_INCLUDE;
            } else {
                return Tag.SKIP_BODY;
            }
        } else if (lessthanequaltovalue != null || lessthanequaltovaluefrom != null) {
            Integer var=(Integer)pageContext.getAttribute(variable);
            if(var <= conditionalVariable) {
                return Tag.EVAL_BODY_INCLUDE;
            } else {
                return Tag.SKIP_BODY;
            }
        } else if (graterthanvalue != null || graterthanvaluefrom!=null) {
            Integer var=(Integer)pageContext.getAttribute(variable);
            if(var > conditionalVariable) {
                return Tag.EVAL_BODY_INCLUDE;
            } else {
                return Tag.SKIP_BODY;
            }
        } else if (graterthanequaltovalue != null || graterthanequaltovaluefrom!=null) {
            Integer var=(Integer)pageContext.getAttribute(variable);
            if(var >= conditionalVariable) {
                return Tag.EVAL_BODY_INCLUDE;
            } else {
                return Tag.SKIP_BODY;
            }
        }
        
        return super.doStartTag();
    }

    protected String getNotNullConditionalVariableValueFrom() {
        if (lessthanvaluefrom != null) {
            return lessthanvaluefrom;
        } else if (lessthanequaltovaluefrom != null) {
            return lessthanequaltovaluefrom;
        } else if (graterthanvaluefrom != null) {
            return graterthanvaluefrom;
        } else {
            return graterthanequaltovaluefrom;
        }
    }

    protected String getNotNullConditionalVariableValue() {
        if (lessthanvalue != null) {
            return lessthanvalue;
        } else if (lessthanequaltovalue != null) {
            return lessthanequaltovalue;
        } else if (graterthanvalue != null) {
            return graterthanvalue;
        } else {
            return graterthanequaltovalue;
        }
    }
    
    protected String getNotNullIcrDcrVariableValueFrom() {
        if (incrementbyvaluefrom != null) {
            return incrementbyvaluefrom;
        } else {
            return decrementbyvaluefrom;
        }
    }

    protected String getNotNullIcrDcrVariableValue() {
        if (incrementbyvalue != null) {
            return incrementbyvalue;
        } else {
            return decrementbyvalue;
        }
    }

    protected Integer getWrapperIntegerForValue(Object valueObj) {
        if(valueObj instanceof Integer) {
            return (Integer)valueObj;
        } else if (valueObj instanceof String) {
            return Integer.parseInt((String)valueObj);
        } else {
            return null;
        }
    }

    @Override
    public int doAfterBody() throws JspException {
        if(incrementbyvalue!=null || incrementbyvaluefrom!=null) {
            Integer var=(Integer)pageContext.getAttribute(variable);
            var=var+icrdcrVaribale;
            pageContext.setAttribute(variable, var);
        } else if (decrementbyvalue!=null || decrementbyvaluefrom!=null) {
            Integer var=(Integer)pageContext.getAttribute(variable);
            var=var-icrdcrVaribale;
            pageContext.setAttribute(variable, var);
        }
        if (lessthanvalue != null || lessthanvaluefrom != null) {
            Integer var=(Integer)pageContext.getAttribute(variable);
            if(var < conditionalVariable) {
                return IterationTag.EVAL_BODY_AGAIN;
            } else {
                return Tag.SKIP_BODY;
            }
        } else if (lessthanequaltovalue != null || lessthanequaltovaluefrom != null) {
            Integer var=(Integer)pageContext.getAttribute(variable);
            if(var <= conditionalVariable) {
                return IterationTag.EVAL_BODY_AGAIN;
            } else {
                return Tag.SKIP_BODY;
            }
        } else if (graterthanvalue != null || graterthanvaluefrom!=null) {
            Integer var=(Integer)pageContext.getAttribute(variable);
            if(var > conditionalVariable) {
                return IterationTag.EVAL_BODY_AGAIN;
            } else {
                return Tag.SKIP_BODY;
            }
        } else if (graterthanequaltovalue != null || graterthanequaltovaluefrom!=null) {
            Integer var=(Integer)pageContext.getAttribute(variable);
            if(var >= conditionalVariable) {
                return IterationTag.EVAL_BODY_AGAIN;
            } else {
                return Tag.SKIP_BODY;
            }
        }
        return super.doAfterBody();
    }

    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }
}
