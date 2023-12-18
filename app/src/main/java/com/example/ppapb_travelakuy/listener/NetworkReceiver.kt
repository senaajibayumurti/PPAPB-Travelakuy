package com.example.ppapb_travelakuy.listener

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class NetworkReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && intent != null) {
            if (ConnectivityManager.CONNECTIVITY_ACTION == intent.action) {
                val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val networkInfo = connectivityManager.activeNetworkInfo
                val isConnected = networkInfo?.isConnectedOrConnecting == true
                Log.d("NetworkReceiver", "isConnected: $isConnected")
                val broadcastIntent = Intent("networkStatus")
                broadcastIntent.putExtra("isConnected", isConnected)
                LocalBroadcastManager.getInstance(context).sendBroadcast(broadcastIntent)
            }
        }
    }
}