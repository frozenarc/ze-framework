package org.frozenarc.zeframework.actionvalidator;

/**
 * Represents validation error generated in validator.
 * @author Manan
 */
public class ActionValidationError {

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }
    
    /**
     * Call this constructor to create action validation error.
     * @param errorCode
     * @param errorMessage
     * @param severity
     */
    public ActionValidationError(int errorCode, String errorMessage, int severity) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.severity = severity;
    }
    
    protected int errorCode;
    protected String errorMessage;
    protected int severity;
}
