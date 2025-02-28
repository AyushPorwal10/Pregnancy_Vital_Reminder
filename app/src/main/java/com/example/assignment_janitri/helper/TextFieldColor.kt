package com.example.assignment_janitri.helper

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.assignment_janitri.ui.theme.Black
import com.example.assignment_janitri.ui.theme.LightBlack
import com.example.assignment_janitri.ui.theme.Purple

object TextFieldStyle {

    @Composable

    fun myTextFieldColor() = OutlinedTextFieldDefaults.colors(


        cursorColor = Purple,

        unfocusedPlaceholderColor = LightBlack,
        focusedPlaceholderColor = LightBlack,

        unfocusedBorderColor = LightBlack,
        focusedBorderColor = LightBlack,

        unfocusedTextColor = LightBlack ,
        focusedTextColor = LightBlack,

        focusedLabelColor = Purple,
        unfocusedLabelColor = LightBlack

    )
    val textFieldDefaultShape = RoundedCornerShape(4.dp)

}