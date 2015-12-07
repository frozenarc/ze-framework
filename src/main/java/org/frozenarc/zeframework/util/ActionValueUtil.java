package org.frozenarc.zeframework.util;

import org.frozenarc.zeframework.datatypeconvert.DataTypeConvertor;
import org.frozenarc.zeframework.fwconfig.FWConfigUtil;
import org.frozenarc.zeframework.fwconfig.FWModel;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.frozenarc.zeframework.applicationexception.ApplicationExceptionUtil;
import org.frozenarc.zeframework.model.FileData;

/**
 * Utility class for setting value to model in action event
 * @author Manan
 */
public class ActionValueUtil {
    
    public static Object getLastParent(String actionName, HttpServletRequest request, String valueTo) throws Exception {
        Map<String, FWModel> modelMap = FWConfigUtil.getActionModelMap(actionName);
        ApplicationExceptionUtil.checkNullPointerException(modelMap, "Models haven't been configured for action name \""+actionName+"\"");
        valueTo=ExpressionUtil.removePrefixAndBracesFromExpression(valueTo);
        return ExpressionUtil.getLastParent(request, modelMap, valueTo);
    }
    
    public static void handlePartValueTo(String actionName, HttpServletRequest request, String valueTo, ArrayList<Part> partList, boolean arrayType) throws Exception {
        Object lastParent = getLastParent(actionName, request, valueTo);
        if (lastParent instanceof LastUDOInfo) {
            Object objParent = ((LastUDOInfo) lastParent).getObject();
            String property = ((LastUDOInfo) lastParent).getProperty();
            FileData[] fileDatas=PartToFileDataConvertor.convert(partList);
            Object objValue=getFileData(arrayType, fileDatas);
            ReflectionUtil.setterInvoke(objParent, property, FileData.class, objValue);
        } else if (lastParent instanceof LastODAInfo) {
            Object objParent = ((LastODAInfo) lastParent).getArray();
            int index = ((LastODAInfo) lastParent).getIndex();
            FileData[] fileDatas=PartToFileDataConvertor.convert(partList);
            Object objValue=getFileData(arrayType, fileDatas);
            Array.set(objParent, index, objValue);
        }
    }

    public static void handleValueTo(String actionName, HttpServletRequest request, String valueTo, String paramName, String[] values, boolean arrayType) throws Exception {
        FormatInfo info = FormatEvaluator.getFormatInfo(valueTo);
        Object lastParent = getLastParent(actionName, request, valueTo);
        if (lastParent instanceof LastUDOInfo) {
            Object objParent = ((LastUDOInfo) lastParent).getObject();
            String property = ((LastUDOInfo) lastParent).getProperty();
            Class returnType = ReflectionUtil.getGetterMethodReturnType(objParent, property);
            Object objArray = DataTypeConvertor.convertString(paramName, values, returnType, info);
            Object objValue=geObjectValue(returnType.getCanonicalName(), arrayType, objArray);
            ReflectionUtil.setterInvoke(objParent, property, returnType, objValue);
        } else if (lastParent instanceof LastODAInfo) {
            Object objParent = ((LastODAInfo) lastParent).getArray();
            int index = ((LastODAInfo) lastParent).getIndex();
            Class type = getSubElementClass(objParent.getClass());
            Object objArray = DataTypeConvertor.convertString(paramName, values, type, info);
            Object objValue=geObjectValue(type.getCanonicalName(), arrayType, objArray);
            Array.set(objParent, index, objValue);
        }
    }
    
    public static Object getFileData(boolean arrayType, FileData[] fileDatas) {
        return arrayType ? fileDatas : fileDatas[0];
    }

    public static Object geObjectValue(String className, boolean arrayType, Object objArray) {
        Object objValue;
        if (className.contains("int")) {
            int[] ints = (int[]) objArray;
            objValue = arrayType ? ints : ints[0];
        } else if (className.contains("long")) {
            long[] longs = (long[]) objArray;
            objValue = arrayType ? longs : longs[0];
        } else if (className.contains("float")) {
            float[] floats = (float[]) objArray;
            objValue = arrayType ? floats : floats[0];
        } else if (className.contains("double")) {
            double[] doubles = (double[]) objArray;
            objValue = arrayType ? doubles : doubles[0];
        } else if (className.contains("short")) {
            short[] shorts = (short[]) objArray;
            objValue = arrayType ? shorts : shorts[0];
        } else if (className.contains("byte")) {
            byte[] bytes = (byte[]) objArray;
            objValue = arrayType ? bytes : bytes[0];
        } else if (className.contains("boolean")) {
            boolean[] booleans = (boolean[]) objArray;
            objValue = arrayType ? booleans : booleans[0];
        } else if (className.contains("char")) {
            char[] chars = (char[]) objArray;
            objValue = arrayType ? chars : chars[0];
        } else {
            Object[] objects = (Object[]) objArray;
            objValue = arrayType ? objects : objects[0];
        } 
        return objValue;
    }

