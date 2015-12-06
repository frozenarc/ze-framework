package org.frozenarc.zeframework.datatypeconvert;

import org.frozenarc.zeframework.util.FormatInfo;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import org.frozenarc.zeframework.applicationexception.ApplicationException;
import org.frozenarc.zeframework.applicationexception.ApplicationExceptionUtil;

/**
 * The class is used to convert fromstring to any object and any premitive type, and 
 * to convert fromobject and any premitive type to string.
 * @author Manan
 */
public class DataTypeConvertor {

    protected static Map<String, SimpleDateFormat> dateFormatMap = Collections.synchronizedMap(new HashMap<String, SimpleDateFormat>());
    protected static Map<String, NumberFormat> numberFormatMap = Collections.synchronizedMap(new HashMap<String, NumberFormat>());

    protected static SimpleDateFormat getSimpleDateFormat(String dateFormatPattern) {
        if (dateFormatMap.get(dateFormatPattern) == null) {
            SimpleDateFormat dateFormat;
            if (dateFormatPattern == null) {
                dateFormat = new SimpleDateFormat();
                dateFormatPattern = dateFormat.toPattern();
            } else {
                dateFormat = new SimpleDateFormat(dateFormatPattern);
            }
            dateFormatMap.put(dateFormatPattern, dateFormat);
        }
        return dateFormatMap.get(dateFormatPattern);
    }

    protected static void configureNumberFormat(NumberFormat format, String pattern) throws ApplicationException {
        ApplicationExceptionUtil.validateNumberPattern(pattern);
        int index = 0;
        String groupingUsed = pattern.substring(index, pattern.indexOf(":", index));
        index = pattern.indexOf(":", index) + 1;
        String maxIntDigit = pattern.substring(pattern.indexOf("(", index) + 1, pattern.indexOf("-", index));
        String minIntDigit = pattern.substring(pattern.indexOf("-", index) + 1, pattern.indexOf(")", index));
        index = pattern.indexOf(".", index) + 1;
        String minFracDigit = pattern.substring(pattern.indexOf("(", index) + 1, pattern.indexOf("-", index));
        String maxFracDigit = pattern.substring(pattern.indexOf("-", index) + 1, pattern.indexOf(")", index));

        try {
            format.setGroupingUsed(groupingUsed.equals("Y"));
            if (!maxIntDigit.equals("D")) {
                format.setMaximumIntegerDigits(Integer.parseInt(maxIntDigit));
            }
            if (!minIntDigit.equals("D")) {
                format.setMinimumIntegerDigits(Integer.parseInt(minIntDigit));
            }
            if (!minFracDigit.equals("D")) {
                format.setMinimumFractionDigits(Integer.parseInt(minFracDigit));
            }
            if (!maxFracDigit.equals("D")) {
                format.setMaximumFractionDigits(Integer.parseInt(maxFracDigit));
            }
        } catch (NumberFormatException ex) {
            ApplicationExceptionUtil.throwAppException("Unparsable digit in number format pattern "+pattern);
        }
    }

    protected static NumberFormat getNumberFormat(String formatPattern) throws ApplicationException {
        if (numberFormatMap.get(formatPattern) == null) {
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormatMap.put(formatPattern, numberFormat);
            configureNumberFormat(numberFormat, formatPattern);
        }
        return numberFormatMap.get(formatPattern);
    }

