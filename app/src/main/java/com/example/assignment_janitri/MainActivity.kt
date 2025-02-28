package com.example.assignment_janitri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.work.WorkManager
import com.example.assignment_janitri.screens.HomeScreen
import com.example.assignment_janitri.viewmodels.HomeScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var workManager: WorkManager

    private val mainScreenViewModel: HomeScreenViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen(mainScreenViewModel)
        }
        mainScreenViewModel.startNotificationSchedule()
        mainScreenViewModel.fetchPregnancyVital()
    }


}
