package org.frozenarc.zeframework.datatypeconvert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The class represents data type conversion errors.
 * @author Manan
 */
public class ConversionErrors {
    
    protected Map<String, ConversionError> conversionErrorMap;
    protected List<ConversionError> conversionErrorList;
    
    public ConversionErrors(List<ConversionError> conversionErrorList) {
        this.conversionErrorList=conversionErrorList;
        conversionErrorMap=new HashMap<String, ConversionError>();
        for(ConversionError error : conversionErrorList) {
            conversionErrorMap.put(error.getParam(), error);
        }
    }
    
    public List<ConversionError> getConversionErrorList() {
        return conversionErrorList;
    }

    public Map<String, ConversionError> getConversionErrorMap() {
        return conversionErrorMap;
    }
}
