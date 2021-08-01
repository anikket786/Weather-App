package com.anikumar.weatherapp.util

import java.lang.Long
import java.text.SimpleDateFormat
import java.util.*

fun getImageUrl(iconCode: String) =
    "https://openweathermap.org/img/wn/${iconCode}@4x.png"

fun formatDate(timestamp: String) : String {
    val date = Date(Long.valueOf(timestamp)*1000L)
    val simpleDateFormat = SimpleDateFormat("EEE, MMM dd")
    return simpleDateFormat.format(date)
}

fun formatTime(timestamp: String) : String {
    val date = Date(Long.valueOf(timestamp)*1000L)
    val simpleDateFormat = SimpleDateFormat("hh:mm aa")
    return simpleDateFormat.format(date)
}

