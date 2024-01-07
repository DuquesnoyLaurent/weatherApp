package com.example.wetter.model

//todo icons?
data class WeatherInfo(
    val currentTemperature: Double,
    val feelsLike: Double,
    val temperatureMin: Double,
    val temperatureMax: Double,

    val weatherDescription: String,

    val humidity: Double,

    val windSpeed: Double,
    val windDirection: Int
)
