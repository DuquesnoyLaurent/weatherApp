package com.example.wetter.model

//todo icons?
data class WeatherInfo(
    val temperatureMin: Double,
    val location: Location,
    val weatherDescription: String,
    val humidity: String,
    val windSpeed: String,
)
