package com.example.wetter.data.remote.api

import com.example.wetter.data.remote.model.WeatherApiResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherRepository {
    //https://api.openweathermap.org/data/2.5/weather\?lat\=51.053822\&lon\=3.722270\&appid\=a0085eb0031511687f5f61d02f5770a0\&units\=metric
    @GET("/weather")
    suspend fun getWeatherForLocation(
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Query("token") token: String,
        @Query("units") units: String = "metric"
    ): WeatherApiResponse
}