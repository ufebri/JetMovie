package com.raylabs.jetmovie.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.raylabs.jetmovie.BuildConfig
import com.raylabs.jetmovie.R
import com.raylabs.jetmovie.domain.model.NotificationData

object NotificationHelper {
    fun showNotification(context: Context, notificationData: NotificationData) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(
                    notificationData.channelID,
                    "Reminders",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            notificationManager.createNotificationChannel(channel)
        }

        val notification =
            NotificationCompat.Builder(context, notificationData.channelID)
                .setSmallIcon(R.drawable.jetmovieicon)
                .setContentTitle(notificationData.title)
                .setContentText(notificationData.description)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Jika ada gambar URL, unduh dan set ke notifikasi
        val backDropPath = getBitmapFromURLWithGlide(context, "${BuildConfig.IMGLINK}${notificationData.backDropPath}")
        val posterPath = getBitmapFromURLWithGlide(context, "${BuildConfig.IMGLINK}${notificationData.posterPath}")
        notification.apply {
            setLargeIcon(posterPath)
            setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(backDropPath)  // Menampilkan gambar yang diunduh
                    .bigLargeIcon(null)  // Menyembunyikan gambar kecil di bagian atas
            )
        }


        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        ) {
            NotificationManagerCompat.from(context)
                .notify(notificationData.title.hashCode(), notification.build())
        }
    }

    private fun getBitmapFromURLWithGlide(context: Context, imageUrl: String): Bitmap? {
        return try {
            // Gunakan Glide untuk mengunduh gambar secara asynchronous
            val futureTarget = Glide.with(context)
                .asBitmap()
                .load(imageUrl)
                .submit()

            futureTarget.get() // Mendapatkan hasil gambar Bitmap
        } catch (e: Exception) {
            e.printStackTrace()
            BitmapFactory.decodeResource(
                context.resources,
                R.drawable.ic_broken_image
            ) // fallback image jika gagal
        }
    }
}