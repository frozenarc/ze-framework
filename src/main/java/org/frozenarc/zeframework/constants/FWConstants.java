package org.frozenarc.zeframework.constants;

/**
 * The class hold all framework's constants.
 * @author Manan
 */
public interface FWConstants {
    
    String CONFIG="_CONFIG";
    
    String CONFIG_XSD="config-xsd";
    String CONFIG_XML="config-xml";
    
    String PROCESSING_TUBE="_PROCESSING_TUBE";
    
    String VALUE_TO_SIGN="_#*#valueto#*#";
    String MODEL_INFO_SIGN="_#*#modelinfo#*#";
    String VALIDATIONS_SIGN="_#*#validations#*#";
    
    String ACTION_SUFFIX=".action";
    String VIEW_SUFFIX=".view";
    
    String REQUEST_SCOPE="request";
    String SESSION_SCOPE="session";
    String APPLICATION_SCOPE="application";
    
    String VIEW_NAME="_VIEW_NAME";
    String ACTION_VIEW_CONNECTOR="_ACTION_VIEW_CONNECTOR";
    String VALIDATION_ERRORS="_VALIDATION_ERRORS";
    String APPLICATION_MESSAGES="_APPLICATION_MESSAGES";
    String CONVERSION_ERRORS="_CONVERSION_ERRORS";
    
    String ARRAY_VALUE="array:";
    String FILE_VALUE="file:";
    
    String VIEW_NAME_VALUE="_VIEW_NAME_VALUE";
    
    String RADIO_GROUP_TAG_NAME="_RADIO_GROUP_TAG_NAME";
    String CHECK_GROUP_TAG_NAME="_CHECK_GROUP_TAG_NAME";
    
    String FW_EXCEPTION = "_FW_EXCEPTION";
    
    String CONVERSION_VALUE_REPLACE="c{value}";
    
    String FILEDATA_ID="_filedata_id_";
    String FILE_ID="file_id";
    String FILE_DOWNLOAD="_filedownload";
    String FILE_DOWNLOADER="org.mvpanchal.zeframework.viewloader.FileDownloader";
    
    
    String MODEL_VALUE_START="m{";
    String FORMAT_VALUE_START="f{";
    String TIMEZONE_VALUE_START="t{";
    String VARIABLE_VALUE_START="$";
    String VALUE_END="}";
    
    String UNDEFINED_MODEL="UNDEFINED_MODEL";
    
}