    public static Object convertString(String paramName, String[] values, Class clazz, FormatInfo info) throws DataTypeConversionException, ApplicationException {
        if (values != null) {
            String className = clazz.getCanonicalName();
            String format = info.getFormatPattern();
            String timeZone = info.getTimeZoneName();
            for (int i = 0; i < values.length; i++) {
                if (values[i] != null && values[i].equals("")) {
                    values[i] = null;
                }
            }
            if (className.equals(String.class.getCanonicalName()) || className.equals(String[].class.getCanonicalName())) {
                return values;
            } else if (className.equals(Date.class.getCanonicalName()) || className.equals(Date[].class.getCanonicalName())) {
                Date[] dates = new Date[values.length];
                for (int i = 0; i < values.length; i++) {
                    SimpleDateFormat dateFormat = getSimpleDateFormat(format);
                    if (timeZone != null) {
                        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
                    } else {
                        dateFormat.setTimeZone(TimeZone.getDefault());
                    }
                    try {
                        dates[i] = values[i] == null ? null : dateFormat.parse(values[i]);
                    } catch (ParseException ex) {
                        throw new DataTypeConversionException(paramName, values[i], ex);
                    }
                }
                return dates;
            } else if (className.equals(Integer.class.getCanonicalName()) || className.equals(Integer[].class.getCanonicalName())) {
                Integer[] integers = new Integer[values.length];
                for (int i = 0; i < values.length; i++) {
                    try {
                        if (values[i] != null && format != null) {
                            NumberFormat numberFormat = getNumberFormat(format);
                            integers[i] = Integer.valueOf(numberFormat.parse(values[i]).intValue());
                        } else {
                            integers[i] = values[i] == null ? null : Integer.valueOf(values[i]);
                        }
                    } catch (NumberFormatException ex) {
                        throw new DataTypeConversionException(paramName, values[i], ex);
                    } catch (ParseException ex) {
                        throw new DataTypeConversionException(paramName, values[i], ex);
                    }
                }
                return integers;
            } else if (className.equals(int.class.getCanonicalName()) || className.equals(int[].class.getCanonicalName())) {
                int[] ints = new int[values.length];
                for (int i = 0; i < values.length; i++) {
                    try {
                        if (values[i] != null && format != null) {
                            NumberFormat numberFormat = getNumberFormat(format);
                            ints[i] = numberFormat.parse(values[i]).intValue();
                        } else {
                            ints[i] = values[i] == null ? 0 : Integer.parseInt(values[i]);
                        }
                    } catch (NumberFormatException ex) {
                        throw new DataTypeConversionException(paramName, values[i], ex);
                    } catch (ParseException ex) {
                        throw new DataTypeConversionException(paramName, values[i], ex);
                    }
                }
                return ints;
            } else if (className.equals(Long.class.getCanonicalName()) || className.equals(Long[].class.getCanonicalName())) {
                Long[] longs = new Long[values.length];
                for (int i = 0; i < values.length; i++) {
                    try {
                        if (values[i] != null && format != null) {
                            NumberFormat numberFormat = getNumberFormat(format);
                            longs[i] = Long.valueOf(numberFormat.parse(values[i]).longValue());
                        } else {
                            longs[i] = values[i] == null ? null : Long.valueOf(values[i]);
                        }
                    } catch (NumberFormatException ex) {
                        throw new DataTypeConversionException(paramName, values[i], ex);
                    } catch (ParseException ex) {
                        throw new DataTypeConversionException(paramName, values[i], ex);
                    }
                }
                return longs;
            } else if (className.equals(long.class.getCanonicalName()) || className.equals(long[].class.getCanonicalName())) {
                long[] longs = new long[values.length];
                for (int i = 0; i < values.length; i++) {
                    try {
                        if (values[i] != null && format != null) {
                            NumberFormat numberFormat = getNumberFormat(format);
                            longs[i] = numberFormat.parse(values[i]).longValue();
                        } else {
                            longs[i] = values[i] == null ? 0 : Long.parseLong(values[i]);
                        }
                    } catch (NumberFormatException ex) {
                        throw new DataTypeConversionException(paramName, values[i], ex);
                    } catch (ParseException ex) {
                        throw new DataTypeConversionException(paramName, values[i], ex);
                    }
                }
                return longs;
            } else if (className.equals(Double.class.getCanonicalName()) || className.equals(Double[].class.getCanonicalName())) {
                Double[] doubles = new Double[values.length];
                for (int i = 0; i < values.length; i++) {
                    try {
                        if (values[i] != null && format != null) {
                            NumberFormat numberFormat = getNumberFormat(format);
                            doubles[i] = Double.valueOf(numberFormat.parse(values[i]).doubleValue());
                        } else {
                            doubles[i] = values[i] == null ? null : Double.valueOf(values[i]);
                        }
                    } catch (NumberFormatException ex) {
                        throw new DataTypeConversionException(paramName, values[i], ex);
                    } catch (ParseException ex) {
                        throw new DataTypeConversionException(paramName, values[i], ex);
                    }
                }
                return doubles;
            } else if (className.equals(double.class.getCanonicalName()) || className.equals(double[].class.getCanonicalName())) {
                double[] doubles = new double[values.length];
                for (int i = 0; i < values.length; i++) {
                    try {
                        if (values[i] != null && format != null) {
                            NumberFormat numberFormat = getNumberFormat(format);
                            doubles[i] = numberFormat.parse(values[i]).doubleValue();
                        } else {
                            doubles[i] = values[i] == null ? 0 : Double.parseDouble(values[i]);
                        }
                    } catch (NumberFormatException ex) {
                        throw new DataTypeConversionException(paramName, values[i], ex);
                    } catch (ParseException ex) {
                        throw new DataTypeConversionException(paramName, values[i], ex);
                    }
                }
                return doubles;
            } else if (className.equals(Float.class.getCanonicalName()) || className.equals(Float[].class.getCanonicalName())) {
                Float[] floats = new Float[values.length];
                for (int i = 0; i < values.length; i++) {
                    try {
                        if (values[i] != null && format != null) {
                            NumberFormat numberFormat = getNumberFormat(format);
                            floats[i] = Float.valueOf(numberFormat.parse(values[i]).floatValue());
                        } else {
                            floats[i] = values[i] == null ? null : Float.valueOf(values[i]);
                        }
                    } catch (NumberFormatException ex) {
                        throw new DataTypeConversionException(paramName, values[i], ex);
                    } catch (ParseException ex) {
                        throw new DataTypeConversionException(paramName, values[i], ex);
                    }
                }
                return floats;
            } else if (className.equals(float.class.getCanonicalName()) || className.equals(float[].class.getCanonicalName())) {
                float[] floats = new float[values.length];
                for (int i = 0; i < values.length; i++) {
                    try {
                        if (values[i] != null && format != null) {
                            NumberFormat numberFormat = getNumberFormat(format);
                            floats[i] = numberFormat.parse(values[i]).floatValue();
                        } else {
                            floats[i] = values[i] == null ? 0 : Float.parseFloat(values[i]);
                        }
                    } catch (NumberFormatException ex) {
                        throw new DataTypeConversionException(paramName, values[i], ex);
                    } catch (ParseException ex) {
                        throw new DataTypeConversionException(paramName, values[i], ex);
                    }
                }
                return floats;
            } else if (className.equals(Boolean.class.getCanonicalName()) || className.equals(Boolean[].class.getCanonicalName())) {
                Boolean[] booleans = new Boolean[values.length];
                for (int i = 0; i < values.length; i++) {
                    booleans[i] = Boolean.valueOf(values[i]);
                }
                return booleans;
            } else if (className.equals(boolean.class.getCanonicalName()) || className.equals(boolean[].class.getCanonicalName())) {
                boolean[] booleans = new boolean[values.length];
                for (int i = 0; i < values.length; i++) {
                    booleans[i] = Boolean.parseBoolean(values[i]);
                }
                return booleans;
            } else if (className.equals(Short.class.getCanonicalName()) || className.equals(Short[].class.getCanonicalName())) {
                Short[] shorts = new Short[values.length];
                for (int i = 0; i < values.length; i++) {
                    try {
                        if (values[i] != null && format != null) {
                            NumberFormat numberFormat = getNumberFormat(format);
                            shorts[i] = Short.valueOf(numberFormat.parse(values[i]).shortValue());
                        } else {
                            shorts[i] = values[i] == null ? null : Short.valueOf(values[i]);
                        }
                    } catch (NumberFormatException ex) {
                        throw new DataTypeConversionException(paramName, values[i], ex);
                    } catch (ParseException ex) {
                        throw new DataTypeConversionException(paramName, values[i], ex);
                    }
                }
                return shorts;
            } else if (className.equals(short.class.getCanonicalName()) || className.equals(short[].class.getCanonicalName())) {
                short[] shorts = new short[values.length];
                for (int i = 0; i < values.length; i++) {
                    try {
                        if (values[i] != null && format != null) {
                            NumberFormat numberFormat = getNumberFormat(format);
                            shorts[i] = numberFormat.parse(values[i]).shortValue();
                        } else {
                            shorts[i] = values[i] == null ? 0 : Short.parseShort(values[i]);
                        }
                    } catch (NumberFormatException ex) {
                        throw new DataTypeConversionException(paramName, values[i], ex);
                    } catch (ParseException ex) {
                        throw new DataTypeConversionException(paramName, values[i], ex);
                    }
                }
                return shorts;
            } else if (className.equals(Byte.class.getCanonicalName()) || className.equals(Byte[].class.getCanonicalName())) {
                Byte[] bytes = new Byte[values.length];
                for (int i = 0; i < values.length; i++) {
                    try {
                        bytes[i] = values[i] == null ? null : Byte.valueOf(values[i]);
                    } catch (NumberFormatException ex) {
                        throw new DataTypeConversionException(paramName, values[i], ex);
                    }
                }
                return bytes;
            } else if (className.equals(byte.class.getCanonicalName()) || className.equals(byte[].class.getCanonicalName())) {
                byte[] bytes = new byte[values.length];
                for (int i = 0; i < values.length; i++) {
                    try {
                        bytes[i] = values[i] == null ? 0 : Byte.parseByte(values[i]);
                    } catch (NumberFormatException ex) {
                        throw new DataTypeConversionException(paramName, values[i], ex);
                    }
                }
                return bytes;
            } else if (className.equals(Character.class.getCanonicalName()) || className.equals(Character[].class.getCanonicalName())) {
                Character[] chars = new Character[values.length];
                for (int i = 0; i < values.length; i++) {
                    chars[i] = Character.valueOf(values[i].charAt(0));
                }
                return chars;
            } else if (className.equals(char.class.getCanonicalName()) || className.equals(char[].class.getCanonicalName())) {
                char[] chars = new char[values.length];
                for (int i = 0; i < values.length; i++) {
                    chars[i] = values[i].charAt(0);
                }
                return chars;
            } else if (className.equals(Object.class.getCanonicalName()) || className.equals(Object[].class.getCanonicalName())) {
                Object[] objs = new Object[values.length];
                for (int i = 0; i < values.length; i++) {
                    objs[i] = values[i];
                }
                return objs;
            }
        }
        return null;
    }

