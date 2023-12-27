package com.example.wetter.appContainer


import com.example.wetter.abstractions.AppContainer
import com.example.wetter.abstractions.LocationService
import com.example.wetter.abstractions.WeatherService
import com.example.wetter.data.remote.api.LocationRepository
import com.example.wetter.data.remote.api.WeatherRepository
import com.example.wetter.services.WeatherServiceImpl
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class AppContainerImpl : AppContainer {
    companion object {
        private const val baseUrl = "https://api.openweathermap.org/data/2.5"
    }

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()

    private val weatherRepository : WeatherRepository by lazy {
        retrofit.create(WeatherRepository::class.java)
    }

    private val locationRepository: LocationRepository by lazy {
        
    }

    override val weatherService: WeatherService by lazy {
        WeatherServiceImpl(weatherRepository)
    }

    override val locationService: LocationService
        get() = TODO("Not yet implemented")


}
