package org.frozenarc.zeframework.basicvalidation;

/**
 *
 * @author Manan
 */
public class BasicValidationMessage {
    
    protected String param;
    protected String value;
    protected String message;

    public BasicValidationMessage(String paramName, String validationForValue) {
        this.param = paramName;
        this.value = validationForValue;
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
