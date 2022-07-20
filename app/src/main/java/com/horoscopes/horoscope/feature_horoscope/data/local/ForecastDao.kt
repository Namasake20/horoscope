package com.horoscopes.horoscope.feature_horoscope.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.horoscopes.horoscope.feature_horoscope.data.local.entity.ForecastEntity

@Dao
interface ForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveForecast(forecastEntity: ForecastEntity)

    @Query("DELETE FROM forecastentity")
    suspend fun deleteForecasts()

    @Query("SELECT * FROM forecastentity")
    suspend fun getForecasts():ForecastEntity

}