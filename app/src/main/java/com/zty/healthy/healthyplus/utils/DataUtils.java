package com.zty.healthy.healthyplus.utils;

import java.text.DecimalFormat;

/**
 * Created by zty on 2016/10/20.
 */

public class DataUtils {

    /**
     * 计算BMI
     */
    public static String getBmi(double d, double height) {
        return new DecimalFormat("0.0").format(d * 10000 / (height * height));
    }
}
