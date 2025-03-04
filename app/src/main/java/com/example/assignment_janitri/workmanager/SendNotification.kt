package com.example.assignment_janitri.workmanager

import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SendNotification @Inject constructor(
    private val workManager: WorkManager
) {
    fun startNotificationSchedule() {
        val request = PeriodicWorkRequest.Builder(MyWorker::class.java, 5, TimeUnit.HOURS , 15, TimeUnit.MINUTES)
            .build()
        workManager.enqueueUniquePeriodicWork(
            "NotificationWork",
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }


}