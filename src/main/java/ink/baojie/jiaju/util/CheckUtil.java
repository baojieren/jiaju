package ink.baojie.jiaju.util;


import ink.baojie.jiaju.base.exception.CustomError;
import ink.baojie.jiaju.base.exception.CustomException;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {

    private static final int CHECK_ERR_CODE_START = CustomError.SYS_ERR_CODE_START + 1000;

    /**
     * 检查参数是否为空，若为空则抛出异常
     *
     * @param paramName  参数名
     * @param paramValue 参数值
     * @throws CustomException
     */
    public static void checkEmpty(String paramName, String paramValue) throws CustomException {
        if (StringUtils.isEmpty(paramValue)) {
            throw new CustomException(new CustomError(
                    CHECK_ERR_CODE_START + 1,
                    "【" + paramName + "】不能为空"));
        }
    }

    /**
     * 检查参数是否为空，若为空则抛出异常
     *
     * @param paramName  参数名
     * @param paramValue 参数值
     * @throws CustomException
     */
    public static void checkEmpty(String paramName, Object paramValue) throws CustomException {
        if (paramValue == null) {
            throw new CustomException(new CustomError(
                    CHECK_ERR_CODE_START + 2,
                    "【" + paramName + "】不能为空"));
        }
    }

    /**
     * 检查参数是否大于等于最小值，若不是则抛出异常
     *
     * @param paramName  参数名
     * @param paramValue 参数值
     * @param minValue   有效范围内最小值（包含）
     * @throws CustomException
     */
    public static void checkMin(String paramName, float paramValue, float minValue) throws CustomException {
        if (paramValue < minValue) {
            throw new CustomException(new CustomError(
                    CHECK_ERR_CODE_START + 3,
                    "【" + paramName + "】不能小于" + minValue));
        }
    }

    /**
     * 检查参数是否小于于等于最大值，若不是则抛出异常
     *
     * @param paramName  参数名
     * @param paramValue 参数值
     * @param maxValue   有效范围内最大值（包含）
     * @throws CustomException
     */
    public static void checkMax(String paramName, float paramValue, float maxValue) throws CustomException {
        if (paramValue > maxValue) {
            throw new CustomException(new CustomError(
                    CHECK_ERR_CODE_START + 4,
                    "【" + paramName + "】不能大于" + maxValue));
        }
    }

    /**
     * 检查参数是否在最小值和最大值之间，若不是则抛出异常
     *
     * @param paramName  参数名
     * @param paramValue 参数值
     * @param minValue   有效范围内最小值（包含）
     * @param maxValue   有效范围内最大值（包含）
     * @throws CustomException
     */
    public static void checkRange(String paramName, float paramValue, float minValue, float maxValue) throws CustomException {
        if (paramValue < minValue || paramValue > maxValue) {
            throw new CustomException(new CustomError(
                    CHECK_ERR_CODE_START + 5,
                    "【" + paramName + "】必须限定在[" + minValue + ", " + maxValue + "]之间"));
        }
    }

    /**
     * 检查参数是否为数字（不含小数点），若不是则抛出异常
     *
     * @param paramName  参数名
     * @param paramValue 参数值
     * @throws CustomException
     */
    public static void checkNumber(String paramName, String paramValue) throws CustomException {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher matcher = pattern.matcher(paramValue);
        if (StringUtils.isEmpty(paramValue) || !matcher.matches()) {
            throw new CustomException(new CustomError(
                    CHECK_ERR_CODE_START + 6,
                    "【" + paramName + "】只能为数字"));
        }
    }

    /**
     * 检查参数是否为数字（可含小数点），若不是则抛出异常
     *
     * @param paramName  参数名
     * @param paramValue 参数值
     * @throws CustomException
     */
    public static void checkFloat(String paramName, String paramValue) throws CustomException {
        try {
            Float.valueOf(paramValue);
        } catch (Exception e) {
            throw new CustomException(new CustomError(
                    CHECK_ERR_CODE_START + 7,
                    "【" + paramName + "】只能为数字"));
        }
    }

    /**
     * 检查参数是否为手机号，若不是则抛出异常
     *
     * @param paramName  参数名
     * @param paramValue 参数值
     * @throws CustomException
     */
    public static void checkPhone(String paramName, String paramValue) throws CustomException {
        if (StringUtils.isEmpty(paramValue) || paramValue.length() != 11) {
            throw new CustomException(new CustomError(
                    CHECK_ERR_CODE_START + 8,
                    "【" + paramName + "】必须是11位数字"));
        }
    }

    /**
     * 检查参数是否在最小值和最大值之间，若不是则抛出异常
     *
     * @param paramName  参数名
     * @param paramValue 参数值
     * @param minValue   有效范围内最小值（包含）
     * @param maxValue   有效范围内最大值（包含）
     * @throws CustomException
     */
    public static void checkIntRange(String paramName, int paramValue, int minValue, int maxValue) throws CustomException {
        if (paramValue < minValue || paramValue > maxValue) {
            throw new CustomException(new CustomError(
                    CHECK_ERR_CODE_START + 9,
                    "【" + paramName + "】必须限定在[" + minValue + ", " + maxValue + "]之间"));
        }
    }

    /**
     * 检查字符串长度
     */
    public static void checkStrLen(String paramName, String paramValue, int size) {
        if (StringUtils.isEmpty(paramValue) || paramValue.length() < size) {
            throw new CustomException(new CustomError(CHECK_ERR_CODE_START + 10, "【" + paramName + "】必须是" + size + "位"));
        }
    }

}
