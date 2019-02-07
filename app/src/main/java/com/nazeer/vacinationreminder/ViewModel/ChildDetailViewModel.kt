package com.nazeer.vacinationreminder.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nazeer.vacinationreminder.Data.Child
import com.nazeer.vacinationreminder.Data.Repo
import com.nazeer.vacinationreminder.Data.Vaccination

class ChildDetailViewModel (private val repo : Repo): ViewModel() {


    lateinit var child: LiveData<Child>
    lateinit var vaccinations: LiveData<List<Vaccination>>
    var childId :Long = -1
    set(value) {
        child = repo.getChild(value)
        vaccinations = repo.getVaccinationsForChild(value)
    }


}