package com.example.wetter.services

import com.example.wetter.abstractions.WeatherService
import com.example.wetter.data.remote.api.WeatherRepository
import com.example.wetter.model.Location
import com.example.wetter.model.WeatherInfo

class WeatherServiceImpl(weatherRepository: WeatherRepository) : WeatherService {
    override suspend fun getWeatherForLocation(location: Location): WeatherInfo {
        TODO("Not yet implemented")
    }

}