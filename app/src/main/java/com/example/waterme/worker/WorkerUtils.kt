
package com.example.waterme.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.waterme.CHANNEL_ID
import com.example.waterme.MainActivity
import com.example.waterme.NOTIFICATION_ID
import com.example.waterme.NOTIFICATION_TITLE
import com.example.waterme.R
import com.example.waterme.REQUEST_CODE
import com.example.waterme.VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
import com.example.waterme.VERBOSE_NOTIFICATION_CHANNEL_NAME

fun makePlantReminderNotification(
    message: String,
    context: Context
) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // Tạo kênh thông báo (Notification Channel) cho Android 8.0 trở lên
        val importance = NotificationManager.IMPORTANCE_HIGH // Mức độ ưu tiên cao
        val channel = NotificationChannel(
            CHANNEL_ID, // ID của kênh thông báo
            VERBOSE_NOTIFICATION_CHANNEL_NAME, // Tên hiển thị của kênh
            importance
        )
        channel.description = VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION // Mô tả kênh

        // Lấy hệ thống NotificationManager để đăng ký kênh
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        // Tạo kênh nếu chưa có
        notificationManager?.createNotificationChannel(channel)
    }

    // Tạo intent khi người dùng nhấn vào thông báo sẽ mở MainActivity
    val pendingIntent: PendingIntent = createPendingIntent(context)


    // Tạo builder để cấu hình thông báo
    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_foreground)       // Icon hiển thị
        .setContentTitle(NOTIFICATION_TITLE)                   // Tiêu đề thông báo
        .setContentText(message)                               // Nội dung (vd: “Đã đến lúc tưới cây…”)
        .setPriority(NotificationCompat.PRIORITY_HIGH)         // Ưu tiên cao
        .setVibrate(LongArray(0))                              // Không rung
        .setContentIntent(pendingIntent)                       // Intent được gọi khi nhấn thông báo
        .setAutoCancel(true)                                   // Tự tắt thông báo khi đã nhấn

    // Hiển thị thông báo
    NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
}

fun createPendingIntent(appContext: Context): PendingIntent {
    // Tạo intent mở MainActivity
    val intent = Intent(appContext, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    var flags = PendingIntent.FLAG_UPDATE_CURRENT
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        flags = flags or PendingIntent.FLAG_IMMUTABLE
    }

    return PendingIntent.getActivity(
        appContext,
        REQUEST_CODE,
        intent,
        flags
    )
}