    public static String[] convertObjectArray(Object value, FormatInfo info) throws ApplicationException {
        if (value != null) {
            Class valuesClass = value.getClass();
            String className = valuesClass.getCanonicalName();
            String format = info.getFormatPattern();
            String timeZone = info.getTimeZoneName();
            if (className.equals(String[].class.getCanonicalName())) {
                return (String[]) value;
            } else if (className.equals(Date[].class.getCanonicalName())) {
                String[] strDates = new String[((Date[]) value).length];
                for (int i = 0; i < strDates.length; i++) {
                    SimpleDateFormat dateFormat = getSimpleDateFormat(format);
                    if (timeZone != null) {
                        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
                    } else {
                        dateFormat.setTimeZone(TimeZone.getDefault());
                    }
                    strDates[i] = ((Date[]) value)[i] == null ? null : dateFormat.format(((Date[]) value)[i]);
                }
                return strDates;
            } else if (className.equals(Integer[].class.getCanonicalName())) {
                String[] strValues = new String[((Integer[]) value).length];
                for (int i = 0; i < strValues.length; i++) {
                    Integer intValue = ((Integer[]) value)[i];
                    if (format != null) {
                        NumberFormat numberFormat = getNumberFormat(format);
                        strValues[i] = intValue == null ? null : numberFormat.format(intValue);
                    } else {
                        strValues[i] = intValue == null ? null : intValue.toString();
                    }
                }
                return strValues;
            } else if (className.equals(Float[].class.getCanonicalName())) {
                String[] strValues = new String[((Float[]) value).length];
                for (int i = 0; i < strValues.length; i++) {
                    Float floatValue = ((Float[]) value)[i];
                    if (format != null) {
                        NumberFormat numberFormat = getNumberFormat(format);
                        strValues[i] = floatValue == null ? null : numberFormat.format(floatValue);
                    } else {
                        strValues[i] = floatValue == null ? null : floatValue.toString();
                    }
                }
                return strValues;
            } else if (className.equals(Long[].class.getCanonicalName())) {
                String[] strValues = new String[((Long[]) value).length];
                for (int i = 0; i < strValues.length; i++) {
                    Long longValue = ((Long[]) value)[i];
                    if (format != null) {
                        NumberFormat numberFormat = getNumberFormat(format);
                        strValues[i] = longValue == null ? null : numberFormat.format(longValue);
                    } else {
                        strValues[i] = longValue == null ? null : longValue.toString();
                    }
                }
                return strValues;
            } else if (className.equals(Double[].class.getCanonicalName())) {
                String[] strValues = new String[((Double[]) value).length];
                for (int i = 0; i < strValues.length; i++) {
                    Double doubleValue = ((Double[]) value)[i];
                    if (format != null) {
                        NumberFormat numberFormat = getNumberFormat(format);
                        strValues[i] = doubleValue == null ? null : numberFormat.format(doubleValue);
                    } else {
                        strValues[i] = doubleValue == null ? null : doubleValue.toString();
                    }
                }
                return strValues;
            } else if (className.equals(Short[].class.getCanonicalName())) {
                String[] strValues = new String[((Short[]) value).length];
                for (int i = 0; i < strValues.length; i++) {
                    Short shortValue = ((Short[]) value)[i];
                    if (format != null) {
                        NumberFormat numberFormat = getNumberFormat(format);
                        strValues[i] = shortValue == null ? null : numberFormat.format(shortValue);
                    } else {
                        strValues[i] = shortValue == null ? null : shortValue.toString();
                    }
                }
                return strValues;
            } else if (className.equals(Byte[].class.getCanonicalName())) {
                String[] strValues = new String[((Byte[]) value).length];
                for (int i = 0; i < strValues.length; i++) {
                    Byte byteValue = ((Byte[]) value)[i];
                    if (format != null) {
                        NumberFormat numberFormat = getNumberFormat(format);
                        strValues[i] = byteValue == null ? null : numberFormat.format(byteValue);
                    } else {
                        strValues[i] = byteValue == null ? null : byteValue.toString();
                    }
                }
                return strValues;
            } else if (className.equals(Boolean[].class.getCanonicalName())) {
                String[] strValues = new String[((Boolean[]) value).length];
                for (int i = 0; i < strValues.length; i++) {
                    strValues[i] = ((Boolean[]) value)[i] == null ? null : (((Boolean[]) value)[i]).toString();
                }
                return strValues;
            } else if (className.equals(Character[].class.getCanonicalName())) {
                String[] strValues = new String[((Character[]) value).length];
                for (int i = 0; i < strValues.length; i++) {
                    strValues[i] = ((Character[]) value)[i] == null ? null : (((Character[]) value)[i]).toString();
                }
                return strValues;
            } else if (className.equals(int[].class.getCanonicalName())) {
                int[] preValues = int[].class.cast(value);
                String[] strValues = new String[preValues.length];
                for (int i = 0; i < strValues.length; i++) {
                    if (format != null) {
                        NumberFormat numberFormat = getNumberFormat(format);
                        strValues[i] = numberFormat.format(preValues[i]);
                    } else {
                        strValues[i] = String.valueOf(preValues[i]);
                    }
                }
                return strValues;
            } else if (className.equals(float[].class.getCanonicalName())) {
                float[] preValues = float[].class.cast(value);
                String[] strValues = new String[preValues.length];
                for (int i = 0; i < strValues.length; i++) {
                    if (format != null) {
                        NumberFormat numberFormat = getNumberFormat(format);
                        strValues[i] = numberFormat.format(preValues[i]);
                    } else {
                        strValues[i] = String.valueOf(preValues[i]);
                    }
                }
                return strValues;
            } else if (className.equals(long[].class.getCanonicalName())) {
                long[] preValues = long[].class.cast(value);
                String[] strValues = new String[preValues.length];
                for (int i = 0; i < strValues.length; i++) {
                    if (format != null) {
                        NumberFormat numberFormat = getNumberFormat(format);
                        strValues[i] = numberFormat.format(preValues[i]);
                    } else {
                        strValues[i] = String.valueOf(preValues[i]);
                    }
                }
                return strValues;
            } else if (className.equals(double[].class.getCanonicalName())) {
                double[] preValues = double[].class.cast(value);
                String[] strValues = new String[preValues.length];
                for (int i = 0; i < strValues.length; i++) {
                    if (format != null) {
                        NumberFormat numberFormat = getNumberFormat(format);
                        strValues[i] = numberFormat.format(preValues[i]);
                    } else {
                        strValues[i] = String.valueOf(preValues[i]);
                    }
                }
                return strValues;
            } else if (className.equals(short[].class.getCanonicalName())) {
                short[] preValues = short[].class.cast(value);
                String[] strValues = new String[preValues.length];
                for (int i = 0; i < strValues.length; i++) {
                    if (format != null) {
                        NumberFormat numberFormat = getNumberFormat(format);
                        strValues[i] = numberFormat.format(preValues[i]);
                    } else {
                        strValues[i] = String.valueOf(preValues[i]);
                    }
                }
                return strValues;
            } else if (className.equals(byte[].class.getCanonicalName())) {
                byte[] preValues = byte[].class.cast(value);
                String[] strValues = new String[preValues.length];
                for (int i = 0; i < strValues.length; i++) {
                    if (format != null) {
                        NumberFormat numberFormat = getNumberFormat(format);
                        strValues[i] = numberFormat.format(preValues[i]);
                    } else {
                        strValues[i] = String.valueOf(preValues[i]);
                    }
                }
                return strValues;
            } else if (className.equals(boolean[].class.getCanonicalName())) {
                boolean[] preValues = boolean[].class.cast(value);
                String[] strValues = new String[preValues.length];
                for (int i = 0; i < strValues.length; i++) {
                    strValues[i] = String.valueOf(preValues[i]);
                }
                return strValues;
            } else if (className.equals(char[].class.getCanonicalName())) {
                char[] preValues = char[].class.cast(value);
                String[] strValues = new String[preValues.length];
                for (int i = 0; i < strValues.length; i++) {
                    strValues[i] = String.valueOf(preValues[i]);
                }
                return strValues;
            } else if (className.equals(Object[].class.getCanonicalName())) {
                Object[] preValues = Object[].class.cast(value);
                String[] strValues = new String[preValues.length];
                for (int i = 0; i < strValues.length; i++) {
                    strValues[i] = preValues[i].toString();
                }
                return strValues;
            }
        }
        return new String[0];
    }

