package com.example.assignment_janitri.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = PregnancyVitalEntity.TABLE_NAME)
data class PregnancyVitalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val systolicBp: String,
    val diastolicBp: String,
    val weightInKg: String,
    val babyKicks : String,
    val timeVitalAdded : String,
){
    companion object {
        const val TABLE_NAME = "PregnancyRelatedVitals"
    }
}