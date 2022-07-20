package com.horoscopes.horoscope.feature_horoscope.domain.repository

import com.horoscopes.horoscope.feature_horoscope.domain.model.Forecast
import com.horoscopes.horoscope.util.Resource
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getPredictions(sign: String,day:String): Flow<Resource<Forecast>>
}