package com.anikumar.weatherapp.repository

import com.anikumar.weatherapp.data.network.NetworkResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ) : NetworkResource<T> {
        return withContext(Dispatchers.IO) {
            NetworkResource.Success(apiCall.invoke())
            try {
                NetworkResource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when(throwable) {
                    is HttpException -> {
                        NetworkResource.Failure(
                            false,
                            throwable.code(),
                            throwable.response()?.errorBody()
                        )
                    } else -> {
                        NetworkResource.Failure(true, null, null)
                    }
                }
            }
        }
    }
}