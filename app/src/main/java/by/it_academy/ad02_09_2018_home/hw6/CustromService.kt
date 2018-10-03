package by.it_academy.ad02_09_2018_home.hw6

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class CustromService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("AAA", "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder {
        Log.e("AAA", "onBind")
        return Binder();
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e("AAA", "onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.e("AAA", "onDestroy")
        super.onDestroy()
    }

}