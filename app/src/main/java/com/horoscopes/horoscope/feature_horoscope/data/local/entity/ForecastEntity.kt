package com.horoscopes.horoscope.feature_horoscope.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.horoscopes.horoscope.feature_horoscope.domain.model.Forecast

@Entity
data class ForecastEntity(
    val color: String,
    val compatibility: String,
    val current_date: String,
    val date_range: String,
    val description: String,
    val lucky_number: String,
    val lucky_time: String,
    val mood: String,
    @PrimaryKey val id:Int? = null
){
    fun toForecast():Forecast{
        return Forecast(
            color = color,
            compatibility = compatibility,
            current_date = current_date,
            date_range = date_range,
            description = description,
            lucky_number = lucky_number,
            lucky_time = lucky_time,
            mood = mood
        )
    }
}