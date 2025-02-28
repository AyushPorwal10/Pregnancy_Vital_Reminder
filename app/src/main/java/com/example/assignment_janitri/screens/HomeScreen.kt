package com.example.assignment_janitri.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.assignment_janitri.viewmodels.HomeScreenViewModel
import com.example.assignment_janitri.R
import com.example.assignment_janitri.ui.theme.DarkPurple
import com.example.assignment_janitri.ui.theme.Purple
import com.example.assignment_janitri.ui.theme.StatusBarColor
import com.example.assignment_janitri.ui.theme.White
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(mainScreenViewModel: HomeScreenViewModel) {

    val permissionState = rememberPermissionState(android.Manifest.permission.POST_NOTIFICATIONS)

    val context = LocalContext.current
    val showDialog by mainScreenViewModel.showDialog.collectAsState()

    val pregnancyVitals by mainScreenViewModel.pregnancyVitals.collectAsState()

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    color = DarkPurple,
                    fontWeight = FontWeight.Bold
                )
            },
            actions = {
                IconButton(onClick = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        if (!permissionState.status.isGranted) {
                            permissionState.launchPermissionRequest()
                        } else {
                            Toast.makeText(context, "Permission Already Granted", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Toast.makeText(context, "Permission Already Granted", Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Icon(Icons.Default.Notifications, contentDescription = "Notification")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = StatusBarColor,
                navigationIconContentColor = DarkPurple
            ),
        )
    },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    mainScreenViewModel.onAddClick()
                },
                containerColor = Purple,
                shape = CircleShape,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 6.dp
                )
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = White)
            }
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .background(White)
                .fillMaxSize()
                .padding(6.dp)
                .padding(paddingValues),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(pregnancyVitals) { pregnancyVital ->
                    SingleVital(pregnancyVital)
                }
            }

        }
    }


    if (showDialog) {
        DialogScreen(onSubmit = {
            mainScreenViewModel.insertVital(it)
            mainScreenViewModel.onDismiss()
        },
            onDismiss = {
                mainScreenViewModel.onDismiss()
            })
    }
}