package com.nazeer.vacinationreminder.Notifications

import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.nazeer.vacinationreminder.Data.Child
import com.nazeer.vacinationreminder.Data.Vaccination
import java.util.*
import java.util.concurrent.TimeUnit
object NotifyManager{
    fun scheduleNotification (child: Child,vaccination: Vaccination){
        val workTag = "notificationWork"

        val inputData:Data = NotifyWorker.buildData(child,vaccination)
        val  delay = vaccination.time - Calendar.getInstance().timeInMillis
        val notificationWork = OneTimeWorkRequest.Builder(NotifyWorker::class.java)
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .setInputData(inputData)
            .addTag(workTag)
            .build()
        WorkManager.getInstance().beginWith( notificationWork).enqueue()


    }
}

