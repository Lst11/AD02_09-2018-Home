package by.it_academy.ad02_09_2018_home.hw5;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import by.it_academy.ad02_09_2018_home.R;

public class Lesson5Activity extends Activity {
    private TextView networkStatus;
    private BroadcastReceiver myBroadcastReceicer = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
//            final String action = intent.getAction();
//            if (action.equals(WifiManager.WIFI_STATE_CHANGED_ACTION))
            if (intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0) == WifiManager.WIFI_STATE_ENABLED) {
                networkStatus.setText("Wifi is available");
                Log.d("Network  Available", "YES");
            } else {
                networkStatus.setText("Wifi is not available");
                Log.d("Network Available", "NO");
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson5);
        networkStatus = (TextView) findViewById(R.id.networkStatus);

        ClockView clockView = new ClockView(this);
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        layout.addView(clockView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(myBroadcastReceicer, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myBroadcastReceicer);
    }
}


