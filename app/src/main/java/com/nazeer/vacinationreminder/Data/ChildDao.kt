package com.nazeer.vacinationreminder.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ChildDao {
    @Insert
    fun insert(child: Child) : Long

    @Query("select * from child_table")
    fun getAllChildren(): LiveData<List<Child>>

    @Query("select * from child_table where id = :childId" )
    fun getChild(childId: Long): LiveData<Child>
}