    public static Class getSubElementClass(Class clazz) throws ClassNotFoundException {
        if (clazz.getCanonicalName().equals(String[][].class.getCanonicalName())) {
            return String[].class;
        } else if (clazz.getCanonicalName().equals(Date[][].class.getCanonicalName())) {
            return Date[].class;
        } else if (clazz.getCanonicalName().equals(Integer[][].class.getCanonicalName())) {
            return Integer[].class;
        } else if (clazz.getCanonicalName().equals(Float[][].class.getCanonicalName())) {
            return Float[].class;
        } else if (clazz.getCanonicalName().equals(Double[][].class.getCanonicalName())) {
            return Double[].class;
        } else if (clazz.getCanonicalName().equals(Long[][].class.getCanonicalName())) {
            return Long[].class;
        } else if (clazz.getCanonicalName().equals(Short[][].class.getCanonicalName())) {
            return Short[].class;
        } else if (clazz.getCanonicalName().equals(Byte[][].class.getCanonicalName())) {
            return Byte[].class;
        } else if (clazz.getCanonicalName().equals(Boolean[][].class.getCanonicalName())) {
            return Boolean[].class;
        } else if (clazz.getCanonicalName().equals(Character[][].class.getCanonicalName())) {
            return Character[].class;
        } else if (clazz.getCanonicalName().equals(int[][].class.getCanonicalName())) {
            return int[].class;
        } else if (clazz.getCanonicalName().equals(float[][].class.getCanonicalName())) {
            return float[].class;
        } else if (clazz.getCanonicalName().equals(double[][].class.getCanonicalName())) {
            return double[].class;
        } else if (clazz.getCanonicalName().equals(long[][].class.getCanonicalName())) {
            return long[].class;
        } else if (clazz.getCanonicalName().equals(short[][].class.getCanonicalName())) {
            return short[].class;
        } else if (clazz.getCanonicalName().equals(byte[][].class.getCanonicalName())) {
            return byte[].class;
        } else if (clazz.getCanonicalName().equals(boolean[][].class.getCanonicalName())) {
            return boolean[].class;
        } else if (clazz.getCanonicalName().equals(char[][].class.getCanonicalName())) {
            return char[].class;
        } else if (clazz.getCanonicalName().equals(String[].class.getCanonicalName())) {
            return String.class;
        } else if (clazz.getCanonicalName().equals(Date[].class.getCanonicalName())) {
            return Date.class;
        } else if (clazz.getCanonicalName().equals(Integer[].class.getCanonicalName())) {
            return Integer.class;
        } else if (clazz.getCanonicalName().equals(Float[].class.getCanonicalName())) {
            return Float.class;
        } else if (clazz.getCanonicalName().equals(Double[].class.getCanonicalName())) {
            return Double.class;
        } else if (clazz.getCanonicalName().equals(Long[].class.getCanonicalName())) {
            return Long.class;
        } else if (clazz.getCanonicalName().equals(Short[].class.getCanonicalName())) {
            return Short.class;
        } else if (clazz.getCanonicalName().equals(Byte[].class.getCanonicalName())) {
            return Byte.class;
        } else if (clazz.getCanonicalName().equals(Boolean[].class.getCanonicalName())) {
            return Boolean.class;
        } else if (clazz.getCanonicalName().equals(Character[].class.getCanonicalName())) {
            return Character.class;
        } else if (clazz.getCanonicalName().equals(int[].class.getCanonicalName())) {
            return int.class;
        } else if (clazz.getCanonicalName().equals(float[].class.getCanonicalName())) {
            return float.class;
        } else if (clazz.getCanonicalName().equals(double[].class.getCanonicalName())) {
            return double.class;
        } else if (clazz.getCanonicalName().equals(long[].class.getCanonicalName())) {
            return long.class;
        } else if (clazz.getCanonicalName().equals(short[].class.getCanonicalName())) {
            return short.class;
        } else if (clazz.getCanonicalName().equals(byte[].class.getCanonicalName())) {
            return byte.class;
        } else if (clazz.getCanonicalName().equals(boolean[].class.getCanonicalName())) {
            return boolean.class;
        } else if (clazz.getCanonicalName().equals(char[].class.getCanonicalName())) {
            return char.class;
        } else if (clazz.getCanonicalName().equals(Object[].class.getCanonicalName())) {
            return Object.class;
        } else {
            return null;
        }
    }
}
