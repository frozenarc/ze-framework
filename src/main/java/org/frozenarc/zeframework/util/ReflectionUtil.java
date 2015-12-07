package org.frozenarc.zeframework.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.frozenarc.zeframework.applicationexception.ApplicationException;
import org.frozenarc.zeframework.applicationexception.ApplicationExceptionUtil;

/**
 * Utility wrapper for reflation api.
 * @author Manan
 */
public class ReflectionUtil {

    public static Object getterInvoke(Object obj, String property) throws ApplicationException, IllegalAccessException, InvocationTargetException {
        Method method = getGetterMethod(obj, property);
        return method.invoke(obj);
    }

    public static void setterInvoke(Object obj, String property, Class propertyType, Object value) throws ApplicationException, IllegalAccessException, InvocationTargetException {
        Method method = getSetterMethod(obj, propertyType, property);
        method.invoke(obj, value);
    }

    public static Class getGetterMethodReturnType(Object obj, String property) throws ApplicationException {
        Method method = getGetterMethod(obj, property);
        return method.getReturnType();
    }

    public static Method getGetterMethod(Object obj, String property) throws ApplicationException {
        Method method = null;
        try {
            method = obj.getClass().getMethod(getGetterMethod(property));
        } catch (NoSuchMethodException exGet) {
            try {
                method = obj.getClass().getMethod(getIsserMethod(property));
            } catch (NoSuchMethodException exIs) {
                ApplicationExceptionUtil.throwAppException("Getter method not found for \"" + property + "\"");
            }
        }
        return method;
    }

    public static String getGetterMethod(String property) {
        if (property.contains("[")) {
            property = property.substring(0, property.indexOf("["));
        }
        return "get" + property.substring(0, 1).toUpperCase() + property.substring(1);
    }

    public static String getIsserMethod(String property) {
        if (property.contains("[")) {
            property = property.substring(0, property.indexOf("["));
        }
        return "is" + property.substring(0, 1).toUpperCase() + property.substring(1);
    }

    public static Method getSetterMethod(Object obj, Class propertyType, String property) throws ApplicationException {
        try {
            return obj.getClass().getMethod(getSetterMethod(property), propertyType);
        } catch (NoSuchMethodException ex) {
            ApplicationExceptionUtil.throwAppException("Setter method not found for \"" + property + "\"");
        }
        return null;
    }

    public static String getSetterMethod(String property) {
        return "set" + property.substring(0, 1).toUpperCase() + property.substring(1);
    }
    
    public static Object createInstance(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return Class.forName(className).newInstance();
    }
}
