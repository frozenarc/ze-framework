package org.frozenarc.zeframework.viewvalidator;

import org.frozenarc.zeframework.util.ReflectionUtil;

/**
 * Creates validator.
 * @author Manan
 */
public class ViewValidatorFactory {

    public static ViewValidator getValidator(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (ViewValidatorContext.getValue(className) != null) {
            return ViewValidatorContext.getValue(className);
        } else {
            return createValidatorSingleton(className);
        }
    }

    protected static synchronized ViewValidator createValidatorSingleton(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (ViewValidatorContext.getValue(className) == null) {
            ViewValidator validator = (ViewValidator) ReflectionUtil.createInstance(className);
            ViewValidatorContext.putValue(className, validator);
        }
        return ViewValidatorContext.getValue(className);
    }
}
