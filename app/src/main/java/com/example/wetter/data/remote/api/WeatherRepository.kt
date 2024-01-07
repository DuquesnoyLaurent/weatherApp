package com.example.wetter.data.remote.api

import com.example.wetter.data.remote.model.WeatherApiResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherRepository {
    @GET("weather")
    suspend fun getWeatherForLocation(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("appid") token: String,
        @Query("units") units: String = "metric"
    ): WeatherApiResponse
}