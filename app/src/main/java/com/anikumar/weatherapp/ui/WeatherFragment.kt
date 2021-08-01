package com.anikumar.weatherapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.anikumar.weatherapp.R
import com.anikumar.weatherapp.data.model.LatLon
import com.anikumar.weatherapp.data.network.NetworkResource
import com.anikumar.weatherapp.data.network.RemoteDataSource
import com.anikumar.weatherapp.data.network.WeatherForecastApi
import com.anikumar.weatherapp.databinding.FragmentWeatherBinding
import com.anikumar.weatherapp.repository.WeatherRepository
import com.anikumar.weatherapp.ui.adapters.DailyWeatherDataAdapter
import com.anikumar.weatherapp.ui.adapters.HourlyWeatherDataAdapter
import com.google.android.material.snackbar.Snackbar

private const val CITY = "city"

class WeatherForecastFragment : Fragment(R.layout.fragment_weather) {

    private lateinit var binding : FragmentWeatherBinding
    private var cityLatLon : LatLon? = null

    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            when (it.getString(CITY)) {
                getString(R.string.rio_de_janeiro) ->
                    cityLatLon = LatLon(-22.9068, -43.1729)
                getString(R.string.beijing) ->
                    cityLatLon = LatLon(39.9042, 116.4074)
                getString(R.string.los_angeles) ->
                    cityLatLon = LatLon(34.0522, -118.2437)
            }
        }

        val weatherForecastApi = RemoteDataSource.buildApi(WeatherForecastApi::class.java)
        val weatherRepository = WeatherRepository(weatherForecastApi)
        weatherViewModel = WeatherViewModel(weatherRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWeatherBinding.bind(view)

        with (binding) {
            hourlyWeatherForecastRecyclerView.layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            hourlyWeatherForecastRecyclerView.addItemDecoration(
                DividerItemDecoration(
                    hourlyWeatherForecastRecyclerView.context,
                    DividerItemDecoration.HORIZONTAL,
                )
            )

            dailyWeatherForecastRecyclerView.layoutManager = LinearLayoutManager(activity)
            dailyWeatherForecastRecyclerView.addItemDecoration(
                DividerItemDecoration(
                    dailyWeatherForecastRecyclerView.context,
                    DividerItemDecoration.VERTICAL,
                )
            )
        }

        setWeatherDataObserver(view)

        dataLoading(true)
        weatherViewModel.updateWeatherData(cityLatLon!!)
    }

    private fun setWeatherDataObserver(view: View) {
        weatherViewModel.weatherData.observe(viewLifecycleOwner) { networkResource ->
            dataLoading(false)
            when (networkResource) {
                is NetworkResource.Success -> {
                    with(binding) {

                        hourlyWeatherForecastRecyclerView.adapter = HourlyWeatherDataAdapter(
                            networkResource.value.hourlyData,
                            requireContext(),
                        )

                        dailyWeatherForecastRecyclerView.adapter = DailyWeatherDataAdapter(
                            networkResource.value.dailyData,
                            requireContext(),
                        )
                    }
                }
                is NetworkResource.Failure -> {
                    if (networkResource.isNetworkError) {
                        Snackbar.make(
                            view,
                            getString(R.string.network_error_message),
                            Snackbar.LENGTH_LONG
                        ).show()
                    } else {
                        Snackbar.make(
                            view,
                            getString(R.string.unknown_failure_message),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun dataLoading(loading: Boolean) {
        binding.progressBar.isVisible = loading
        binding.hourlyWeatherForecastCardView.isVisible = !loading
        binding.dailyWeatherForecastCardView.isVisible = !loading
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param city
         * @return A new instance of fragment WeatherForecastFragment.
         */
        @JvmStatic
        fun newInstance(city: String) =
            WeatherForecastFragment().apply {
                arguments = Bundle().apply {
                    putString(CITY, city)
                }
            }
    }
}