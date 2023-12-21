package com.example.wetter.model

data class HourlyForecast(
    val time: String,
    val temperature: Double,
    val weatherDescription: String
)
