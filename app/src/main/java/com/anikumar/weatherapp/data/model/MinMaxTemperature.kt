package com.anikumar.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class MinMaxTemperature(
    @SerializedName("min")
    val minTemperature : String,

    @SerializedName("max")
    val maxTemperature : String,
)
