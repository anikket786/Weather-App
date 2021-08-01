package com.anikumar.weatherapp.repository

import com.anikumar.weatherapp.data.model.LatLon
import com.anikumar.weatherapp.data.network.WeatherForecastApi

class WeatherRepository(
    private val weatherForecastApi: WeatherForecastApi,
) : BaseRepository() {

    companion object {
        const val API_KEY = "1f89da47fe4d0be6bbbf376af70bdb58"
        const val EXCLUDE_PARAMETERS = "minutely,alerts"
    }

    suspend fun getWeatherForecast(
        latLon : LatLon,
    ) = safeApiCall {
        return@safeApiCall weatherForecastApi.getWeatherForecast(
            lat = latLon.latitude,
            lon = latLon.longitude,
            apiKey = API_KEY,
            exclude = EXCLUDE_PARAMETERS,
        )
    }
}