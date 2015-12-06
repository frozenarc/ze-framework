package org.frozenarc.zeframework.actionhandler;

import org.frozenarc.zeframework.util.ReflectionUtil;

/**
 * The class create ActionHandler.
 * @author Manan
 */
public class ActionHandlerFactory {

    public static ActionHandler getActionHandler(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (ActionHandlerContext.getValue(className) != null) {
            return ActionHandlerContext.getValue(className);
        } else {
            return createActionHandlerSingleton(className);
        }
    }

    protected static synchronized ActionHandler createActionHandlerSingleton(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (ActionHandlerContext.getValue(className) == null) {
            ActionHandler actionHandler = (ActionHandler) ReflectionUtil.createInstance(className);
            ActionHandlerContext.putValue(className, actionHandler);
        }
        return ActionHandlerContext.getValue(className);
    }
}
