package cn.edu.scut.bookshop.util;

import java.math.BigDecimal;

public class MathUtil
{
    /**
     * @param value 浮点数
     * @param scale 要保留的小数位数
     * @return 保留小数位后的浮点数
     */
    public static double round(double value, int scale)
    {
        return new BigDecimal(value).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
