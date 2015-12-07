package org.frozenarc.zeframework.util;

import org.frozenarc.zeframework.fwconfig.FWModel;
import org.frozenarc.zeframework.model.Model;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import org.frozenarc.zeframework.applicationexception.ApplicationException;
import org.frozenarc.zeframework.applicationexception.ApplicationExceptionUtil;
import org.frozenarc.zeframework.constants.FWConstants;

/**
 *
 * @author Manan
 */
public class ExpressionUtil {

    public static String replaceVariablesWithValues(PageContext pageContext, String expr) throws ApplicationException {
        String varSign = FWConstants.VARIABLE_VALUE_START;
        if (!expr.contains(varSign)) {
            return expr;
        }
        int fromIndex = 0;
        StringBuilder builder = new StringBuilder();
        int loopCount = 0;
        while (expr.indexOf("[", fromIndex) != -1 && expr.indexOf("]", fromIndex) != -1) {
            loopCount++;
            builder.append(expr.substring(fromIndex, expr.indexOf("[", fromIndex)));
            builder.append("[");
            fromIndex = expr.indexOf("[", fromIndex) + 1;
            String token = expr.substring(fromIndex, expr.indexOf("]", fromIndex));
            if (token.indexOf(varSign) == 0) {
                token = token.substring(1, token.length());
                ApplicationExceptionUtil.checkNullPointerException(pageContext.getAttribute(token), "Variable " + token + " is not exist in scope.");
                token = pageContext.getAttribute(token).toString();
            }
            builder.append(token);
            builder.append("]");
            fromIndex = expr.indexOf("]", fromIndex) + 1;
        }
        if (fromIndex < expr.length()) {
            builder.append(expr.substring(fromIndex));
        }
        return loopCount == 0 ? expr : builder.toString();
    }

    public static Object evaluateSimpleProperty(Object parentObj, String propertyName) throws Exception {
        return ReflectionUtil.getterInvoke(parentObj, propertyName);
    }

    public static Object getLastOneDimArrayInfo(Object parentObj, String propertyName) throws Exception {
        return evaluateArrayElementProperty(parentObj, propertyName, true);
    }

    public static Object evaluateArrayElementProperty(Object parentObj, String propertyName, boolean lastODA) throws Exception {
        int fromIndex = 0;
        ArrayList<Integer> arrayIndexList = new ArrayList<Integer>();
        while (propertyName.indexOf("[", fromIndex) != -1 && propertyName.indexOf("]", fromIndex) != -1) {
            fromIndex = propertyName.indexOf("[", fromIndex) + 1;
            String arrayIndexStr = propertyName.substring(fromIndex, propertyName.indexOf("]", fromIndex));
            arrayIndexList.add(Integer.valueOf(arrayIndexStr));
            fromIndex = propertyName.indexOf("]", fromIndex) + 1;
        }
        propertyName = propertyName.substring(0, propertyName.indexOf("["));
        Object obj = ReflectionUtil.getterInvoke(parentObj, propertyName);
        int arrayIndexListSize = lastODA ? arrayIndexList.size() - 1 : arrayIndexList.size();
        for (int i = 0; i < arrayIndexListSize; i++) {
            obj = ((Object[]) obj)[arrayIndexList.get(i)];
        }
        return lastODA ? new LastODAInfo(obj, arrayIndexList.get(arrayIndexListSize)) : obj;
    }

    public static Object evaluateProperty(Object parentObj, String propertyName) throws Exception {
        if (propertyName.contains("[")) {
            return evaluateArrayElementProperty(parentObj, propertyName, false);
        } else {
            return evaluateSimpleProperty(parentObj, propertyName);
        }
    }

    public static Object evaluateExpression(Object parentObj, String expression) throws Exception {
        if (!expression.contains(".")) {
            String propertyName = expression;
            if (propertyName.contains("[")) {
                return evaluateArrayElementProperty(parentObj, propertyName, true);
            } else if (propertyName.equals("length") && parentObj.getClass().isArray()) {
                return new ArrayObject(parentObj);
            } else {
                return new LastUDOInfo(parentObj, propertyName);
            }
        } else {
            String propertyName = expression.substring(0, expression.indexOf("."));
            Object obj = evaluateProperty(parentObj, propertyName);
            return evaluateExpression(obj, expression.substring(expression.indexOf(".") + 1));
        }
    }

