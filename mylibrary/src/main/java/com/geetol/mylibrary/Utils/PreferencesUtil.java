package com.geetol.mylibrary.Utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;


/**
 * Copyright (©) 2014
 * <p/>
 * 配置文件读写工具类
 *
 * @author eastonc
 * @version 1.0, 14-7-28 13:43
 * @since 14-7-28
 */
public class PreferencesUtil {


    public static void putString(String keyString, String valueString) {
        if (!TextUtils.isEmpty(keyString)) {
            getDefaultSharedPreferences().edit().putString(keyString, valueString).apply();
        }
    }


    /**
     * @
     */
    private static SharedPreferences getDefaultSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(GtSdk.contexts);
    }

    /**
     * 获取String类型的键值
     */
    public static String getString(String keyString) {
        if (TextUtils.isEmpty(keyString))
            return null;
        return getDefaultSharedPreferences().getString(keyString, "");
    }

    /**
     * 获取String类型的键值
     */
    public static String getString(String keyString, String df) {
        return getDefaultSharedPreferences().getString(keyString, df);
    }


    /**
     * 清空键名为 keyString 的键值对
     */
    public static void clearKey(String keyString) {
        if (!TextUtils.isEmpty(keyString))
            getDefaultSharedPreferences().edit().remove(keyString).apply();
    }


    /**
     * 移除键名为 keyString 的键值对
     */
    public static void clearAll() {
        getDefaultSharedPreferences().edit().clear().apply();
    }

}
