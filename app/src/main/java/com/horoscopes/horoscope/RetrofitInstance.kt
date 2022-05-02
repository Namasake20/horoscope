package com.horoscopes.horoscope

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api:HoroApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://sameer-kumar-aztro-v1.p.rapidapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HoroApi::class.java)
    }


}