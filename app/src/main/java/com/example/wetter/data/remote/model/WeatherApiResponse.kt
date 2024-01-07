package com.example.wetter.data.remote.model

import kotlinx.serialization.Serializable



@Serializable
data class WeatherApiResponse(
    val weather: List<weather>,
    val main: main,
    val wind: wind
)

@Serializable
data class weather(
    val id: Int, 
    val main: String, 
    val description: String
)

@Serializable
data class main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val humidity: Double
)

@Serializable
data class wind(
    val speed: Double,
    val deg: Int,
)
