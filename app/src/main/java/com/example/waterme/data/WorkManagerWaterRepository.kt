
package com.example.waterme.data

import android.content.Context
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.waterme.model.Plant
import com.example.waterme.worker.WaterReminderWorker
import java.util.concurrent.TimeUnit

class WorkManagerWaterRepository(context: Context) : WaterRepository {
    private val workManager = WorkManager.getInstance(context) // Lấy WorkManager instance

    override val plants: List<Plant> // Lấy danh sách dữ liệu
        get() = DataSource.plants

    // Hàm lên lịch nhắc nhở
    override fun scheduleReminder(duration: Long, unit: TimeUnit, plantName: String) {
        val data = Data.Builder() // Tạo một Data object để truyền vào WorkManager
        data.putString(WaterReminderWorker.nameKey, plantName) // Thêm tên cây vào Data object

        val workRequestBuilder = OneTimeWorkRequestBuilder<WaterReminderWorker>()  // Tạo một WorkRequest để thực hiện công việc nhắc nhở
            .setInitialDelay(duration, unit) // Đặt thời gian trễ ban đầu cho công việc
            .setInputData(data.build()) // Đặt dữ liệu đầu vào cho công việc
            .build()

        workManager.enqueueUniqueWork( // Đăng ký công việc với WorkManager
            plantName + duration, // Tên công việc duy nhất để tránh xung đột
            ExistingWorkPolicy.REPLACE, // Chính sách thay thế công việc nếu đã tồn tại
            workRequestBuilder
        )
    }
}
