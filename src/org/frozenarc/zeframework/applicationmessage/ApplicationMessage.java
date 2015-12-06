package org.frozenarc.zeframework.applicationmessage;

/**
 * Represents application message.
 * @author Manan
 */
public class ApplicationMessage {

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(int messageCode) {
        this.messageCode = messageCode;
    }

    public ApplicationMessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(ApplicationMessageType messageType) {
        this.messageType = messageType;
    }

    protected int messageCode;
    protected String message;
    protected ApplicationMessageType messageType;
    
    /**
     * Call this constructor to create applicattion message.
     * @param messageCode
     * @param message
     * @param messageType
     */
    public ApplicationMessage(int messageCode, String message, ApplicationMessageType messageType) {
        this.messageCode=messageCode;
        this.message=message;
        this.messageType=messageType;
    }
    
}
