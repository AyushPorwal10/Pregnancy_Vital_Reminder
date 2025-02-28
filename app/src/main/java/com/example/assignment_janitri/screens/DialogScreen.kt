package com.example.assignment_janitri.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.assignment_janitri.R
import com.example.assignment_janitri.helper.CheckEmptyFields
import com.example.assignment_janitri.helper.TextFieldStyle
import com.example.assignment_janitri.helper.getFormattedDate
import com.example.assignment_janitri.roomdatabase.PregnancyVitalEntity
import com.example.assignment_janitri.ui.theme.DarkPurple
import com.example.assignment_janitri.ui.theme.Purple
import com.example.assignment_janitri.ui.theme.White


@Composable
fun DialogScreen(
    onSubmit: (PregnancyVitalEntity) -> Unit,
    onDismiss: () -> Unit,
) {

    var systolicBp by remember { mutableStateOf("") }
    var diastolicBp by remember { mutableStateOf("") }
    var weightInKg by remember { mutableStateOf("") }
    var babyKicks by remember { mutableStateOf("") }
    var showMessageToFillAllFields by remember { mutableStateOf(false) }

    Dialog(
        onDismissRequest =
        {
            onDismiss()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            elevation = CardDefaults.cardElevation(15.dp),
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(containerColor = White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {


            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = stringResource(id = R.string.add_vitals),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(8.dp),
                    color = DarkPurple,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {

                    TakeVitalInput(inputValue = systolicBp, onValueChange = { newValue ->
                        systolicBp = newValue

                    }, modifier = Modifier.weight(1f), stringResource(id = R.string.sys_bp))
                    TakeVitalInput(inputValue = diastolicBp, onValueChange = { newValue ->
                        diastolicBp = newValue
                    }, modifier = Modifier.weight(1f), stringResource(id = R.string.dia_bp))

                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TakeVitalInput(
                        inputValue = weightInKg, onValueChange = {
                            weightInKg = it
                        },
                        modifier = Modifier
                            .weight(1f), stringResource(id = R.string.weight_in_kg)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TakeVitalInput(inputValue = babyKicks, onValueChange = { newValue ->
                        babyKicks = newValue
                    }, modifier = Modifier.weight(1f), stringResource(id = R.string.baby_kicks))

                }

                if (showMessageToFillAllFields) {
                    Text(
                        text = stringResource(id = R.string.all_fields_required),
                        style = MaterialTheme.typography.titleMedium,
                        color = Purple
                    )
                }

                Button(
                    onClick = {

                        if (CheckEmptyFields.isVitalFieldsEmpty(
                                systolicBp,
                                diastolicBp,
                                weightInKg,
                                babyKicks
                            )
                        ) {
                            showMessageToFillAllFields = true
                        } else {
                            onSubmit(
                                PregnancyVitalEntity(
                                    0,
                                    systolicBp,
                                    diastolicBp,
                                    weightInKg,
                                    babyKicks,
                                    getFormattedDate()
                                )
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(top = 40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Purple),
                    shape = TextFieldStyle.textFieldDefaultShape
                ) {
                    Text(text = "Submit")
                }
            }
        }
    }
}

@Composable
fun TakeVitalInput(
    inputValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    placeHolder: String
) {
    OutlinedTextField(
        value = inputValue,
        onValueChange = { onValueChange(it) },
        modifier = modifier
            .padding(8.dp)
            .height(60.dp),
        colors = TextFieldStyle.myTextFieldColor(),
        shape = TextFieldStyle.textFieldDefaultShape,
        maxLines = 1,
        placeholder = {
            Text(text = placeHolder)
        },
        keyboardOptions = KeyboardOptions(keyboardType = if(placeHolder == "Dia BP") KeyboardType.Unspecified else KeyboardType.Number)
    )
}