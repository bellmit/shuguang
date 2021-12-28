package com.sofn.ducss.util;

import com.sofn.ducss.exception.SofnException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimal 工具
 */
@Slf4j
public class BigDecimalUtil {

    /**
     * 如果BigDecimal工具为空就返回0
     *
     * @param bigDecimal BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal valueIsNull(BigDecimal bigDecimal) {
        return bigDecimal == null ? new BigDecimal("0") : bigDecimal;
    }

    /**
     * BigDecimal保留2位小数
     *
     * @param bigDecimal BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal setScale2(BigDecimal bigDecimal) {
        return bigDecimal == null ? new BigDecimal("0") : bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 将数值除10000
     *
     * @param bigDecimal BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal getTenThousand(BigDecimal bigDecimal) {
        return bigDecimal == null ? BigDecimal.ZERO : bigDecimal.divide(new BigDecimal("10000"), 10, BigDecimal.ROUND_HALF_UP);
    }


    /**
     * 将数值除10000
     *
     * @param bigDecimal    BigDecimal
     * @param decimalNumber 小数位数
     * @return BigDecimal
     */
    public static BigDecimal getTenThousand(BigDecimal bigDecimal, int decimalNumber) {
        return bigDecimal == null ? BigDecimal.ZERO : bigDecimal.divide(new BigDecimal("10000"), decimalNumber, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 将数值除100000000
     *
     * @param bigDecimal BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal getBillion(BigDecimal bigDecimal) {
        return bigDecimal == null ? BigDecimal.ZERO : bigDecimal.divide(new BigDecimal("100000000"), 2, RoundingMode.HALF_UP);
    }

    /**
     * 将字符串转为BigDecimal
     *
     * @param str 字符串
     * @return BigDecimal
     */
    public static BigDecimal getBigDecimalByStr(String str) {
        if (StringUtils.isBlank(str)) {
            return BigDecimal.ZERO;
        }
        try {
            return new BigDecimal(str);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("字符串：{}转BigDecimal失败", str);
            return BigDecimal.ZERO;
        }

    }

    /**
     * @param molecular   分子
     * @param denominator 分母
     * @return 百分比.留一位小数
     */
    public static BigDecimal dev(BigDecimal molecular, BigDecimal denominator) {
        if (molecular == null) {
            molecular = BigDecimal.ZERO;
        }
        if (denominator == null || denominator.compareTo(new BigDecimal(0)) == 0) {
            return BigDecimal.ZERO;
        }
        return molecular.multiply(new BigDecimal(100)).divide(denominator, 1, RoundingMode.HALF_UP);

    }

    /**
     * 公式   value1/value2 * 100
     *
     * @param value1        value1
     * @param value2        value2
     * @param decimalNumber 保留几位小数
     * @return BigDecimal
     */
    public static BigDecimal getProportion(Object value1, Object value2, int decimalNumber) {
        BigDecimal bigDecimal;
        BigDecimal bigDecimal2;
        try {
            bigDecimal = new BigDecimal(value1.toString());
            bigDecimal2 = new BigDecimal(value2.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException("无法将obj转为BigDecimal");
        }
        BigDecimal divide = bigDecimal.divide(bigDecimal2, 10, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));
        return divide.setScale(decimalNumber, BigDecimal.ROUND_HALF_UP);

    }

    /**
     * 获取亿吨
     * @param value   值
     * @return   BigDecimal
     */
    public static BigDecimal get100MillionTons(BigDecimal value) {
        return  value.divide(new BigDecimal(100000000), 10, BigDecimal.ROUND_HALF_UP);

    }


    /**
     * 获取亿吨并保留2位小数
     * @param value   值
     * @return   BigDecimal
     */
    public static BigDecimal get100MillionTonsAndScale2(BigDecimal value) {
        return  value.divide(new BigDecimal(100000000), 2, BigDecimal.ROUND_HALF_UP);
    }


}
