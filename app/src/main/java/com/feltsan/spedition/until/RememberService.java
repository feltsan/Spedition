package com.feltsan.spedition.until;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


/**
 * Created by john on 11.10.15.
 */
public class RememberService extends Service {

    Alarm alarm = new Alarm();


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        alarm.SetAlarm(this);
        return START_STICKY;
    }
    @Override
    public void onStart(Intent intent, int startId)
    {
        alarm.SetAlarm(this);
    }
}
