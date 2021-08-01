package com.anikumar.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class DailyWeatherData(
    @SerializedName("dt")
    val date : String,

    @SerializedName("temp")
    val temperature : MinMaxTemperature,

    @SerializedName("weather")
    val weather : List<Weather>,
)