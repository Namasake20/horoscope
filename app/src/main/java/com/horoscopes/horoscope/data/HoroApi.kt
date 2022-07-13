package com.horoscopes.horoscope.data

import com.horoscopes.horoscope.data.models.Forecast
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface HoroApi {

    companion object{
        const val BASE_URL = " https://sameer-kumar-aztro-v1.p.rapidapi.com"
    }

    @Headers()
    @POST(".")
    suspend fun getPredictions(@Query("sign") sign: String, @Query("day") day:String): Response<Forecast>
}
