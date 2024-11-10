package id.daydream.jetmovie.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.huawei.hms.push.RemoteMessage

class MyPushReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // Tangani pesan push
        val remoteMessage = intent.getParcelableExtra<RemoteMessage>("data")
        if (remoteMessage != null) {
            Log.d("PushReceiver", "Message received: ${remoteMessage.messageId}")
        }
    }
}
