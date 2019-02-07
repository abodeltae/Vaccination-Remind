package com.nazeer.vacinationreminder.Commons

import java.text.SimpleDateFormat
import java.util.*

object Utils {
    private var dateFormat = SimpleDateFormat("dd MMM yyyy")
    fun formatDate (calendar : Calendar):String {
        return dateFormat.format(calendar.time)
    }
    fun formatDate (timeStamp : Long):String {
        return dateFormat.format(Date(timeStamp))
    }
}