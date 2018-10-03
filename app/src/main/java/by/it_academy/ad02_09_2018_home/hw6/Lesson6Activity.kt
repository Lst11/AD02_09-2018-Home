package by.it_academy.ad02_09_2018_home.hw6

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.net.wifi.WifiManager
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import by.it_academy.ad02_09_2018_home.R


class Lesson6Activity : AppCompatActivity() {
    lateinit var sConn: ServiceConnection
    var bound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson6)

        val buttonOn = findViewById<Button>(R.id.wifiOn)
        val buttonOff = findViewById<Button>(R.id.wifiOff)

        val wifiManager: WifiManager = getApplicationContext().getSystemService(Context.WIFI_SERVICE) as WifiManager
//        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE); wifi.setWifiEnabled(false);

        buttonOn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                wifiManager.isWifiEnabled = true
            }
        })

        buttonOff.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                wifiManager.isWifiEnabled = false
            }
        })


    }

    override fun onResume() {
        super.onResume()
        var intent: Intent = object : Intent(this, CustromService::class.java) {}
        sConn = object : ServiceConnection {
            override fun onServiceConnected(className: ComponentName, binder: IBinder) {
                Log.d("AAA", "onServiceConnected")
                bound = true
            }

            override fun onServiceDisconnected(className: ComponentName) {
                Log.d("AAA", "onServiceDisconnected")
                bound = false
            }
        }
        bindService(intent, sConn, BIND_AUTO_CREATE)
//        startService(Intent(this, CustromService::class.java))
    }

    override fun onPause() {
        super.onPause()
        if (!bound) return
        unbindService(sConn)
        bound = false
//        stopService(Intent(this, CustromService::class.java))
    }
}

//                startService(Intent(getBaseContext(), CustromService::class.java))