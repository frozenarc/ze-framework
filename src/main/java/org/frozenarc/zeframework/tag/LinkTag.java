package org.frozenarc.zeframework.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import org.frozenarc.zeframework.applicationexception.ApplicationException;
import org.frozenarc.zeframework.constants.FWConstants;
import org.frozenarc.zeframework.util.ViewValueUtil;

/**
 * Represents 'link' tag.
 * @author Manan
 */
public class LinkTag extends BasicTag {

    protected String view;
    protected String charset;
    protected String coords;
    protected String name;
    protected String rel;
    protected String rev;
    protected String shape;
    protected String target;
    protected String accesskey;
    protected String tabindex;
    protected String onblur;
    protected String querystring;
    protected String querystringvaluefrom;

    @Override
    public int doEndTag() throws JspException {
        try {
            StringBuilder builder = new StringBuilder();
            builder.append("</a>");
            pageContext.getOut().print(builder.toString());
        } catch (Exception ex) {
            throw new JspException(ex);
        }
        return super.doEndTag();
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            if(querystringvaluefrom!=null) {
                querystringvaluefrom=(String)ViewValueUtil.handleValueFrom(pageContext, querystringvaluefrom);
            }
            StringBuilder builder = new StringBuilder();
            builder.append("<a");
            addAtributes(builder);
            builder.append(">");
            pageContext.getOut().print(builder.toString());
        } catch (ApplicationException ex) {
            throw new JspException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new JspException(ex);
        }
        return Tag.EVAL_BODY_INCLUDE;
        //return super.doStartTag();
    }
    
    @Override
    public void addAtributes(StringBuilder builder) throws ApplicationException {
        super.addAtributes(builder);
        if(view!=null) {
            if(querystringvaluefrom!=null) {
                builder.append(" href=\""+view+FWConstants.VIEW_SUFFIX+"?"+querystringvaluefrom+"\"");
            } else {
                builder.append(" href=\""+view+FWConstants.VIEW_SUFFIX+(querystring==null?"":("?"+querystring))+"\"");
            }
        }
        if(charset!=null) {
            builder.append(" charset=\""+charset+"\"");
        }
        
        if(coords!=null) {
            builder.append(" coords=\""+coords+"\"");
        }
        
        if(name!=null) {
            builder.append(" name=\""+name+"\"");
        }
        
        if(rel!=null) {
            builder.append(" rel=\""+rel+"\"");
        }
        
        if(rev!=null) {
            builder.append(" rev=\""+rev+"\"");
        }
        
        if(shape!=null) {
            builder.append(" shape=\""+shape+"\"");
        }
        
        if(target!=null) {
            builder.append(" target=\""+target+"\"");
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
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getCoords() {
        return coords;
    }

    public void setCoords(String coords) {
        this.coords = coords;
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

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getRev() {
        return rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getTabindex() {
        return tabindex;
    }

    public void setTabindex(String tabindex) {
        this.tabindex = tabindex;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
    
    public String getQuerystring() {
        return querystring;
    }

    public void setQuerystring(String querystring) {
        this.querystring = querystring;
    }
    
    public String getQuerystringvaluefrom() {
        return querystringvaluefrom;
    }

    public void setQuerystringvaluefrom(String querystringvaluefrom) {
        this.querystringvaluefrom = querystringvaluefrom;
    }
}
