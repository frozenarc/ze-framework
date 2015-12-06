package org.frozenarc.zeframework.servlet;

import org.frozenarc.zeframework.fwconfig.FWConfigUtil;
import org.frozenarc.zeframework.connector.ActionViewConnector;
import org.frozenarc.zeframework.constants.FWConstants;
import org.frozenarc.zeframework.datatypeconvert.ConversionErrors;
import org.frozenarc.zeframework.evententities.EventEntities;
import org.frozenarc.zeframework.exceptionhandler.ExceptionHandler;
import org.frozenarc.zeframework.exceptionhandler.ExceptionHandlerFactory;
import org.frozenarc.zeframework.fwconfig.FWException;
import org.frozenarc.zeframework.actionvalidator.ActionValidationErrors;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Utility class for servlet
 * @author Manan
 */
public class ServletUtil {

    public static void preserveExceptionForPage(HttpServletRequest request, Exception ex) {
        request.setAttribute(FWConstants.FW_EXCEPTION, ex);
    }

    public static void preserveValidationErrors(HttpServletRequest request, ActionValidationErrors validationErrors) {
        request.setAttribute(FWConstants.VALIDATION_ERRORS, validationErrors);
    }

    public static void gotoPage(EventEntities entities, String page, boolean sendRedirect) throws IOException, ServletException {
        if (!entities.getResponse().isCommitted()) {
            if (sendRedirect) {
                entities.getResponse().sendRedirect(page);
            } else {
                RequestDispatcher dispatcher = entities.getRequest().getRequestDispatcher(page);
                dispatcher.forward(entities.getRequest(), entities.getResponse());
            }
        }
    }

    public static void callExceptionHandler(Exception ex, FWException fWException, ActionViewConnector connector, EventEntities entities) throws Exception {
        if (fWException.getHandler() != null) {
            ExceptionHandler handler = ExceptionHandlerFactory.getExceptionHandler(fWException.getHandler());
            if (handler != null) {
                handler.handleException(ex, connector, entities);
            }
        }
    }

    public static void handleException(Exception ex, FWException fwException, ActionViewConnector connector, EventEntities entities) throws Exception {
        if (fwException != null) {
            exceptionHandlingMethod(ex, fwException, connector, entities);
        } else {
            FWException exception = FWConfigUtil.getFWException(ex);
            if (exception != null) {
                exceptionHandlingMethod(ex, exception, connector, entities);
            } else {
                throw new ServletException(ex);
            }
        }
    }

    protected static void exceptionHandlingMethod(Exception ex, FWException fwException, ActionViewConnector connector, EventEntities entities) throws Exception {
        callExceptionHandler(ex, fwException, connector, entities);
        preserveExceptionForPage(entities.getRequest(), ex);
        gotoPage(entities, fwException.getPage(), false);
    }

    public static void preserveConversionErrors(HttpServletRequest request, ConversionErrors conversionErrors) {
        request.setAttribute(FWConstants.CONVERSION_ERRORS, conversionErrors);
    }
}
