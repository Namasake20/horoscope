package com.horoscopes.horoscope.main

import com.horoscopes.horoscope.data.models.Forecast
import com.horoscopes.horoscope.util.Resource

interface MainRepository {
    suspend fun getPredictions(sign: String,day:String): Resource<Forecast>
}