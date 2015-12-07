package org.frozenarc.zeframework.applicationexception;

/**
 * Utitlity class to check and generate application exception
 * @author Manan
 */
public class ApplicationExceptionUtil {
    
    public static void checkNullPointerException(Object checkNull, String message) throws ApplicationException {
        if(checkNull==null) {
            throwAppException(message);
        }
    }
    
    public static void throwAppException(String message) throws ApplicationException {
        throw new ApplicationException(message);
    }
    
    public static void validateNumberPattern(String pattern) throws ApplicationException {
        int index = 0;
        if(pattern.indexOf(":", index)==-1) {
            throwAppException("Number format pattern without ':'");
        }
        index = pattern.indexOf(":", index) + 1;
        if(pattern.indexOf(".", index)==-1) {
            throwAppException("Number format pattern without '.'");
        }
        String integerPart=pattern.substring(pattern.indexOf(":")+1, pattern.indexOf("."));
        index=0;
        if(integerPart.indexOf("(", index)==-1) {
            throwAppException("Number format pattern without '('");
        }
        if(integerPart.indexOf("-", index)==-1) {
            throwAppException("Number format pattern without '-'");
        }
        if(integerPart.indexOf(")", index)==-1) {
            throwAppException("Number format pattern without ')'");
        }
        index = pattern.indexOf(".", index) + 1;
        if(pattern.indexOf("(", index)==-1) {
            throwAppException("Number format pattern without '('");
        }
        if(pattern.indexOf("-", index)==-1) {
            throwAppException("Number format pattern without '-'");
        }
        if(pattern.indexOf(")", index)==-1) {
            throwAppException("Number format pattern without ')'");
        }
    }
    
    /*public static void validateStartEndSign(String value, String start, String end) throws ApplicationException {
        if(!value.startsWith(start)) {
            throwAppException("'"+start+"' is missing in "+value);
        }
        if(!value.endsWith(end)) {
            throwAppException("'"+end+"' is missing in "+value);
        }
    }*/
    
    public static void validateStartSign(String value, String start) throws ApplicationException {
        if(!value.startsWith(start)) {
            throwAppException("'"+start+"' is missing in "+value);
        }
    }
}