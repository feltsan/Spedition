package com.feltsan.spedition.until;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telephony.ServiceState;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by john on 17.10.15.
 */
public class ConnectivityChangeReceiver   extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int state = intent.getExtras().getInt("state");
        if(state == ServiceState.STATE_IN_SERVICE && !ShPrManager.getConfirm(context)){
            ShPrManager.setConfirm(context,true);
            context.startService(new Intent(context, GetInvites.class));
        }
    }


}
