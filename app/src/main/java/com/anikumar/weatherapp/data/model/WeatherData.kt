package com.anikumar.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class WeatherData(
    @SerializedName("hourly")
    val hourlyData : List<HourlyWeatherData>,

    @SerializedName("daily")
    val dailyData : List<DailyWeatherData>,
)