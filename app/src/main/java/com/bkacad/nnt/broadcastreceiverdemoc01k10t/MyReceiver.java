package com.bkacad.nnt.broadcastreceiverdemoc01k10t;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public abstract class MyReceiver extends BroadcastReceiver {

    public abstract void powerOn();
    public abstract void powerOff();

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()){
            case "android.intent.action.ACTION_POWER_CONNECTED":
                powerOn();
//                Toast.makeText(context, "Cắm sạc", Toast.LENGTH_SHORT).show();
                break;
            case "android.intent.action.ACTION_POWER_DISCONNECTED":
                powerOff();
//                Toast.makeText(context, "Rút sạc", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
