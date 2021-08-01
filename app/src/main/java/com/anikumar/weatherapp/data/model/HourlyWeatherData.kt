package com.anikumar.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class HourlyWeatherData(
    @SerializedName("temp")
    val temperature : String,

    @SerializedName("humidity")
    val humidity : String,

    @SerializedName("dt")
    val time : String,

    @SerializedName("weather")
    val weather : List<Weather>,
)
