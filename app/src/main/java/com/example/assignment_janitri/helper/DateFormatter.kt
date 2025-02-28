package com.example.assignment_janitri.helper

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getFormattedDate() : String{

    val dateFormat = SimpleDateFormat("EEE, dd MMM yyyy hh:mm a", Locale.ENGLISH)
    val currentDate = Date()

    return dateFormat.format(currentDate)
}