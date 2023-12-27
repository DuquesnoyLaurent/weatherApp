package com.example.wetter.data.remote.api

import retrofit2.http.GET
import com.example.wetter.model.Location
import com.example.wetter.data.remote.model.WeatherApiResponse


interface WeatherRepository {
    @GET("/weather/")
    suspend fun getWeatherForLocation(location : Location) : WeatherApiResponse
}