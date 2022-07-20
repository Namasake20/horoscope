package com.horoscopes.horoscope.feature_horoscope.di

import android.app.Application
import androidx.room.Room
import com.horoscopes.horoscope.feature_horoscope.data.local.ForecastDatabase
import com.horoscopes.horoscope.feature_horoscope.data.remote.HoroApi
import com.horoscopes.horoscope.feature_horoscope.data.remote.HoroApi.Companion.BASE_URL
import com.horoscopes.horoscope.feature_horoscope.data.repository.MainRepositoryImpl
import com.horoscopes.horoscope.feature_horoscope.domain.model.Forecast
import com.horoscopes.horoscope.feature_horoscope.domain.repository.MainRepository
import com.horoscopes.horoscope.feature_horoscope.domain.use_case.GetPrediction
import com.horoscopes.horoscope.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHoroApi(): HoroApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(HoroApi::class.java)

    @Singleton
    @Provides
    fun provideMainRepository(api: HoroApi,db:ForecastDatabase): MainRepository = MainRepositoryImpl(api,db.dao())

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }

    @Singleton
    @Provides
    fun provideDatabase(application: Application):ForecastDatabase =
        Room.databaseBuilder(application,ForecastDatabase::class.java,"forecast_db").build()

    @Singleton
    @Provides
    fun provideGetPredictionUseCase(repository: MainRepository):GetPrediction{
        return GetPrediction(repository)
    }
}