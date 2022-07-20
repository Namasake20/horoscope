package com.horoscopes.horoscope.feature_horoscope.data.remote.dto

import com.horoscopes.horoscope.feature_horoscope.data.local.entity.ForecastEntity

data class ForecastDto(
    val color: String,
    val compatibility: String,
    val current_date: String,
    val date_range: String,
    val description: String,
    val lucky_number: String,
    val lucky_time: String,
    val mood: String
){

    fun toForecastEntity():ForecastEntity{
        return ForecastEntity(
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