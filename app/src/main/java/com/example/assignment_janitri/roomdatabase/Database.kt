package com.example.assignment_janitri.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [PregnancyVitalEntity::class] , version = 1)
abstract class PregnancyVital : RoomDatabase(){

    abstract fun pregnancyDAO () : PregnancyVitalDAO
}