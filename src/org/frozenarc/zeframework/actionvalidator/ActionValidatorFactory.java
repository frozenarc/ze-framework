package org.frozenarc.zeframework.actionvalidator;

import org.frozenarc.zeframework.util.ReflectionUtil;

/**
 * Creates validator.
 * @author Manan
 */
public class ActionValidatorFactory {

    public static ActionValidator getValidator(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (ActionValidatorContext.getValue(className) != null) {
            return ActionValidatorContext.getValue(className);
        } else {
            return createValidatorSingleton(className);
        }
    }

    protected static synchronized ActionValidator createValidatorSingleton(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (ActionValidatorContext.getValue(className) == null) {
            ActionValidator validator = (ActionValidator) ReflectionUtil.createInstance(className);
            ActionValidatorContext.putValue(className, validator);
        }
        return ActionValidatorContext.getValue(className);
    }
}
