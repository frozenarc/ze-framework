package org.frozenarc.zeframework.util;

import org.frozenarc.zeframework.fwconfig.FWConfigUtil;
import org.frozenarc.zeframework.fwconfig.FWModel;
import org.frozenarc.zeframework.model.Model;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.frozenarc.zeframework.applicationexception.ApplicationExceptionUtil;
import org.frozenarc.zeframework.constants.FWConstants;
import org.frozenarc.zeframework.model.ModelInfo;

/**
 * Creates model.
 * @author Manan
 */
public class ModelsCreator {

    public static Model[] createModelsForView(String viewName, HttpServletRequest request) throws Exception {
        List<FWModel> modelList = FWConfigUtil.getViewModels(viewName);
        if (modelList == null) {
            return null;
        }
        Model[] models = new Model[modelList.size()];
        for (int i = 0; i < modelList.size(); i++) {
            FWModel model = modelList.get(i);
            if(model.getType().equals(FWConstants.UNDEFINED_MODEL)) {
                ApplicationExceptionUtil.throwAppException("Model \""+model.getName()+"\" is not defined.");
            }
            models[i] = ModelUtil.getModel(request, model, true);
            models[i].viewInit();
        }
        return models;
    }

    public static Model[] createModelsForAction(String actionName, HttpServletRequest request) throws Exception {
        List<FWModel> modelList = FWConfigUtil.getActionModels(actionName);
        if (modelList == null) {
            return null;
        }
        Model[] models = new Model[modelList.size()];
        for (int i = 0; i < modelList.size(); i++) {
            FWModel model = modelList.get(i);
            if(model.getType().equals(FWConstants.UNDEFINED_MODEL)) {
                ApplicationExceptionUtil.throwAppException("Model \""+model.getName()+"\" is not defined.");
            }
            models[i] = ModelUtil.getModel(request, model, true);
            String[] modelInfoValues = (String[]) request.getParameterMap().get(FWConstants.MODEL_INFO_SIGN + model.getName());
            ModelInfo modelInfo = new ModelInfo();
            if (modelInfoValues != null && modelInfoValues.length != 0) {
                for (String modelInfoValue : modelInfoValues) {
                    modelInfo.putValue(modelInfoValue.substring(0, modelInfoValue.indexOf(":")), modelInfoValue.substring(modelInfoValue.indexOf(":") + 1));
                }
            }
            models[i].actionInit(modelInfo);
        }
        return models;
    }
}
