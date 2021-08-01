package com.anikumar.weatherapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anikumar.weatherapp.R
import com.anikumar.weatherapp.data.model.HourlyWeatherData
import com.anikumar.weatherapp.util.formatTime
import com.anikumar.weatherapp.util.getImageUrl
import com.squareup.picasso.Picasso

class HourlyWeatherDataAdapter(
    private val hourlyWeatherDataList : List<HourlyWeatherData>,
    private val context : Context,
) : RecyclerView.Adapter<HourlyWeatherDataAdapter.WeatherDataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return WeatherDataViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: WeatherDataViewHolder, position: Int) {
        val weatherData = hourlyWeatherDataList[position]
        holder.bind(weatherData, context)
    }

    override fun getItemCount() = hourlyWeatherDataList.size

    class WeatherDataViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_hourly_weather, parent, false)) {

        private val tempTextView : TextView = itemView.findViewById(R.id.temperatureTextView)
        private val humidityTextView : TextView = itemView.findViewById(R.id.humidityTextView)
        private val timeTextView : TextView = itemView.findViewById(R.id.timeTextView)
        private val imageView : ImageView = itemView.findViewById(R.id.imageView)

        fun bind(hourlyWeatherData: HourlyWeatherData, context: Context) {
            tempTextView.text = "${hourlyWeatherData.temperature}\u00B0"
            humidityTextView.text = "${hourlyWeatherData.humidity}%"
            timeTextView.text = formatTime(hourlyWeatherData.time)
            Picasso.with(context)
                .load(getImageUrl(hourlyWeatherData.weather[0].icon))
                .into(imageView)
        }
    }
}