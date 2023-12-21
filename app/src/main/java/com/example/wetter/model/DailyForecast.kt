package com.example.wetter.model

data class DailyForecast(
    val date: String,
    val temperatureMin: Double,
    val temperatureMax: Double,
    val weatherDescription: String
)
