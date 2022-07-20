package com.horoscopes.horoscope.feature_horoscope.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.horoscopes.horoscope.feature_horoscope.data.local.entity.ForecastEntity

@Database(entities = [ForecastEntity::class],version = 1)
abstract class ForecastDatabase:RoomDatabase() {
    abstract fun dao():ForecastDao
}