package com.nazeer.vacinationreminder.ViewModel

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nazeer.vacinationreminder.Commons.Utils
import com.nazeer.vacinationreminder.Data.Child
import com.nazeer.vacinationreminder.Data.Repo
import java.util.*

class AddChildViewModel(private val repo : Repo): ViewModel() {
    private var birthDateCalendar:Calendar? = null
    var childName :String? = null

    var activityAliveLiveData : MutableLiveData<Boolean> = MutableLiveData()
    var childBirthDayLiveData : MutableLiveData<String> = MutableLiveData()


    fun setChildBirthDay(calendar : Calendar){
        birthDateCalendar = calendar
        childBirthDayLiveData.value = Utils.formatDate(calendar)
    }
     fun addChild () {
        val child = Child(childName!!, birthDateCalendar!!.timeInMillis)
         repo.insertChild(child)
         activityAliveLiveData.value = false

     }


    fun hasValidName(): Boolean {
        return !TextUtils.isEmpty(childName)
    }
    fun hasValidBirthday(): Boolean {
        return birthDateCalendar!=null
    }

}