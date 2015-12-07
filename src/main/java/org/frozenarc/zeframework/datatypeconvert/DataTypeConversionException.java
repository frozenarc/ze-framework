package org.frozenarc.zeframework.datatypeconvert;

/**
 * Object of the class thrown when data type conversion error occurs.
 * @author Manan
 */
public class DataTypeConversionException extends Exception {
    
    protected String paramName;
    protected String value;
    
    public DataTypeConversionException(String paramName, String value, Throwable arg0) {
        super(arg0);
        this.paramName=paramName;
        this.value=value;
    }
    
    public String getParamName() {
        return paramName;
    }

    public String getValue() {
        return value;
    }
    
}