    public static Object evaluateExpression(HttpServletRequest request, Map<String, FWModel> modelMap, String expression) throws Exception {
        String modelName = expression.substring(0, expression.indexOf("."));
        ApplicationExceptionUtil.checkNullPointerException(modelMap.get(modelName), "Model \"" + modelName + "\" is not configured for this view.");
        Model model = ModelUtil.getModel(request, modelMap.get(modelName), false);
        return evaluateExpression(model, expression.substring(expression.indexOf(".") + 1));
    }

    public static Object getLastParent(HttpServletRequest request, Map<String, FWModel> modelMap, String expression) throws Exception {
        return evaluateExpression(request, modelMap, expression);
    }

    public static String removePrefixAndBracesFromExpression(String expression) throws Exception {
        return expression.substring(expression.indexOf(FWConstants.MODEL_VALUE_START) + FWConstants.MODEL_VALUE_START.length(), expression.indexOf(FWConstants.VALUE_END));
    }

    public static void validateExpression(String expression) throws ApplicationException {
        if (!expression.startsWith(FWConstants.MODEL_VALUE_START)) {
            ApplicationExceptionUtil.throwAppException("Expressoin \"" + expression + "\" doesn't contain \"" + FWConstants.MODEL_VALUE_START + "\"");
        }
        if (!expression.endsWith(FWConstants.VALUE_END)) {
            ApplicationExceptionUtil.throwAppException("Expressoin \"" + expression + "\" doesn't contain \"" + FWConstants.VALUE_END + "\"");
        }
        if (!expression.contains(".")) {
            ApplicationExceptionUtil.throwAppException("Expressoin \"" + expression + "\" is not referencing any model property");
        }
        int index = 0;
        String modelValueExpression = expression.substring(expression.indexOf(FWConstants.MODEL_VALUE_START, index), index = expression.indexOf(FWConstants.VALUE_END, index) + FWConstants.VALUE_END.length());
        if (modelValueExpression.contains(FWConstants.FORMAT_VALUE_START) || modelValueExpression.contains(FWConstants.TIMEZONE_VALUE_START)) {
            ApplicationExceptionUtil.throwAppException("Expressoin \"" + expression + "\" doesn't contain \"" + FWConstants.VALUE_END + "\" for model value");
        }
        if (modelValueExpression.indexOf(FWConstants.VALUE_END) != modelValueExpression.length() - 1) {
            ApplicationExceptionUtil.throwAppException("Expressoin \"" + expression + "\" contains access \"" + FWConstants.VALUE_END + "\" for model value");
        }
        if (expression.contains(FWConstants.FORMAT_VALUE_START)) {
            String formatValueExpression = expression.substring(expression.indexOf(FWConstants.FORMAT_VALUE_START, index), index = expression.indexOf(FWConstants.VALUE_END, index) + FWConstants.VALUE_END.length());
            if (formatValueExpression.contains(FWConstants.TIMEZONE_VALUE_START)) {
                ApplicationExceptionUtil.throwAppException("Expressoin \"" + expression + "\" doesn't contain \"" + FWConstants.VALUE_END + "\" for format value");
            }
            if (formatValueExpression.indexOf(FWConstants.VALUE_END) != formatValueExpression.length() - 1) {
                ApplicationExceptionUtil.throwAppException("Expressoin \"" + expression + "\" contains access \"" + FWConstants.VALUE_END + "\" for format value");
            }
        }
        if (expression.contains(FWConstants.TIMEZONE_VALUE_START)) {
            String timezoneValueExpression = expression.substring(expression.indexOf(FWConstants.TIMEZONE_VALUE_START, index), index = expression.indexOf(FWConstants.VALUE_END, index) + FWConstants.VALUE_END.length());
            if (timezoneValueExpression.indexOf(FWConstants.VALUE_END) != timezoneValueExpression.length() - 1) {
                ApplicationExceptionUtil.throwAppException("Expressoin \"" + expression + "\" contains access \"" + FWConstants.VALUE_END + "\"  for timzone value");
            }
        }
        if (expression.contains("[") || expression.contains("]")) {
            boolean left = false;
            char[] expArray = expression.toCharArray();
            for (int i = 0; i < expArray.length; i++) {
                if (expArray[i] == '[') {
                    if (!left) {
                        left = true;                        
                    } else {
                        ApplicationExceptionUtil.throwAppException("Expression \"" + expression + "\" is missing \"]\"");
                    }
                } else if (expArray[i] == ']') {
                    if (left) {
                        left = false;                        
                    } else {
                        ApplicationExceptionUtil.throwAppException("Expression \"" + expression + "\" is missing \"[\"");
                    }
                }
            }
        }
    }
}
