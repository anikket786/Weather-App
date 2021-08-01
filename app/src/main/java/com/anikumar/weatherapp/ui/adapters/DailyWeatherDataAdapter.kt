package com.anikumar.weatherapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anikumar.weatherapp.R
import com.anikumar.weatherapp.data.model.DailyWeatherData
import com.anikumar.weatherapp.util.formatDate
import com.anikumar.weatherapp.util.getImageUrl
import com.squareup.picasso.Picasso

class DailyWeatherDataAdapter(
    private val dailyWeatherDataList : List<DailyWeatherData>,
    private val context : Context,
) : RecyclerView.Adapter<DailyWeatherDataAdapter.WeatherDataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return WeatherDataViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: WeatherDataViewHolder, position: Int) {
        val weatherData = dailyWeatherDataList[position]
        holder.bind(weatherData, context)
    }

    override fun getItemCount() = dailyWeatherDataList.size

    class WeatherDataViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_daily_weather, parent, false)) {

        private val maxTempTextView : TextView = itemView.findViewById(R.id.maxTemperatureTextView)
        private val minTempTextView : TextView = itemView.findViewById(R.id.minTemperatureTextView)
        private val descriptionTextView : TextView = itemView.findViewById(R.id.descriptionTextView)
        private val dateTextView : TextView = itemView.findViewById(R.id.dateTextView)
        private val imageView : ImageView = itemView.findViewById(R.id.imageView)

        fun bind(dailyWeatherData: DailyWeatherData, context: Context) {
            maxTempTextView.text = "${dailyWeatherData.temperature.maxTemperature}\u00B0"
            minTempTextView.text = "${dailyWeatherData.temperature.minTemperature}\u00B0"
            descriptionTextView.text = dailyWeatherData.weather[0].description

            dateTextView.text = formatDate(dailyWeatherData.date)
            Picasso.with(context)
                .load(getImageUrl(dailyWeatherData.weather[0].icon))
                .into(imageView)
        }
    }
}