package com.feltsan.spedition.until;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


/**
 * Created by john on 11.10.15.
 */
public class BootReceiver extends BroadcastReceiver {
    Alarm alarm = new Alarm();
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())&& ShPrManager.getRunService(context)) {
            alarm.SetAlarm(context);
        }
    }
}
