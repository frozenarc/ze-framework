package org.frozenarc.zeframework.util;

import org.frozenarc.zeframework.constants.FWConstants;
import javax.servlet.jsp.PageContext;
import org.frozenarc.zeframework.applicationexception.ApplicationException;
import org.frozenarc.zeframework.applicationexception.ApplicationExceptionUtil;

/**
 * Utility class for value.
 * @author Manan
 */
public class ValueUtil {

    public static Object handleValue(PageContext pageContext, String value) throws ApplicationException {
        if (value.indexOf(FWConstants.VARIABLE_VALUE_START) == 0) {
            ApplicationExceptionUtil.validateStartSign(value, FWConstants.VARIABLE_VALUE_START);
            value = value.substring(value.indexOf(FWConstants.VARIABLE_VALUE_START) + FWConstants.VARIABLE_VALUE_START.length());
            ApplicationExceptionUtil.checkNullPointerException(value, "Variable " + value + " does not exist in scope");
            return pageContext.getAttribute(value);
        }
        return value;
    }
}
