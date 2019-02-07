package com.nazeer.vacinationreminder.Data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "child_table")
 data class Child(val name: String,
             val birthDate: Long ) {
    @PrimaryKey(autoGenerate = true)
    var id : Long =0
}