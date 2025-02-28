package com.example.assignment_janitri.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment_janitri.repository.HomeScreenRepository
import com.example.assignment_janitri.roomdatabase.PregnancyVital
import com.example.assignment_janitri.roomdatabase.PregnancyVitalEntity
import com.example.assignment_janitri.workmanager.SendNotification
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val homeScreenRepository: HomeScreenRepository,
    private val sendNotification: SendNotification
) : ViewModel() {

    private val _pregnancyVitals = MutableStateFlow<List<PregnancyVitalEntity>>(emptyList())
    val pregnancyVitals : StateFlow<List<PregnancyVitalEntity>> get() = _pregnancyVitals.asStateFlow()


    private val _showDialog = MutableStateFlow<Boolean>(false)

    var showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()


    fun onAddClick() {
        _showDialog.value = true
    }

    fun onDismiss() {
        _showDialog.value = false
    }

    fun startNotificationSchedule(){
        sendNotification.startNotificationSchedule()
    }

    fun insertVital(pregnancyVitalData: PregnancyVitalEntity) {
        viewModelScope.launch {
            Log.d("Data","Inserting data from viewModel")
            homeScreenRepository.insertVital(pregnancyVitalData)
        }
    }

    fun fetchPregnancyVital(){
        viewModelScope.launch {
            homeScreenRepository.fetchPregnancyVital().collect{listOfVitals->
                _pregnancyVitals.value = listOfVitals
            }
        }
    }
}