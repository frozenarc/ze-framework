package org.frozenarc.zeframework.util;

import org.frozenarc.zeframework.constants.FWConstants;
import org.frozenarc.zeframework.fwconfig.FWModel;
import org.frozenarc.zeframework.model.Model;
import javax.servlet.http.HttpServletRequest;

/**
 * Utility class for model.
 * @author Manan
 */
public class ModelUtil {

    public static boolean isSessionScope(FWModel model) {
        return model.getScope() == null || model.getScope().equals(FWConstants.SESSION_SCOPE);
    }

    public static boolean isRequestScope(FWModel model) {
        return model.getScope() != null && model.getScope().equals(FWConstants.REQUEST_SCOPE);
    }

    public static boolean isApplicationScope(FWModel model) {
        return model.getScope() != null && model.getScope().equals(FWConstants.APPLICATION_SCOPE);
    }

    public static boolean isExistInScope(HttpServletRequest request, FWModel model, int scope) {
        if (scope==0) {
            return request.getSession().getAttribute(model.getName()) != null;
        } else if (scope==1) {
            return request.getAttribute(model.getName()) != null;
        } else {
            return request.getSession().getServletContext().getAttribute(model.getName()) != null;
        }
    }
    
    public static int getModelScope(FWModel model) {
        if(isSessionScope(model)) {
            return 0;
        } else if(isRequestScope(model)) {
            return 1;
        } else {
            return 2;
        }
    }
    
    public static Model getModel(HttpServletRequest request, FWModel model, boolean createIfNotExist) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        int scope=getModelScope(model);
        if (createIfNotExist && !isExistInScope(request, model, scope)) {
            putModelInScope(request, model, scope);
        }
        return getModel(request, model, scope);
    }

    public static Model getModel(HttpServletRequest request, FWModel model, int scope) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
       if (scope==0) {
            return (Model) request.getSession().getAttribute(model.getName());
        } else if (scope==1) {
            return (Model) request.getAttribute(model.getName());
        } else {
            return (Model) request.getSession().getServletContext().getAttribute(model.getName());
        }
    }

    public static void putModelInScope(HttpServletRequest request, FWModel model, int scope) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Model modelBean = (Model) ReflectionUtil.createInstance(model.getType());
        if (scope==0) {
            request.getSession().setAttribute(model.getName(), modelBean);
        } else if (scope==1) {
            request.setAttribute(model.getName(), modelBean);
        } else {
            request.getSession().getServletContext().setAttribute(model.getName(), modelBean);
        }
    }
}
