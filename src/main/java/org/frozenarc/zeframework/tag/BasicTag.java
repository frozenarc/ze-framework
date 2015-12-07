package org.frozenarc.zeframework.tag;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.TagSupport;
import org.frozenarc.zeframework.applicationexception.ApplicationException;

/**
 * Super class for input tags.
 * @author Manan
 */
public class BasicTag extends TagSupport implements DynamicAttributes {

    protected Map<String, String> dynAttr = new HashMap<String, String>();
    protected String styleclass;
    protected String dir;
    protected String lang;
    protected String title;
    protected String style;
    protected String onclick;
    protected String ondblclick;
    protected String onmousedown;
    protected String onmousemove;
    protected String onmouseout;
    protected String onmouseover;
    protected String onmouseup;
    protected String onkeydown;
    protected String onkeypress;
    protected String onkeyup;

    public void addAtributes(StringBuilder builder) throws ApplicationException {
        if (id != null) {
            builder.append(" id=\"" + id + "\"");
        }
        if (styleclass != null) {
            builder.append(" class=\"" + styleclass + "\"");
        }
        if (dir != null) {
            builder.append(" dir=\"" + dir + "\"");
        }
        if (lang != null) {
            builder.append(" lang=\"" + lang + "\"");
        }
        if (title != null) {
            builder.append(" title=\"" + title + "\"");
        }
        if (style != null) {
            builder.append(" style=\"" + style + "\"");
        }

        if (onclick != null) {
            builder.append(" onclick=\"" + onclick + "\"");
        }
        if (ondblclick != null) {
            builder.append(" ondblclick=\"" + ondblclick + "\"");
        }
        if (onkeydown != null) {
            builder.append(" onkeydown=\"" + onkeydown + "\"");
        }
        if (onkeypress != null) {
            builder.append(" onkeypress=\"" + onkeypress + "\"");
        }
        if (onkeyup != null) {
            builder.append(" onkeyup=\"" + onkeyup + "\"");
        }
        if (onmousedown != null) {
            builder.append(" onmousedown=\"" + onmousedown + "\"");
        }
        if (onmousemove != null) {
            builder.append(" onmousemove=\"" + onmousemove + "\"");
        }
        if (onmouseout != null) {
            builder.append(" onmouseout=\"" + onmouseout + "\"");
        }
        if (onmouseover != null) {
            builder.append(" onmouseover=\"" + onmouseover + "\"");
        }
        if (onmouseup != null) {
            builder.append(" onmouseup=\"" + onmouseup + "\"");
        }
        Iterator iterator = dynAttr.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            if (key != null) {
                builder.append(" " + key + "=\"" + dynAttr.get(key) + "\"");
            }
        }
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getOnclick() {
        return onclick;
    }

    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }

    public String getOndblclick() {
        return ondblclick;
    }

    public void setOndblclick(String ondblclick) {
        this.ondblclick = ondblclick;
    }

    public String getOnkeydown() {
        return onkeydown;
    }

    public void setOnkeydown(String onkeydown) {
        this.onkeydown = onkeydown;
    }

    public String getOnkeypress() {
        return onkeypress;
    }

    public void setOnkeypress(String onkeypress) {
        this.onkeypress = onkeypress;
    }

    public String getOnkeyup() {
        return onkeyup;
    }

    public void setOnkeyup(String onkeyup) {
        this.onkeyup = onkeyup;
    }

    public String getOnmousedown() {
        return onmousedown;
    }

    public void setOnmousedown(String onmousedown) {
        this.onmousedown = onmousedown;
    }

    public String getOnmousemove() {
        return onmousemove;
    }

    public void setOnmousemove(String onmousemove) {
        this.onmousemove = onmousemove;
    }

    public String getOnmouseout() {
        return onmouseout;
    }

    public void setOnmouseout(String onmouseout) {
        this.onmouseout = onmouseout;
    }

    public String getOnmouseover() {
        return onmouseover;
    }

    public void setOnmouseover(String onmouseover) {
        this.onmouseover = onmouseover;
    }

    public String getOnmouseup() {
        return onmouseup;
    }

    public void setOnmouseup(String onmouseup) {
        this.onmouseup = onmouseup;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyleclass() {
        return styleclass;
    }

    public void setStyleclass(String styleclass) {
        this.styleclass = styleclass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDynamicAttribute(String uri, String name, Object value) throws JspException {
        dynAttr.put(name, (String) value);
    }
}
