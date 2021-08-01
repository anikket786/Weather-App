package com.anikumar.weatherapp.data.network

import com.anikumar.weatherapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteDataSource {

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    fun <Api> buildApi(
        api: Class<Api>,
    ) : Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .also { client ->
                        if (BuildConfig.DEBUG) {
                            val logging = HttpLoggingInterceptor()
                            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                            client.addInterceptor(logging)
                        }
                    }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}