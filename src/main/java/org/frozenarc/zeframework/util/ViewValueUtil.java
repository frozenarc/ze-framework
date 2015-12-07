package org.frozenarc.zeframework.util;

import org.frozenarc.zeframework.fwconfig.FWConfigUtil;
import org.frozenarc.zeframework.constants.FWConstants;
import org.frozenarc.zeframework.fwconfig.FWModel;
import java.lang.reflect.Array;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import org.frozenarc.zeframework.applicationexception.ApplicationException;
import org.frozenarc.zeframework.applicationexception.ApplicationExceptionUtil;

/**
 * Utility class for getting value from model in view event
 * @author Manan
 */
public class ViewValueUtil {

    public static void handleValueTo(PageContext pageContext, StringBuilder builder, String name, String valueto, boolean arrayType) throws ApplicationException {
        String arrayPrefix = arrayType ? FWConstants.ARRAY_VALUE : "";
        ExpressionUtil.validateExpression(valueto);
        valueto = ExpressionUtil.replaceVariablesWithValues(pageContext, valueto);
        builder.append("<input type=\"hidden\" name=\"" + FWConstants.VALUE_TO_SIGN + name + "\" value=\"" + arrayPrefix + valueto + "\" />");
    }

    public static Object handleValueFrom(PageContext pageContext, String valueFrom) throws Exception {
        ExpressionUtil.validateExpression(valueFrom);
        valueFrom=ExpressionUtil.removePrefixAndBracesFromExpression(valueFrom);
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String viewName = (String) request.getAttribute(FWConstants.VIEW_NAME);
        ApplicationExceptionUtil.checkNullPointerException(viewName, "View name is null for the request");
        Map<String, FWModel> modelMap = FWConfigUtil.getViewModelMap(viewName);
        ApplicationExceptionUtil.checkNullPointerException(modelMap, "Models haven't been configured for view name \""+viewName+"\"");
        valueFrom = ExpressionUtil.replaceVariablesWithValues(pageContext, valueFrom);
        Object lastParent = ExpressionUtil.getLastParent((HttpServletRequest)pageContext.getRequest(), modelMap, valueFrom);
        if(lastParent instanceof LastUDOInfo) {
            Object obj=((LastUDOInfo)lastParent).getObject();
            String property=((LastUDOInfo)lastParent).getProperty();
            return ReflectionUtil.getterInvoke(obj, property);
        } else if(lastParent instanceof LastODAInfo) {
            Object obj=((LastODAInfo)lastParent).getArray();
            int index=((LastODAInfo)lastParent).getIndex();
            return Array.get(obj, index);
        } else if(lastParent instanceof ArrayObject) {
            Object obj=((ArrayObject)lastParent).getArrayObj();
            return Array.getLength(obj);
        } else {
            return null;
        }
    }
}
