package org.frozenarc.zeframework.servletcontext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.frozenarc.zeframework.applicationproperties.ApplicationProperties;
import org.frozenarc.zeframework.fwconfig.FWConfig;
import org.frozenarc.zeframework.fwconfig.FWConfigUtil;
import org.frozenarc.zeframework.lifecycle.ApplicationDestroyer;
import org.frozenarc.zeframework.lifecycle.ApplicationInitializer;
import org.frozenarc.zeframework.util.ReflectionUtil;

/**
 * ServletContextListener to handle init and destroy.
 * @author Manan
 */
public class FWContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        try {
            FWConfig fWConfig = ConfigLoader.loadConfig(event.getServletContext());
            if (fWConfig.getAppPropsFilePath() != null) {
                ApplicationProperties.loadProperties(event.getServletContext(), fWConfig.getAppPropsFilePath());
            }
            String appInit = FWConfigUtil.getAppInitializer();
            if (appInit != null) {
                ApplicationInitializer appInitializer = (ApplicationInitializer) ReflectionUtil.createInstance(appInit);
                appInitializer.initializeApplication(event.getServletContext());
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void contextDestroyed(ServletContextEvent event) {
        try {
            String appDestroy = FWConfigUtil.getAppDestroyer();
            if (appDestroy != null) {
                ApplicationDestroyer appDestroyer = (ApplicationDestroyer) ReflectionUtil.createInstance(appDestroy);
                appDestroyer.destroyApplication(event.getServletContext());
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
