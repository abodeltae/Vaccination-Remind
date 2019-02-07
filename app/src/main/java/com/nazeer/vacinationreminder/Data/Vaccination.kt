package com.nazeer.vacinationreminder.Data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "vaccination_table",
    indices = [Index("childId")],
    foreignKeys = [ForeignKey(entity = Child::class,parentColumns = ["id"],childColumns =["childId"] )])
class Vaccination(val name : String , val time : Long,val childId:Long) {
    @PrimaryKey(autoGenerate = true)
    var id :Long = 0
}