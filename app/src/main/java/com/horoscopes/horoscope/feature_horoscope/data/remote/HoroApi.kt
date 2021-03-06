package com.horoscopes.horoscope.feature_horoscope.data.remote

import com.horoscopes.horoscope.feature_horoscope.data.remote.dto.ForecastDto
import com.horoscopes.horoscope.util.apikey
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface HoroApi {

    companion object{
        const val BASE_URL = " https://sameer-kumar-aztro-v1.p.rapidapi.com"
    }

    @Headers("X-RapidAPI-Host: sameer-kumar-aztro-v1.p.rapidapi.com", apikey)
    @POST(".")
    suspend fun getPredictions(@Query("sign") sign: String, @Query("day") day:String): ForecastDto?
}
