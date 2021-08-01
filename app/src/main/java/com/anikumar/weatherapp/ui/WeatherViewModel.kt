package com.anikumar.weatherapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import com.anikumar.weatherapp.data.model.LatLon
import com.anikumar.weatherapp.data.model.WeatherData
import com.anikumar.weatherapp.data.network.NetworkResource
import com.anikumar.weatherapp.repository.WeatherRepository
import okhttp3.ResponseBody

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
) : ViewModel() {
    private val _weatherData : MutableLiveData<NetworkResource<WeatherData>> = MutableLiveData()
    val weatherData: LiveData<NetworkResource<WeatherData>>
        get() = _weatherData

    fun updateWeatherData(latLon: LatLon) = viewModelScope.launch {
        _weatherData.value = weatherRepository.getWeatherForecast(latLon)
    }
}