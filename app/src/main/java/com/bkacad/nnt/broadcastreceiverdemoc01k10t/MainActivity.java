package com.bkacad.nnt.broadcastreceiverdemoc01k10t;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvStatus;
    private ConstraintLayout rootView;

    private MyReceiver myReceiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tvStatus);
        rootView = findViewById(R.id.rootView);

        // Tạo receiver
        myReceiver = new MyReceiver() {
            @Override
            public void powerOn() {
                tvStatus.setText("Đang sạc");
                rootView.setBackgroundResource(android.R.color.holo_green_light);
            }

            @Override
            public void powerOff() {
                tvStatus.setText("Rút sạc");
                rootView.setBackgroundResource(android.R.color.holo_red_light);
            }
        };
        // Tạo ra intent filter ->đk sự kiện sẽ lắng nghe
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
    }

    // Đăng kí sự kiện lắng nghe khi app run

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myReceiver,intentFilter);
    }

    // Khi activity onpause, hoac stop

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver);
    }
}