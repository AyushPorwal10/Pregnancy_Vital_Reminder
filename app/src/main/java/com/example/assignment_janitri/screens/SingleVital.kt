package com.example.assignment_janitri.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.assignment_janitri.R
import com.example.assignment_janitri.roomdatabase.PregnancyVitalEntity
import com.example.assignment_janitri.ui.theme.Black
import com.example.assignment_janitri.ui.theme.DarkPurple
import com.example.assignment_janitri.ui.theme.LightPink
import com.example.assignment_janitri.ui.theme.Purple
import com.example.assignment_janitri.ui.theme.White

@Composable
fun SingleVital(pregnancyVital: PregnancyVitalEntity
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(containerColor = LightPink),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceEvenly
        ) {




            Column(verticalArrangement = Arrangement.spacedBy(6.dp) ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    MyIcon(modifier = Modifier, R.drawable.heart_rate)
                    MyText(modifier = Modifier, value = "${pregnancyVital.systolicBp} bpm")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    MyIcon(modifier = Modifier, R.drawable.weight)
                    MyText(modifier = Modifier, value = "${pregnancyVital.weightInKg} kg")
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically ) {
                    MyIcon(modifier = Modifier, R.drawable.blood_pressure)
                    MyText(modifier = Modifier, value = "${pregnancyVital.diastolicBp} mmHg")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    MyIcon(modifier = Modifier, R.drawable.blood_pressure)
                    MyText(modifier = Modifier, value = "${pregnancyVital.babyKicks} kicks")
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Purple)
                .padding(6.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(text = pregnancyVital.timeVitalAdded , color = White)
        }
    }

}

@Composable
fun MyIcon(modifier: Modifier ,iconId : Int) {
    Icon(
        painter = painterResource(id = iconId),
        contentDescription = null,
        modifier = modifier.size(20.dp),
    )
}

@Composable
fun MyText(modifier: Modifier, value: String) {
    Text(
        text = value,
        color = DarkPurple,
        modifier = modifier
            .padding(6.dp),
        style = MaterialTheme.typography.titleSmall
    )
}