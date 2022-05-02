package com.horoscopes.horoscope

import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface HoroApi {

    @Headers()
    @POST(".")
    suspend fun getForecast(@Query("sign") sign: String,@Query("day") day:String): Response<Forecast>
}