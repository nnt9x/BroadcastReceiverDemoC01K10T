package com.bkacad.nnt.broadcastreceiverdemoc01k10t;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvStatus;
    private ConstraintLayout rootView;

    private MyReceiver myReceiver;
    private IntentFilter intentFilter;

    private Notification notification;
    private NotificationManagerCompat notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tvStatus);
        rootView = findViewById(R.id.rootView);

        notificationManager = NotificationManagerCompat.from(this);
        // Tạo trước notification
        notification = new NotificationCompat.Builder(this,"channel 1")
                .setContentTitle("Thông báo")
                .setSmallIcon(R.drawable.ic_baseline_warning_24)
                .setContentText("Ứng dụng chỉ hoạt động khi có wifi")
                .build();

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

            @Override
            public void wifiOn() {
                    notificationManager.cancel(1);
//                Toast.makeText(MainActivity.this,"Wifi bật",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void wifiOff() {
                // wifi tắt -> hiển thị thông báo
                notificationManager.notify(1, notification);
//                Toast.makeText(MainActivity.this,"Wifi tắt",Toast.LENGTH_SHORT).show();
            }
        };
        // Tạo ra intent filter ->đk sự kiện sẽ lắng nghe
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
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