package com.feltsan.spedition.until;

import android.content.Context;
import android.content.SharedPreferences;

import com.feltsan.spedition.App;
import com.feltsan.spedition.ui.activity.AllDocumentsActivity;
import com.feltsan.spedition.ui.activity.InviteActivity;


/**
 * Created by john on 14.10.15.
 */
public class ShPrManager {

    public static void setRunService(Context context,boolean run){
        SharedPreferences preferences = context.getSharedPreferences(AllDocumentsActivity.class.getSimpleName(), Context.MODE_PRIVATE);
        preferences.edit().putBoolean(App.RUN_ID,run).commit();
    }

    public static boolean getRunService(Context context){
        SharedPreferences preferences = context.getSharedPreferences(AllDocumentsActivity.class.getSimpleName(), Context.MODE_PRIVATE);
        return  preferences.getBoolean(App.RUN_ID, false);
    }

    public static void setConfirm(Context context,boolean conf){
        SharedPreferences preferences = context.getSharedPreferences(InviteActivity.class.getSimpleName(), Context.MODE_PRIVATE);
        preferences.edit().putBoolean(App.SER_ID,conf).commit();
    }

    public static boolean getConfirm(Context context){
        SharedPreferences preferences = context.getSharedPreferences(InviteActivity.class.getSimpleName(), Context.MODE_PRIVATE);
        return  preferences.getBoolean(App.SER_ID, true);
    }

    public static void setTempConfirm(Context context,boolean conf){
        SharedPreferences preferences = context.getSharedPreferences(Sender.class.getSimpleName(), Context.MODE_PRIVATE);
        preferences.edit().putBoolean(App.TEMP_ID,conf).commit();
    }

    public static boolean getTempConfirm(Context context){
        SharedPreferences preferences = context.getSharedPreferences(Sender.class.getSimpleName(), Context.MODE_PRIVATE);
        return  preferences.getBoolean(App.TEMP_ID, true);
    }

    public static void setDownloaded(Context context,boolean conf){
        SharedPreferences preferences = context.getSharedPreferences(DateHelper.class.getSimpleName(), Context.MODE_PRIVATE);
        preferences.edit().putBoolean(App.DOWNLOAD_ID,conf).commit();
    }

    public static boolean getDownloaded(Context context){
        SharedPreferences preferences = context.getSharedPreferences(DateHelper.class.getSimpleName(), Context.MODE_PRIVATE);
        return  preferences.getBoolean(App.DOWNLOAD_ID, false);
    }

}
