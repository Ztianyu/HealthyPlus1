package com.zty.healthy.healthyplus.utils;

import android.content.Context;

/**
 * 软件参数设置器 默认全部使用String类型存储数据，如有其他类型数据请自行转换。
 */
public class SharedPrefUtils {

    /**
     * SharedPreferences xml 名称
     */
    private static String APP_SHARED_STR = "HealthyPlusPref";

    public static String USER_ID = "userId";//用户id
    public static String FAMILY_NUM = "familyNumber";//家庭编号
    public static String TAKEN_ID = "tokenId";//
    public static String USER_PHONE = "phone";

    public static String CURRENT_OLD_MAN = "currentOldManId";//当前老人id
    public static String CURRENT_OLD_MAN_HEIGHT = "currentOldManHeight";//当前老人身高

    /**
     * 从SharedPreferences 获取一个boolean值，默认为false
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        return context.getSharedPreferences(APP_SHARED_STR,
                Context.MODE_PRIVATE).getBoolean(key, false);
    }

    /**
     * 设置 一个boolean 值到SharedPreferences
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static synchronized void setBoolean(Context context, String key,
                                               boolean value) {
        context.getSharedPreferences(APP_SHARED_STR, Context.MODE_PRIVATE)
                .edit().putBoolean(key, value).commit();
    }

    /**
     * 从SharedPreferences 获取一个String值，默认为null
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        return context.getSharedPreferences(APP_SHARED_STR,
                Context.MODE_PRIVATE).getString(key, null);
    }

    /**
     * 设置 一个String 值到SharedPreferences
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static synchronized void setString(Context context, String key,
                                              String value) {
        context.getSharedPreferences(APP_SHARED_STR, Context.MODE_PRIVATE)
                .edit().putString(key, value).commit();
    }

    /**
     * 更新 一个String 值到SharedPreferences
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static synchronized void updateString(Context context, String key,
                                                 String value) {
        context.getSharedPreferences(APP_SHARED_STR, Context.MODE_PRIVATE)
                .edit().putString(key, value).apply();
    }

    /**
     * 从SharedPreferences 获取一个int值，默认为null
     *
     * @param context
     * @param key
     * @return
     */
    public static int getInt(Context context, String key) {
        return context.getSharedPreferences(APP_SHARED_STR,
                Context.MODE_PRIVATE).getInt(key, ~0);
    }

    /**
     * 设置 一个int 值到SharedPreferences
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static synchronized void setInt(Context context, String key,
                                           int value) {
        context.getSharedPreferences(APP_SHARED_STR, Context.MODE_PRIVATE)
                .edit().putInt(key, value).commit();
    }


}
