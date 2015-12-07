package org.frozenarc.zeframework.connector;

import org.frozenarc.zeframework.constants.FWConstants;
import javax.servlet.http.HttpServletRequest;

/**
 * The class creates ActionViewConnector.
 * @author Manan
 */
public class ActionViewConnectorFactory {

    public static ActionViewConnector getActionViewConnector(HttpServletRequest request, boolean forAction) {
        ActionViewConnector connector;
        if (forAction) {
            request.getSession().setAttribute(FWConstants.ACTION_VIEW_CONNECTOR, connector = new ActionViewConnector());
        } else {
            connector = (ActionViewConnector) request.getSession().getAttribute(FWConstants.ACTION_VIEW_CONNECTOR);
            if (connector != null) {
                request.getSession().removeAttribute(FWConstants.ACTION_VIEW_CONNECTOR);
            }
        }
        return connector;
    }
}
