package com.horoscopes.horoscope

data class Forecast(
    val color: String,
    val compatibility: String,
    val current_date: String,
    val date_range: String,
    val description: String,
    val lucky_number: String,
    val lucky_time: String,
    val mood: String
)