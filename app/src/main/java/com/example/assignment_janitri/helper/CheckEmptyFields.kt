package com.example.assignment_janitri.helper

object CheckEmptyFields {

    fun isVitalFieldsEmpty(
        systolicBp: String,
        diastolicDp: String,
        weightInKg: String,
        babyKicks: String
    ) :Boolean{
        return systolicBp.trim().isEmpty() || diastolicDp.trim().isEmpty() || weightInKg.trim().isEmpty() || babyKicks.trim().isEmpty()
    }
}