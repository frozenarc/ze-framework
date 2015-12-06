package org.frozenarc.zeframework.datatypeconvert;

/**
 * The class represents data type conversion error.
 * @author Manan
 */
public class ConversionError {
    
    protected String param;
    protected String value;
    protected String message;

    public ConversionError(String paramName, String errorForValue) {
        this.param = paramName;
        this.value = errorForValue;
    }
    
    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
