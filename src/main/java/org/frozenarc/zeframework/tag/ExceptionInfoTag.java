package org.frozenarc.zeframework.tag;

import org.frozenarc.zeframework.constants.FWConstants;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Represents 'exceptioninfo' tag.
 * @author Manan
 */
public class ExceptionInfoTag extends TagSupport {

    public Boolean getClassname() {
        return classname;
    }

    public void setClassname(Boolean classname) {
        this.classname = classname;
    }

    public Boolean getExceptionchain() {
        return exceptionchain;
    }

    public void setExceptionchain(Boolean exceptionchain) {
        this.exceptionchain = exceptionchain;
    }

    public Boolean getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(Boolean stacktrace) {
        this.stacktrace = stacktrace;
    }

    protected Boolean classname;
    protected Boolean exceptionchain;
    protected Boolean stacktrace;

    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            StringBuilder builder = new StringBuilder();
            populateExceptionInfo(builder);
            pageContext.getOut().print(builder.toString());
        } catch (IOException ex) {
            throw new JspException(ex);
        }
        return super.doStartTag();
    }

    public void populateExceptionInfo(StringBuilder builder) {
        Exception ex = (Exception) pageContext.getRequest().getAttribute(FWConstants.FW_EXCEPTION);
        if (ex != null) {
            if (classname != null && classname == true) {
                builder.append(ex.getClass().getName() + ": ");
            }
            String message = ex.getMessage() == null ? "" : ex.getMessage();
            builder.append(message);
            if (exceptionchain != null && exceptionchain == true) {
                builder.append("<br/>");
                builder.append("<br/>");
                builder.append(ex.getClass().getName() + ": " + message);
                builder.append("<br/>");
                addExceptionChain(builder, ex.getCause());
            }
            if (stacktrace != null && stacktrace == true) {
                builder.append("<br/>");
                builder.append("<br/>");
                builder.append(ex.getClass().getName() + ": " + message);
                builder.append("<br/>");
                StackTraceElement[] elements = ex.getStackTrace();
                for (StackTraceElement element : elements) {
                    builder.append("&nbsp;&nbsp;&nbsp;&nbsp;"+element.toString());
                    builder.append("<br/>");
                }
                addStackTrace(builder, ex.getCause());
            }
        }
    }

    public void addExceptionChain(StringBuilder builder, Throwable ex) {
        if (ex != null) {
            String message = ex.getMessage() == null ? "" : ex.getMessage();
            builder.append(ex.getClass().getName() + ": " + message);
            builder.append("<br/>");
            addExceptionChain(builder, ex.getCause());
        }
    }

    public void addStackTrace(StringBuilder builder, Throwable ex) {
        if (ex != null) {
            builder.append("<br/>");
            String message = ex.getMessage() == null ? "" : ex.getMessage();
            builder.append("Caused by: "+ex.getClass().getName() + ": " + message);
            builder.append("<br/>");
            StackTraceElement[] elements = ex.getStackTrace();
            for (StackTraceElement element : elements) {
                builder.append("&nbsp;&nbsp;&nbsp;&nbsp;"+element.toString());
                builder.append("<br/>");
            }
            addStackTrace(builder, ex.getCause());
        }
    }
}
