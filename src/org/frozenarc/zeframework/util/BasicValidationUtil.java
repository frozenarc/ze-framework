package org.frozenarc.zeframework.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import org.frozenarc.zeframework.actionvalidator.ActionValidationError;
import org.frozenarc.zeframework.constants.FWConstants;

/**
 *
 * @author manan
 */
public class BasicValidationUtil {

    public static void validateData(HttpServletRequest request) {
        List<ActionValidationError> validationErrors=new ArrayList<ActionValidationError>();
        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String paramName = (String) enumeration.nextElement();
            if (!paramName.contains(FWConstants.VALIDATIONS_SIGN)) {
                String validations = request.getParameter(paramName);
                if (validations != null) {
                    StringTokenizer tokenizer = new StringTokenizer(validations, ",");
                    while (tokenizer.hasMoreTokens()) {
                        String token = tokenizer.nextToken().trim();
                        String param = paramName.substring(paramName.indexOf(FWConstants.VALIDATIONS_SIGN) + FWConstants.VALIDATIONS_SIGN.length());
                        String valueToParam = FWConstants.VALUE_TO_SIGN + param;
                        String value = request.getParameter(valueToParam);
                        if (token.equals("notnull")) {
                            if(value==null || value.equals("")) {
                                validationErrors.add(new ActionValidationError(-100, param, 1));
                            }
                        }
                        if(token.contains("integer")) {
                            try {
                                int intValue=Integer.parseInt(value);
                                if(token.equals("pos-integer")) {
                                    if(intValue <= 0) {
                                        
                                    }
                                } else if(token.equals("zero-pos-integer")) {
                                    if(intValue < 0) {
                                        
                                    }
                                } else if(token.equals("neg-integer")) {
                                    if(intValue >= 0) {
                                        
                                    }
                                } else if(token.equals("zero-neg-integer")) {
                                    if(intValue > 0) {
                                        
                                    }
                                }
                            } catch(NumberFormatException ex) {
                                
                            }
                        }
                        if(token.equals("numeric")) {
                            try {
                                Double.parseDouble(value);
                            } catch(NumberFormatException ex) {
                                
                            }
                        }
                    }
                }
            }
        }
    }
}
