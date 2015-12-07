package org.frozenarc.zeframework.servlet;

import org.frozenarc.zeframework.connector.ActionViewConnector;
import org.frozenarc.zeframework.connector.ActionViewConnectorFactory;
import org.frozenarc.zeframework.fwconfig.FWException;
import org.frozenarc.zeframework.fwconfig.FWView;
import org.frozenarc.zeframework.model.Model;
import org.frozenarc.zeframework.processingtube.AbstractProcessingTube;
import org.frozenarc.zeframework.processingtube.ProcessingTubeFactory;
import org.frozenarc.zeframework.viewloader.ViewLoader;
import org.frozenarc.zeframework.viewloader.ViewLoaderFactory;
import org.frozenarc.zeframework.fwconfig.FWConfigUtil;
import org.frozenarc.zeframework.constants.FWConstants;
import org.frozenarc.zeframework.evententities.EventEntities;
import org.frozenarc.zeframework.util.ModelsCreator;
import java.io.*;

import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import org.frozenarc.zeframework.applicationexception.ApplicationExceptionUtil;
import org.frozenarc.zeframework.fwconfig.FWViewValidator;
import org.frozenarc.zeframework.viewvalidator.ViewValidator;
import org.frozenarc.zeframework.viewvalidator.ViewValidatorFactory;

/**
 * Controller servlet to handle view event.
 * @author Manan
 */
public class ViewController extends HttpServlet {

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

            String viewName = request.getServletPath();
            viewName = viewName.substring(1, viewName.indexOf(FWConstants.VIEW_SUFFIX));
            FWView view = FWConfigUtil.getView(viewName);
            ApplicationExceptionUtil.checkNullPointerException(view, "View name \""+viewName+"\" is not configured.");
            
            ActionViewConnector connector = ActionViewConnectorFactory.getActionViewConnector(request, false);
            
            if (view.getValidators() != null) {
                List<FWViewValidator> fWValidators = view.getValidators();
                String validateViewName = null;
                for (int i = 0; i < fWValidators.size() && validateViewName==null; i++) {
                    FWViewValidator fWValidator = fWValidators.get(i);
                    ViewValidator validator = ViewValidatorFactory.getValidator(fWValidator.getType());
                    validateViewName = validator.validate(connector, entities);
                }
                if(validateViewName!=null) {
                    ServletUtil.gotoPage(entities, validateViewName + FWConstants.VIEW_SUFFIX, true);
                    return;
                }
            }
            
            Model[] models = ModelsCreator.createModelsForView(viewName, request);
            
            if (view.getLoader() != null) {
                if (connector != null) {
                    connector.setView(viewName);
                }
                ViewLoader loader = (ViewLoader) ViewLoaderFactory.getViewLoader(view.getLoader());
                AbstractProcessingTube processingTube = ProcessingTubeFactory.getProcessingTube();
                try {
                    processingTube.processViewTube(loader, models, connector, entities);
                } catch (Exception ex) {
                    FWException viewException = FWConfigUtil.getViewFWException(view, ex);
                    ServletUtil.handleException(ex, viewException, connector, entities);
                    return;
                }
            }
            request.setAttribute(FWConstants.VIEW_NAME, view.getName());
            ServletUtil.gotoPage(entities, view.getPage(), false);
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
        return "ViewController calls loader to load page data";
    }
    
}