    public static String convertObject(Object value, FormatInfo info) throws ApplicationException {
        String strValue = "";
        if (value != null) {
            Class valueClass = value.getClass();
            String className = valueClass.getCanonicalName();
            String formatPattern = info != null ? info.getFormatPattern() : null;
            String timeZone = info != null ? info.getTimeZoneName() : null;
            if (className.equals(String.class.getCanonicalName())) {
                strValue = (String) value;
            } else if (className.equals(Date.class.getCanonicalName())) {
                SimpleDateFormat dateFormat = getSimpleDateFormat(formatPattern);
                if (timeZone != null) {
                    dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
                } else {
                    dateFormat.setTimeZone(TimeZone.getDefault());
                }
                strValue = dateFormat.format((Date) value);
            } else if (className.equals(int.class.getCanonicalName())) {
                int preValue = int.class.cast(value);
                if (formatPattern != null) {
                    NumberFormat numberFormat = getNumberFormat(formatPattern);
                    strValue = numberFormat.format(preValue);
                } else {
                    strValue = String.valueOf(preValue);
                }
            } else if (className.equals(float.class.getCanonicalName())) {
                float preValue = float.class.cast(value);
                if (formatPattern != null) {
                    NumberFormat numberFormat = getNumberFormat(formatPattern);
                    strValue = numberFormat.format(preValue);
                } else {
                    strValue = String.valueOf(preValue);
                }
            } else if (className.equals(double.class.getCanonicalName())) {
                double preValue = double.class.cast(value);
                if (formatPattern != null) {
                    NumberFormat numberFormat = getNumberFormat(formatPattern);
                    strValue = numberFormat.format(preValue);
                } else {
                    strValue = String.valueOf(preValue);
                }
            } else if (className.equals(long.class.getCanonicalName())) {
                long preValue = long.class.cast(value);
                if (formatPattern != null) {
                    NumberFormat numberFormat = getNumberFormat(formatPattern);
                    strValue = numberFormat.format(preValue);
                } else {
                    strValue = String.valueOf(preValue);
                }
            } else if (className.equals(short.class.getCanonicalName())) {
                short preValue = short.class.cast(value);
                if (formatPattern != null) {
                    NumberFormat numberFormat = getNumberFormat(formatPattern);
                    strValue = numberFormat.format(preValue);
                } else {
                    strValue = String.valueOf(preValue);
                }
            } else if (className.equals(byte.class.getCanonicalName())) {
                byte preValue = byte.class.cast(value);
                if (formatPattern != null) {
                    NumberFormat numberFormat = getNumberFormat(formatPattern);
                    strValue = numberFormat.format(preValue);
                } else {
                    strValue = String.valueOf(preValue);
                }
            } else if (className.equals(boolean.class.getCanonicalName())) {
                boolean preValue = boolean.class.cast(value);
                strValue = String.valueOf(preValue);
            } else if (className.equals(char.class.getCanonicalName())) {
                char preValue = char.class.cast(value);
                strValue = String.valueOf(preValue);
            } else if (className.equals(Integer.class.getCanonicalName())) {
                if (formatPattern != null) {
                    NumberFormat numberFormat = getNumberFormat(formatPattern);
                    strValue = numberFormat.format(value);
                } else {
                    strValue = ((Integer) value).toString();
                }
            } else if (className.equals(Float.class.getCanonicalName())) {
                if (formatPattern != null) {
                    NumberFormat numberFormat = getNumberFormat(formatPattern);
                    strValue = numberFormat.format(value);
                } else {
                    strValue = ((Float) value).toString();
                }
            } else if (className.equals(Double.class.getCanonicalName())) {
                if (formatPattern != null) {
                    NumberFormat numberFormat = getNumberFormat(formatPattern);
                    strValue = numberFormat.format(value);
                } else {
                    strValue = ((Double) value).toString();
                }
            } else if (className.equals(Long.class.getCanonicalName())) {
                if (formatPattern != null) {
                    NumberFormat numberFormat = getNumberFormat(formatPattern);
                    strValue = numberFormat.format(value);
                } else {
                    strValue = ((Long) value).toString();
                }
            } else if (className.equals(Short.class.getCanonicalName())) {
                if (formatPattern != null) {
                    NumberFormat numberFormat = getNumberFormat(formatPattern);
                    strValue = numberFormat.format(value);
                } else {
                    strValue = ((Short) value).toString();
                }
            } else if (className.equals(Byte.class.getCanonicalName())) {
                if (formatPattern != null) {
                    NumberFormat numberFormat = getNumberFormat(formatPattern);
                    strValue = numberFormat.format(value);
                } else {
                    strValue = ((Byte) value).toString();
                }
            } else if (className.equals(Boolean.class.getCanonicalName())) {
                strValue = ((Boolean) value).toString();
            } else if (className.equals(Character.class.getCanonicalName())) {
                strValue = ((Character) value).toString();
            } else if (className.equals(Object.class.getCanonicalName())) {
                strValue = value.toString();
            }
        }
        return strValue;
    }

