package com.example.wetter.data.remote.api

import com.example.wetter.data.remote.model.WeatherApiResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface WeatherRepository {
    //https://api.openweathermap.org/data/2.5/weather\?lat\=51.053822\&lon\=3.722270\&appid\=a0085eb0031511687f5f61d02f5770a0\&units\=metric
    @GET("/weather/?lat={latitude}&lon={longitude}&appid={token}&units=metric")
    suspend fun getWeatherForLocation(
        @Path("latitude") latitude: String,
        @Path("longitude") longitude: String,
        @Path("token") token: String
    ): WeatherApiResponse
}