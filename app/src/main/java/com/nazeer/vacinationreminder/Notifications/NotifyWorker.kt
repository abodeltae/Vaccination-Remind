package com.nazeer.vacinationreminder.Notifications

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.nazeer.vacinationreminder.Data.Child
import com.nazeer.vacinationreminder.Data.Vaccination
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import androidx.core.app.NotificationCompat
import com.nazeer.vacinationreminder.R
import com.nazeer.vacinationreminder.View.ChildDetailsActivity
import java.util.*


class NotifyWorker(private val mContext: Context,private val params: WorkerParameters) : Worker(mContext, params) {
companion object {
    private const val CHILD_ID="CHILD_ID"

    private const val CHILD_NAME= "CHILD_NAME"
    private const val  NOTIFICATION_CHANNEL_ID = "VaccinationReminder"

    private const val VACCINATION_NAME= "VACCINATION_NAME"

    fun buildData(child: Child, vaccination: Vaccination):Data{
      return Data.Builder().putLong(CHILD_ID, child.id)
            .putString(CHILD_NAME,child.name)
            .putString(VACCINATION_NAME,vaccination.name)
            .build()

    }
}
    override fun doWork(): Result {
        triggerNotification()

        return Result.success()

    }


    private fun triggerNotification() {

        val childId = params.inputData.getLong(CHILD_ID, -1)
        val childName = params.inputData.getString(CHILD_NAME)
        val vaccinationName = params.inputData.getString(VACCINATION_NAME)
        /**Creates an explicit intent for an Activity in your app */
        val resultIntent = ChildDetailsActivity.getStartIntent(mContext,childId)
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        val resultPendingIntent = PendingIntent.getActivity(
            mContext,
            0 /* Request code */, resultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val mBuilder = NotificationCompat.Builder(mContext,NOTIFICATION_CHANNEL_ID)
        val title = mContext.getString(R.string.vaccination_notification_title, childName)
        val message = mContext.getString(R.string.vaccination_notification_message,vaccinationName)
        mBuilder.setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.outline_add_white_36)
            .setAutoCancel(true)
            .setContentIntent(resultPendingIntent)

        val mNotificationManager = mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel =
                NotificationChannel(NOTIFICATION_CHANNEL_ID, "Vaccinations", importance)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID)
            mNotificationManager.createNotificationChannel(notificationChannel)
        }
        mNotificationManager.notify((title+message).hashCode(), mBuilder.build())

    }


}