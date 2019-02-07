package com.nazeer.vacinationreminder.Commons

import com.nazeer.vacinationreminder.Data.Child
import com.nazeer.vacinationreminder.Data.Vaccination
import java.util.*
import kotlin.collections.ArrayList

object VaccinationUtils {
    fun createNeededVaccination(time : Long, id:Long):List<Vaccination>{
        val c = Calendar.getInstance()
        val vaccinations = ArrayList<Vaccination>()
        c.timeInMillis=time
        val tempCalendar = Calendar.getInstance()
        tempCalendar.add(Calendar.SECOND,10);
        vaccinations.add(Vaccination("test 10 seconds Vaccination ",tempCalendar.timeInMillis,id))
        c.add(Calendar.MONTH,6);
        vaccinations.add(Vaccination("6 Months Vaccination ",c.timeInMillis,id))
        c.add(Calendar.MONTH,6);
        vaccinations.add(Vaccination("1 year Vaccination ",c.timeInMillis,id))
        return vaccinations
    }
}