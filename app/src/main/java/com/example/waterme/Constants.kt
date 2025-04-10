package com.example.waterme

// Hằng số kênh thông báo

// Tên của kênh thông báo cho các thông báo chi tiết của công việc chạy nền
val VERBOSE_NOTIFICATION_CHANNEL_NAME: CharSequence = "Thông báo chi tiết của WorkManager"

// Mô tả của kênh thông báo cho các thông báo chi tiết của công việc chạy nền
const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION = "Hiển thị thông báo mỗi khi công việc bắt đầu"

// Tiêu đề của thông báo cho các công việc chạy nền
val NOTIFICATION_TITLE: CharSequence = "Tưới cây nào!"

// ID của kênh thông báo dành cho thông báo chi tiết
const val CHANNEL_ID = "VERBOSE_NOTIFICATION"

// ID của thông báo chi tiết
const val NOTIFICATION_ID = 1

// Mã yêu cầu dùng cho PendingIntent
const val REQUEST_CODE = 0

// Thời gian nhắc nhở tưới cây
const val FIVE_SECONDS: Long = 5         // 5 giây
const val ONE_DAY: Long = 1              // 1 ngày
const val SEVEN_DAYS: Long = 7           // 7 ngày (1 tuần)
const val THIRTY_DAYS: Long = 30         // 30 ngày (1 tháng)
