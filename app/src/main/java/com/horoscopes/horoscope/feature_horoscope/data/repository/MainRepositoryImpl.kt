package com.horoscopes.horoscope.feature_horoscope.data.repository

import com.horoscopes.horoscope.feature_horoscope.data.local.ForecastDao
import com.horoscopes.horoscope.feature_horoscope.data.remote.HoroApi
import com.horoscopes.horoscope.feature_horoscope.domain.model.Forecast
import com.horoscopes.horoscope.feature_horoscope.domain.repository.MainRepository
import com.horoscopes.horoscope.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class MainRepositoryImpl @Inject constructor(
    private val api: HoroApi,
    private val dao: ForecastDao
) : MainRepository {
    override fun getPredictions(sign: String, day: String): Flow<Resource<Forecast>> = flow{

        emit(Resource.Loading())

//        retrieving new words from api and inserting to db
        try {
            val remotePrediction = api.getPredictions(sign, day)
            dao.deleteForecasts()
            if (remotePrediction != null) {
                dao.saveForecast(remotePrediction.toForecastEntity())
            }

        }catch (e: HttpException){
//            emit(Resource.Error(message = "Oops,something went wrong",data = prediction))
        }catch (e: IOException){

//            emit(Resource.Error(message = "Error,check internet connection",data = prediction))
        }

        //emitting new words
        val newPrediction = dao.getForecasts().toForecast()
        emit(Resource.Success(newPrediction))


    }

}