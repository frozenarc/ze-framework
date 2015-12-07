package org.frozenarc.zeframework.servletcontext;

import org.frozenarc.zeframework.fwconfig.FWConfigContext;
import org.frozenarc.zeframework.constants.FWConstants;
import org.frozenarc.zeframework.fwconfig.FWConfig;
import org.frozenarc.zeframework.config.ActionType;
import org.frozenarc.zeframework.config.ExceptionType;
import org.frozenarc.zeframework.config.ModelNameType;
import org.frozenarc.zeframework.config.ModelType;
import org.frozenarc.zeframework.config.ZeFWConfigType;
import org.frozenarc.zeframework.config.ViewType;
import org.frozenarc.zeframework.fwconfig.FWAction;
import org.frozenarc.zeframework.fwconfig.FWException;
import org.frozenarc.zeframework.fwconfig.FWModel;
import org.frozenarc.zeframework.fwconfig.FWActionValidator;
import org.frozenarc.zeframework.fwconfig.FWView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.ServletContext;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.frozenarc.zeframework.config.ActionValidatorType;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.frozenarc.zeframework.config.ActionViewRedirectType;
import org.frozenarc.zeframework.config.ViewValidatorType;
import org.frozenarc.zeframework.fwconfig.FWViewValidator;

/**
 * Loads configuration from config files.
 * @author Manan
 */
public class ConfigLoader {

    public static FWConfig loadConfig(ServletContext context) throws SAXException, FileNotFoundException, IOException, JAXBException {
        String xsdPath = context.getInitParameter(FWConstants.CONFIG_XSD);
        String xmlPaths = context.getInitParameter(FWConstants.CONFIG_XML);
        StringTokenizer tokenizer = new StringTokenizer(xmlPaths, ",");
        FWConfig fwConfig = new FWConfig();
        while (tokenizer.hasMoreTokens()) {
            String xmlPath = tokenizer.nextToken().trim();
            validateConfig(context, xsdPath, xmlPath);
            ZeFWConfigType configType = loadConfig(context, xmlPath);
            loadFWConfig(configType, fwConfig);
        }
        FWConfigContext.putValue(FWConstants.CONFIG, fwConfig);
        return fwConfig;
    }