    public static Object convertString(String strValue, FormatInfo info, String className) throws ParseException, ApplicationException {
        String format = info.getFormatPattern();
        String timezone = info.getTimeZoneName();
        if (className.equalsIgnoreCase(String.class.getCanonicalName())) {
            return strValue;
        } else if (className.equalsIgnoreCase(Date.class.getCanonicalName())) {
            SimpleDateFormat dateFormat = getSimpleDateFormat(format);
            if (timezone != null) {
                dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
            } else {
                dateFormat.setTimeZone(TimeZone.getDefault());
            }
            return dateFormat.parse(strValue);
        } else if (className.equalsIgnoreCase(Integer.class.getCanonicalName())) {
            if (format != null) {
                NumberFormat numberFormat = getNumberFormat(format);
                return Integer.valueOf(numberFormat.parse(strValue).intValue());
            } else {
                return Integer.valueOf(strValue);
            }
        } else if (className.equalsIgnoreCase(int.class.getCanonicalName())) {
            if (format != null) {
                NumberFormat numberFormat = getNumberFormat(format);
                return numberFormat.parse(strValue).intValue();
            } else {
                return Integer.parseInt(strValue);
            }
        } else if (className.equalsIgnoreCase(Long.class.getCanonicalName())) {
            if (format != null) {
                NumberFormat numberFormat = getNumberFormat(format);
                return Long.valueOf(numberFormat.parse(strValue).longValue());
            } else {
                return Long.valueOf(strValue);
            }
        } else if (className.equalsIgnoreCase(long.class.getCanonicalName())) {
            if (format != null) {
                NumberFormat numberFormat = getNumberFormat(format);
                return numberFormat.parse(strValue).longValue();
            } else {
                return Long.parseLong(strValue);
            }
        } else if (className.equalsIgnoreCase(Float.class.getCanonicalName())) {
            if (format != null) {
                NumberFormat numberFormat = getNumberFormat(format);
                return Float.valueOf(numberFormat.parse(strValue).floatValue());
            } else {
                return Float.valueOf(strValue);
            }
        } else if (className.equalsIgnoreCase(float.class.getCanonicalName())) {
            if (format != null) {
                NumberFormat numberFormat = getNumberFormat(format);
                return numberFormat.parse(strValue).floatValue();
            } else {
                return Float.parseFloat(strValue);
            }
        } else if (className.equalsIgnoreCase(Double.class.getCanonicalName())) {
            if (format != null) {
                NumberFormat numberFormat = getNumberFormat(format);
                return Double.valueOf(numberFormat.parse(strValue).doubleValue());
            } else {
                return Double.valueOf(strValue);
            }
        } else if (className.equalsIgnoreCase(double.class.getCanonicalName())) {
            if (format != null) {
                NumberFormat numberFormat = getNumberFormat(format);
                return numberFormat.parse(strValue).doubleValue();
            } else {
                return Double.parseDouble(strValue);
            }
        } else if (className.equalsIgnoreCase(Boolean.class.getCanonicalName())) {
            return Boolean.valueOf(strValue);
        } else if (className.equalsIgnoreCase(boolean.class.getCanonicalName())) {
            return Boolean.parseBoolean(strValue);
        } else if (className.equalsIgnoreCase(Character.class.getCanonicalName())) {
            return Character.valueOf(strValue.charAt(0));
        } else if (className.equalsIgnoreCase(char.class.getCanonicalName())) {
            return strValue.charAt(0);
        } else if (className.equalsIgnoreCase(Short.class.getCanonicalName())) {
            if (format != null) {
                NumberFormat numberFormat = getNumberFormat(format);
                return Short.valueOf(numberFormat.parse(strValue).shortValue());
            } else {
                return Short.valueOf(strValue);
            }
        } else if (className.equalsIgnoreCase(short.class.getCanonicalName())) {
            if (format != null) {
                NumberFormat numberFormat = getNumberFormat(format);
                return numberFormat.parse(strValue).shortValue();
            } else {
                return Short.parseShort(strValue);
            }
        } else if (className.equalsIgnoreCase(Byte.class.getCanonicalName())) {
            if (format != null) {
                NumberFormat numberFormat = getNumberFormat(format);
                return Byte.valueOf(numberFormat.parse(strValue).byteValue());
            } else {
                return Byte.valueOf(strValue);
            }
        } else if (className.equalsIgnoreCase(byte.class.getCanonicalName())) {
            if (format != null) {
                NumberFormat numberFormat = getNumberFormat(format);
                return numberFormat.parse(strValue).byteValue();
            } else {
                return Byte.parseByte(strValue);
            }
        } else {
            return strValue;
        }
    }
}
