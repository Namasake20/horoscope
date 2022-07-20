package com.horoscopes.horoscope.feature_horoscope.domain.use_case

import com.horoscopes.horoscope.feature_horoscope.domain.model.Forecast
import com.horoscopes.horoscope.feature_horoscope.domain.repository.MainRepository
import com.horoscopes.horoscope.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPrediction(private val repository: MainRepository) {
    operator fun invoke(sign:String,day:String): Flow<Resource<Forecast>>{
        if (sign.isBlank() && day.isBlank()){
            return flow {  }
        }
        return repository.getPredictions(sign, day)
    }
}