package org.frozenarc.zeframework.applicationexception;

/**
 * Uses to throw application exception by the framework.
 * @author Manan
 */
public class ApplicationException extends Exception {

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException() {
    }

}
