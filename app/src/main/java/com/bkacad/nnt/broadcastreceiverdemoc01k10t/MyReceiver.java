package com.bkacad.nnt.broadcastreceiverdemoc01k10t;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.Toast;

public abstract class MyReceiver extends BroadcastReceiver {

    public abstract void powerOn();
    public abstract void powerOff();
    public abstract void wifiOn();
    public abstract void wifiOff();

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()){
            case "android.intent.action.ACTION_POWER_CONNECTED":
                powerOn();
                break;
            case "android.intent.action.ACTION_POWER_DISCONNECTED":
                powerOff();
                break;
            case "android.net.wifi.WIFI_STATE_CHANGED":
                WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                if(wifiManager.isWifiEnabled()){
                    wifiOn();
                }
                else{
                    wifiOff();
                }
//                Toast.makeText(context, "Wifi đã bật hoặc tắt", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