    protected static void validateConfig(ServletContext context, String xsdPath, String xmlPath) throws SAXException, FileNotFoundException, IOException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new SAXSource(new InputSource(context.getResourceAsStream(xsdPath))));
        Validator validator = schema.newValidator();
        validator.validate(new SAXSource(new InputSource(context.getResourceAsStream(xmlPath))));
    }

    protected static ZeFWConfigType loadConfig(ServletContext context, String xmlPath) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance("org.mvpanchal.zeframework.config");
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        JAXBElement ele = (JAXBElement) unmarshaller.unmarshal(context.getResourceAsStream(xmlPath));
        return (ZeFWConfigType) ele.getValue();
    }

    protected static void loadLifeCycle(ZeFWConfigType configType, FWConfig config) {
        if (configType.getApplicationLifeCycle() != null) {
            if (configType.getApplicationLifeCycle().getInitializer() != null) {
                String type = configType.getApplicationLifeCycle().getInitializer().getType();
                config.setAppInitializer(type);
            }
            if (configType.getApplicationLifeCycle().getDestroyer() != null) {
                String type = configType.getApplicationLifeCycle().getDestroyer().getType();
                config.setAppDestroyer(type);
            }
            if (configType.getApplicationLifeCycle().getProcessingTube() != null) {
                String type = configType.getApplicationLifeCycle().getProcessingTube().getType();
                config.setProcessingTube(type);
            }
        }
    }

    protected static void loadExceptions(ZeFWConfigType configType, FWConfig config) {
        if (configType.getExceptions() != null) {
            List<ExceptionType> exceptionsType = configType.getExceptions().getException();
            for (ExceptionType exceptionType : exceptionsType) {
                FWException exception = new FWException();
                exception.setType(exceptionType.getType());
                exception.setPage(exceptionType.getPage());
                exception.setHandler(exceptionType.getHandler());
                config.putException(exception.getType(), exception);
                if (config.getFWExceptionList().contains(exception)) {
                    config.getFWExceptionList().remove(exception);
                }
                config.getFWExceptionList().add(exception);
            }
        }
    }

    protected static HashMap<String, ModelType> loadModels(ZeFWConfigType configType, FWConfig config) {
        HashMap<String, ModelType> modelTypeMap = new HashMap<String, ModelType>();
        if (configType.getModels() != null) {
            List<ModelType> modelsType = configType.getModels().getModel();
            for (ModelType modelType : modelsType) {
                modelTypeMap.put(modelType.getName(), modelType);
            }
        }
        return modelTypeMap;
    }

    protected static void loadActions(ZeFWConfigType configType, FWConfig config, HashMap<String, ModelType> modelTypeMap) {
        if (configType.getActions() != null) {
            List<ActionType> actionsType = configType.getActions().getAction();
            for (ActionType actionType : actionsType) {
                FWAction action = new FWAction();
                action.setName(actionType.getName());
                action.setHandler(actionType.getHandler());
                if (actionType.getModelNames() != null) {
                    List<FWModel> modelList = new ArrayList<FWModel>();
                    Map<String, FWModel> modelMap = new HashMap<String, FWModel>();
                    List<ModelNameType> modelNamesType = actionType.getModelNames().getModelName();
                    for (ModelNameType modelNameType : modelNamesType) {
                        ModelType modelType = modelTypeMap.get(modelNameType.getName());
                        if (modelType != null) {
                            FWModel model = new FWModel();
                            model.setName(modelType.getName());
                            model.setType(modelType.getType());
                            model.setScope(modelType.getScope());
                            if (modelList.contains(model)) {
                                modelList.remove(model);
                            }
                            modelList.add(model);
                            modelMap.put(model.getName(), model);
                        } else {
                            FWModel model = new FWModel();
                            model.setName(modelNameType.getName());
                            model.setType(FWConstants.UNDEFINED_MODEL);
                            modelList.add(model);
                            modelMap.put(model.getName(), model);
                        }
                    }
                    action.setModels(modelList);
                    action.setModelMap(modelMap);
                }
                if (actionType.getExceptions() != null) {
                    List<ExceptionType> exceptionTypes = actionType.getExceptions().getException();
                    HashMap<String, FWException> exceptionMap = new HashMap<String, FWException>();
                    ArrayList<FWException> exceptionList = new ArrayList<FWException>();
                    for (ExceptionType exceptionType : exceptionTypes) {
                        FWException exception = new FWException();
                        exception.setType(exceptionType.getType());
                        exception.setPage(exceptionType.getPage());
                        exception.setHandler(exceptionType.getHandler());
                        exceptionMap.put(exception.getType(), exception);
                        if (exceptionList.contains(exception)) {
                            exceptionList.remove(exception);
                        }
                        exceptionList.add(exception);
                    }
                    action.setExceptionMap(exceptionMap);
                    action.setExceptionList(exceptionList);
                }
                if (actionType.getActionValidators() != null) {
                    List<ActionValidatorType> validatorTypes = actionType.getActionValidators().getActionValidator();
                    ArrayList<FWActionValidator> fWValidators = new ArrayList<FWActionValidator>();
                    for (ActionValidatorType validatorType : validatorTypes) {
                        FWActionValidator validator = new FWActionValidator();
                        validator.setType(validatorType.getType());
                        fWValidators.add(validator);
                    }
                    action.setValidators(fWValidators);
                }
                config.putAction(action.getName(), action);
                if (config.getFWActionList().contains(action)) {
                    config.getFWActionList().remove(action);
                }
                config.getFWActionList().add(action);
            }
        }
    }

    protected static void loadViews(ZeFWConfigType configType, FWConfig config, HashMap<String, ModelType> modelTypeMap) {
        if (configType.getViews() != null) {
            List<ViewType> viewsType = configType.getViews().getView();
            for (ViewType viewType : viewsType) {
                FWView view = new FWView();
                view.setName(viewType.getName());
                view.setPage(viewType.getPage());
                view.setLoader(viewType.getLoader());
                if (viewType.getModelNames() != null) {
                    List<FWModel> modelList = new ArrayList<FWModel>();
                    Map<String, FWModel> modelMap = new HashMap<String, FWModel>();
                    List<ModelNameType> modelNamesType = viewType.getModelNames().getModelName();
                    for (ModelNameType modelNameType : modelNamesType) {
                        ModelType modelType = modelTypeMap.get(modelNameType.getName());
                        if (modelType != null) {
                            FWModel model = new FWModel();
                            model.setName(modelType.getName());
                            model.setType(modelType.getType());
                            model.setScope(modelType.getScope());
                            if (modelList.contains(model)) {
                                modelList.remove(model);
                            }
                            modelList.add(model);
                            modelMap.put(model.getName(), model);
                        } else {
                            FWModel model = new FWModel();
                            model.setName(modelNameType.getName());
                            model.setType(FWConstants.UNDEFINED_MODEL);
                            modelList.add(model);
                            modelMap.put(model.getName(), model);
                        }
                    }
                    view.setModels(modelList);
                    view.setModelMap(modelMap);
                }
                if (viewType.getExceptions() != null) {
                    List<ExceptionType> exceptionTypes = viewType.getExceptions().getException();
                    HashMap<String, FWException> exceptionMap = new HashMap<String, FWException>();
                    ArrayList<FWException> exceptionList = new ArrayList<FWException>();
                    for (ExceptionType exceptionType : exceptionTypes) {
                        FWException exception = new FWException();
                        exception.setType(exceptionType.getType());
                        exception.setPage(exceptionType.getPage());
                        exception.setHandler(exceptionType.getHandler());
                        exceptionMap.put(exception.getType(), exception);
                        if (exceptionList.contains(exception)) {
                            exceptionList.remove(exception);
                        }
                        exceptionList.add(exception);
                    }
                    view.setExceptionMap(exceptionMap);
                    view.setExceptionList(exceptionList);
                }
                if (viewType.getViewValidators() != null) {
                    List<ViewValidatorType> validatorTypes = viewType.getViewValidators().getViewValidator();
                    ArrayList<FWViewValidator> fWValidators = new ArrayList<FWViewValidator>();
                    for (ViewValidatorType validatorType : validatorTypes) {
                        FWViewValidator validator = new FWViewValidator();
                        validator.setType(validatorType.getType());
                        fWValidators.add(validator);
                    }
                    view.setValidators(fWValidators);
                }
                config.putView(view.getName(), view);
                if (config.getFWViewList().contains(view)) {
                    config.getFWViewList().remove(view);
                }
                config.getFWViewList().add(view);
            }
        }
        FWView fileDownloadView = new FWView();
        fileDownloadView.setName(FWConstants.FILE_DOWNLOAD);
        fileDownloadView.setLoader(FWConstants.FILE_DOWNLOADER);
        if (!config.getFWViewList().contains(fileDownloadView)) {
            config.putView(fileDownloadView.getName(), fileDownloadView);
            config.getFWViewList().add(fileDownloadView);
        }
    }

    protected static void loadActionsViews(ZeFWConfigType configType, FWConfig config) {
        if (configType.getActionViewRedirects() != null) {
            List<ActionViewRedirectType> actionViewRedirectsType = configType.getActionViewRedirects().getActionViewRedirect();
            for (ActionViewRedirectType actionViewRedirectType : actionViewRedirectsType) {
                config.putActionViewSendRedirect(actionViewRedirectType.getActionname() + ":" + actionViewRedirectType.getViewname(), actionViewRedirectType.isSendredirect());
            }
        }
    }

    protected static void loadAppProps(ZeFWConfigType configType, FWConfig config) {
        if (configType.getApplicationProperties() != null) {
            config.setAppPropsFilePath(configType.getApplicationProperties().getFilepath());
        }
    }

    protected static void loadFWConfig(ZeFWConfigType configType, FWConfig config) {
        loadLifeCycle(configType, config);
        loadExceptions(configType, config);
        HashMap<String, ModelType> modelTypeMap = loadModels(configType, config);
        loadActions(configType, config, modelTypeMap);
        loadViews(configType, config, modelTypeMap);
        loadActionsViews(configType, config);
        loadAppProps(configType, config);
    }
}
