package org.frozenarc.zeframework.util;

import org.frozenarc.zeframework.constants.FWConstants;
import org.frozenarc.zeframework.datatypeconvert.ConversionError;
import org.frozenarc.zeframework.datatypeconvert.ConversionErrors;
import org.frozenarc.zeframework.datatypeconvert.DataTypeConversionException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 * Populates model in action event.
 * @author Manan
 */
public class ModelsPopulator {

    public static ConversionErrors populateModels(String actionName, HttpServletRequest request) throws Exception {
        List<ConversionError> conversionErrorList = new ArrayList<ConversionError>();
        Enumeration enumeration = request.getParameterNames();
        List<String> paramList = new ArrayList<String>();
        while (enumeration.hasMoreElements()) {
            String paramName = (String) enumeration.nextElement();
            if (!paramName.contains(FWConstants.VALUE_TO_SIGN)) {
                paramList.add(paramName);
            }
        }
        for (String param : paramList) {
            String[] values = (String[]) request.getParameterMap().get(param);
            String valueTo = request.getParameter(FWConstants.VALUE_TO_SIGN + param);
            if (valueTo != null) {
                try {
                    if (valueTo.contains(FWConstants.ARRAY_VALUE)) {
                        valueTo = valueTo.substring(valueTo.indexOf(FWConstants.ARRAY_VALUE) + FWConstants.ARRAY_VALUE.length());
                        ActionValueUtil.handleValueTo(actionName, request, valueTo, param, values, true);
                    } else {
                        ActionValueUtil.handleValueTo(actionName, request, valueTo, param, values, false);
                    }
                } catch (DataTypeConversionException ex) {
                    ConversionError error = new ConversionError(ex.getParamName(), ex.getValue());
                    conversionErrorList.add(error);
                }
            }
        }        
        if (request.getContentType().contains("multipart/form-data")) {
            Object[] parts = request.getParts().toArray();
            PartData partData = new PartData();
            for (int i = 0; i < parts.length; i++) {
                Part part = (Part) parts[i];
                if (part.getHeader("content-disposition").contains("filename")) {
                    partData.addPart(part);
                }
            }
            for (String param : partData.getPartNameList()) {
                String valueTo = request.getParameter(FWConstants.VALUE_TO_SIGN + param);
                ArrayList<Part> partList = partData.getPartMap().get(param);
                if (valueTo != null) {
                    if (valueTo.contains(FWConstants.ARRAY_VALUE)) {
                        valueTo = valueTo.substring(valueTo.indexOf(FWConstants.ARRAY_VALUE) + FWConstants.ARRAY_VALUE.length());
                        ActionValueUtil.handlePartValueTo(actionName, request, valueTo, partList, true);
                    } else {                        
                        ActionValueUtil.handlePartValueTo(actionName, request, valueTo, partList, false);
                    }
                }
            }
        }
        return new ConversionErrors(conversionErrorList);
    }
}
