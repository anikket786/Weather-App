package com.anikumar.weatherapp.data.network

import com.anikumar.weatherapp.data.model.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherForecastApi {
    @GET("onecall")
    suspend fun getWeatherForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appId") apiKey: String,
        @Query("exclude") exclude: String,
        @Query("units") units: String = "metric"
    ) : WeatherData
}