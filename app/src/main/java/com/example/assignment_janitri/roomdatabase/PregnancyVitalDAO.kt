package com.example.assignment_janitri.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface PregnancyVitalDAO {

    @Insert
    suspend fun insertPregnancyVital(pregnancyVitalEntity: PregnancyVitalEntity)

    @Query("SELECT * FROM PregnancyRelatedVitals ORDER BY id DESC") // this is to make sure that latest log are on the top
    fun fetchPregnancyVitals() : Flow<List<PregnancyVitalEntity>>
}