package org.frozenarc.zeframework.util;

import org.frozenarc.zeframework.applicationexception.ApplicationException;
import org.frozenarc.zeframework.constants.FWConstants;

/**
 * Used to evaluate format.
 * @author Manan
 */
public class FormatEvaluator {

    public static FormatInfo getFormatInfo(String valueFrom) throws ApplicationException {
        String format = null;
        String timeZone = null;
        int index = valueFrom.indexOf(FWConstants.VALUE_END, 0)+1;
        if (valueFrom.indexOf(FWConstants.FORMAT_VALUE_START, index) != -1) {
            format = valueFrom.substring(valueFrom.indexOf(FWConstants.FORMAT_VALUE_START, index)+FWConstants.FORMAT_VALUE_START.length(), valueFrom.indexOf(FWConstants.VALUE_END, index));
            index = valueFrom.indexOf(FWConstants.VALUE_END, index)+1;
            if (valueFrom.indexOf(FWConstants.TIMEZONE_VALUE_START, index) != -1) {
                timeZone = valueFrom.substring(valueFrom.indexOf(FWConstants.TIMEZONE_VALUE_START, index)+FWConstants.TIMEZONE_VALUE_START.length(), valueFrom.indexOf(FWConstants.VALUE_END, index));
            }
        }
        FormatInfo info = new FormatInfo();
        info.setFormatPattern(format);
        info.setTimeZoneName(timeZone);
        return info;
    }
}
