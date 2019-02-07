package com.nazeer.vacinationreminder.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VaccinationDao {
    @Insert
    fun insert(vaccination: Vaccination)

    @Insert
    fun insert(vaccinations: List<Vaccination>)

    @Query("select * from vaccination_table where childId = :childId" )
    fun getVaccinationForChild(childId: Long):LiveData<List<Vaccination>>
}