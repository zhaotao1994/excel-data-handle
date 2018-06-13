package vip.zhaotao.poi;

import java.math.BigDecimal;

/**
 * 转换工具类
 */
public class ConvertUtils {

    /**
     * Object转Character
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static Character toCharacter(Object object, Character defaultValue) {
        try {
            return object != null && toString(object) != null ? Character.valueOf(toString(object).charAt(0)) : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * Object转Character，转换失败返回null
     *
     * @param object
     * @return
     */
    public static Character toCharacter(Object object) {
        return toCharacter(object, null);
    }

    /**
     * Object转String
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static String toString(Object object, String defaultValue) {
        return (object != null) ? object.toString() : defaultValue;
    }

    /**
     * Object转String，转换失败返回null
     *
     * @param object
     * @return
     */
    public static String toString(Object object) {
        return toString(object, null);
    }

    /**
     * Object转Byte
     *
     * @param object
     * @param defaultByte
     * @return
     */
    public static Byte toByte(Object object, Byte defaultByte) {
        try {
            return (object != null) ? Byte.valueOf(toString(object)) : defaultByte;
        } catch (NumberFormatException e) {
            return defaultByte;
        }
    }

    /**
     * Object转Byte，转换失败返回null
     *
     * @param object
     * @return
     */
    public static Byte toByte(Object object) {
        return toByte(object, null);
    }

    /**
     * Object转Short
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static Short toShort(Object object, Short defaultValue) {
        try {
            return (object != null) ? Short.valueOf(toString(object)) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Object转Short，转换失败返回null
     *
     * @param object
     * @return
     */
    public static Short toShort(Object object) {
        return toShort(object, null);
    }

    /**
     * Object转Integer
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static Integer toInteger(Object object, Integer defaultValue) {
        try {
            return (object != null) ? Integer.valueOf(toString(object)) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Object转Integer，转换失败返回null
     *
     * @param object
     * @return
     */
    public static Integer toInteger(Object object) {
        return toInteger(object, null);
    }

    /**
     * Object转Long
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static Long toLong(Object object, Long defaultValue) {
        try {
            return (object != null) ? Long.valueOf(toString(object)) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Object转Long，转换失败返回null
     *
     * @param object
     * @return
     */
    public static Long toLong(Object object) {
        return toLong(object, null);
    }

    /**
     * Object转Float
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static Float toFloat(Object object, Float defaultValue) {
        try {
            return (object != null) ? Float.valueOf(toString(object)) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Object转Float，转换失败返回null
     *
     * @param object
     * @return
     */
    public static Float toFloat(Object object) {
        return toFloat(object, null);
    }

    /**
     * Object转Double
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static Double toDouble(Object object, Double defaultValue) {
        try {
            return (object != null) ? Double.valueOf(toString(object)) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Object转Double，转换失败返回null
     *
     * @param object
     * @return
     */
    public static Double toDouble(Object object) {
        return toDouble(object, null);
    }

    /**
     * Object转BigDecimal
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static BigDecimal toBigDecimal(Object object, BigDecimal defaultValue) {
        try {
            return (object != null) ? BigDecimal.valueOf(toDouble(object)) : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * Object转BigDecimal，转换失败返回null
     *
     * @param object
     * @return
     */
    public static BigDecimal toBigDecimal(Object object) {
        return toBigDecimal(object, null);
    }

    /**
     * Object转Boolean
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static Boolean toBoolean(Object object, Boolean defaultValue) {
        try {
            return (object != null) ? Boolean.valueOf(toString(object)) : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * Object转Boolean，转换失败返回null
     *
     * @param object
     * @return
     */
    public static Boolean toBoolean(Object object) {
        return toBoolean(object, null);
    }
}
