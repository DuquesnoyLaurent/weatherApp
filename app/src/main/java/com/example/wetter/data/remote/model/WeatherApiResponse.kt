package com.example.wetter.data.remote.model

data class WeatherApiResponse(
    val weather: weather,
    val main: main,
    val wind: wind
)

data class weather(
    val id: Int, 
    val main: String, 
    val description: String
)

data class main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val humidity: Double
)

data class wind(
    val speed: Double,
    val deg: Int,
)
