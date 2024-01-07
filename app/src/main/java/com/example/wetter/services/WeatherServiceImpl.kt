package com.example.wetter.services

import com.example.wetter.BuildConfig
import com.example.wetter.abstractions.WeatherService
import com.example.wetter.data.remote.api.WeatherRepository
import com.example.wetter.data.remote.model.WeatherApiResponse
import com.example.wetter.model.Location
import com.example.wetter.model.WeatherInfo

class WeatherServiceImpl(private val weatherRepository: WeatherRepository) : WeatherService {
    override suspend fun getWeatherForLocation(location: Location): WeatherInfo {
        val apiResponse = createAndSendWeatherRequestForLocation(location)

        return parseApiResponse(apiResponse)
    }

    private suspend fun createAndSendWeatherRequestForLocation(location: Location) : WeatherApiResponse{
        val apiKey = BuildConfig.apiKey

        return weatherRepository.getWeatherForLocation(
            latitude = location.latitude,
            longitude = location.longitude,
            token = apiKey
        )
    }

    private fun parseApiResponse(weatherApiResponse: WeatherApiResponse) : WeatherInfo{
        return WeatherInfo(
            currentTemperature = weatherApiResponse.main.temp,
            feelsLike = weatherApiResponse.main.feels_like,
            temperatureMin = weatherApiResponse.main.temp_min,
            temperatureMax = weatherApiResponse.main.temp_max,
            weatherDescription = weatherApiResponse.weather[0].description,
            humidity = weatherApiResponse.main.humidity,
            windSpeed = weatherApiResponse.wind.speed,
            windDirection = weatherApiResponse.wind.deg
        )
    }

}