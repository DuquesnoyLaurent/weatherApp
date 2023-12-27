package com.example.wetter.abstractions

import com.example.wetter.model.Location
import com.example.wetter.model.WeatherInfo

interface WeatherService {
    suspend fun getWeatherForLocation(location : Location) : WeatherInfo
}