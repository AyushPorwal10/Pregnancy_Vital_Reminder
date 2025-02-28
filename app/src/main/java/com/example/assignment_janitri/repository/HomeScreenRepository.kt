package com.example.assignment_janitri.repository

import com.example.assignment_janitri.roomdatabase.PregnancyVitalDAO
import com.example.assignment_janitri.roomdatabase.PregnancyVitalEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class HomeScreenRepository @Inject constructor(
    private val pregnancyVitalDAO: PregnancyVitalDAO
){

   suspend fun insertVital(pregnancyVitalData: PregnancyVitalEntity){
        pregnancyVitalDAO.insertPregnancyVital(pregnancyVitalData)
    }


     fun fetchPregnancyVital() : Flow<List<PregnancyVitalEntity>> {
        return pregnancyVitalDAO.fetchPregnancyVitals()
    }
}