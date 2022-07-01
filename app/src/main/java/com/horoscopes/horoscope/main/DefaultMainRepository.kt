package com.horoscopes.horoscope.main

import com.horoscopes.horoscope.data.HoroApi
import com.horoscopes.horoscope.data.models.Forecast
import com.horoscopes.horoscope.util.Resource
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val api: HoroApi) :MainRepository{
    override suspend fun getPredictions(sign: String, day: String): Resource<Forecast> {
        return try {
            val response = api.getPredictions(sign,day)
            val result = response.body()
            if(response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch(e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }

}