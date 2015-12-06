package org.frozenarc.zeframework.servlet;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import org.frozenarc.zeframework.actionhandler.ActionHandler;
import org.frozenarc.zeframework.actionhandler.ActionHandlerFactory;
import org.frozenarc.zeframework.actionvalidator.ActionValidationError;
import org.frozenarc.zeframework.actionvalidator.ActionValidationErrors;
import org.frozenarc.zeframework.actionvalidator.ActionValidator;
import org.frozenarc.zeframework.actionvalidator.ActionValidatorFactory;
import org.frozenarc.zeframework.applicationexception.ApplicationExceptionUtil;
import org.frozenarc.zeframework.connector.ActionViewConnector;
import org.frozenarc.zeframework.connector.ActionViewConnectorFactory;
import org.frozenarc.zeframework.constants.FWConstants;
import org.frozenarc.zeframework.datatypeconvert.ConversionErrors;
import org.frozenarc.zeframework.evententities.EventEntities;
import org.frozenarc.zeframework.fwconfig.FWAction;
import org.frozenarc.zeframework.fwconfig.FWActionValidator;
import org.frozenarc.zeframework.fwconfig.FWConfigUtil;
import org.frozenarc.zeframework.fwconfig.FWException;
import org.frozenarc.zeframework.model.Model;
import org.frozenarc.zeframework.processingtube.AbstractProcessingTube;
import org.frozenarc.zeframework.processingtube.ProcessingTubeFactory;
import org.frozenarc.zeframework.util.BasicValidationUtil;
import org.frozenarc.zeframework.util.ModelsCreator;
import org.frozenarc.zeframework.util.ModelsPopulator;

/**
 * Controller servlet to handle action event.
 * @author Manan
 */
@MultipartConfig
public class ActionController extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            
            EventEntities entities = new EventEntities();
            entities.setRequest(request);
            entities.setResponse(response);

            String actionName = request.getServletPath();
            actionName = actionName.substring(1, actionName.indexOf(FWConstants.ACTION_SUFFIX));
            FWAction action = FWConfigUtil.getAction(actionName);
            ApplicationExceptionUtil.checkNullPointerException(action, "Action name \""+actionName+"\" is not configured.");
            String viewName = request.getParameter(FWConstants.VIEW_NAME_VALUE);
            BasicValidationUtil.validateData(request);
            Model[] models = ModelsCreator.createModelsForAction(actionName, request);
            ConversionErrors errors = ModelsPopulator.populateModels(actionName, request);
            if (errors.getConversionErrorList().size() > 0) {
                ServletUtil.preserveConversionErrors(request, errors);
                ServletUtil.gotoPage(entities, viewName + FWConstants.VIEW_SUFFIX, false);
                return;
            }

            if (action.getValidators() != null) {
                List<ActionValidationError> validations = new ArrayList<ActionValidationError>();
                List<FWActionValidator> fWValidators = action.getValidators();
                boolean callNext = true;
                for (int i = 0; i < fWValidators.size() && callNext; i++) {
                    FWActionValidator fWValidator = fWValidators.get(i);
                    ActionValidator validator = ActionValidatorFactory.getValidator(fWValidator.getType());
                    callNext = validator.validate(models, validations, entities);
                }
                if (validations.size() > 0) {
                    ActionValidationErrors validationErrors = new ActionValidationErrors(validations);
                    ServletUtil.preserveValidationErrors(request, validationErrors);
                    ServletUtil.gotoPage(entities, viewName + FWConstants.VIEW_SUFFIX, false);
                    return;
                }
            }

            ActionViewConnector connector = null;
            connector = ActionViewConnectorFactory.getActionViewConnector(request, true);
            connector.setAction(actionName);

            ActionHandler handler = (ActionHandler) ActionHandlerFactory.getActionHandler(action.getHandler());
            AbstractProcessingTube processingTube = ProcessingTubeFactory.getProcessingTube();
            try {
                String viewAfterAction = processingTube.processActionTube(handler, models, connector, entities);
                if (viewAfterAction != null) {
                    viewName = viewAfterAction;
                }
                ServletUtil.gotoPage(entities, viewName + FWConstants.VIEW_SUFFIX, FWConfigUtil.isSendRedirect(actionName, viewName));
            } catch (Exception ex) {
                FWException actionException = FWConfigUtil.getActionFWException(action, ex);
                ServletUtil.handleException(ex, actionException, connector, entities);
            }
        } catch (Exception exception) {
            throw new ServletException(exception);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "ActionController calls action handler to handle request";
    }
    
}
