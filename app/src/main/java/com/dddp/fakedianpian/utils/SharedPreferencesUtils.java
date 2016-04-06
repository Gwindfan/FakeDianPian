package com.dddp.fakedianpian.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by Bruce J on 2016/4/5.
 */
public class SharedPreferencesUtils {
    private static final String FILE_NAME = "dianping";
    private static final String MODE_NAME = "welcome";
    private static final String CITY_NAME = "cityName";

    //Read
    public static boolean getWelcomeBoolean(Context context){
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getBoolean(MODE_NAME, true);
    }
    //Write
    public static void putWelcomeBoolean(Context context, boolean isFirst){
        Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND).edit();
        editor.putBoolean(MODE_NAME, isFirst);
        editor.commit();
    }

    public static void putCityName(Context context, String cityName){
        Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND).edit();
        editor.putString(CITY_NAME, cityName);
        editor.commit();
    }

    public static String getCityName(Context context){
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(CITY_NAME, "Select a city");
    }
}
