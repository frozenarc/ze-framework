package org.frozenarc.zeframework.fwconfig;

import org.frozenarc.zeframework.constants.FWConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Utility class to get config.
 * @author Manan
 */
public class FWConfigUtil {

    protected static FWConfig getFWConfig() {
        return (FWConfig) FWConfigContext.getValue(FWConstants.CONFIG);
    }

    public static FWAction getAction(String actionName) {
        FWConfig config = getFWConfig();
        FWAction action = config.getAction(actionName);
        return action;
    }

    public static FWView getView(String viewName) {
        FWConfig config = getFWConfig();
        FWView view = config.getView(viewName);
        return view;
    }

    public static List<FWModel> getActionModels(String actionName) {
        FWConfig config = getFWConfig();
        FWAction action = config.getAction(actionName);
        return action.getModels();
    }

    public static Map<String, FWModel> getActionModelMap(String actionName) {
        FWConfig config = getFWConfig();
        FWAction action = config.getAction(actionName);
        return action.getModelMap();
    }

    public static List<FWModel> getViewModels(String viewName) {
        FWConfig config = getFWConfig();
        FWView view = config.getView(viewName);
        return view.getModels();
    }

    public static Map<String, FWModel> getViewModelMap(String viewName) {
        FWConfig config = getFWConfig();
        FWView view = config.getView(viewName);
        return view.getModelMap();
    }

    public static String getAppInitializer() {
        FWConfig config = getFWConfig();
        return config.getAppInitializer();
    }
    
    public static String getProcessingTube() {
        FWConfig config = getFWConfig();
        return config.getProcessingTube();
    }

    public static String getAppDestroyer() {
        FWConfig config = getFWConfig();
        return config.getAppDestroyer();
    }

    public static boolean isSendRedirect(String actionName, String viewName) {
        FWConfig config = getFWConfig();
        Boolean sendRedirect=config.getActionViewSendRedirect(actionName+":"+viewName);
        return sendRedirect==null?false:sendRedirect;
    }

    public static FWException getFWException(Exception ex) throws ClassNotFoundException {
        FWConfig config = getFWConfig();
        ArrayList<FWException> exceptionList = config.getFWExceptionList();
        if (exceptionList != null) {
            for (FWException exception : exceptionList) {
                if (Class.forName(exception.getType()).isInstance(ex)) {
                    return exception;
                }
            }
        }
        return null;
    }

    public static FWException getActionFWException(FWAction action, Exception ex) throws ClassNotFoundException {
        List<FWException> exceptionList = action.getExceptionList();
        if (exceptionList != null) {
            for (FWException exception : exceptionList) {
                if (Class.forName(exception.getType()).isInstance(ex)) {
                    return exception;
                }
            }
        }
        return null;
    }

    public static FWException getViewFWException(FWView view, Exception ex) throws ClassNotFoundException {
        List<FWException> exceptionList = view.getExceptionList();
        if (exceptionList != null) {
            for (FWException exception : exceptionList) {
                if (Class.forName(exception.getType()).isInstance(ex)) {
                    return exception;
                }
            }
        }
        return null;
    }
}
