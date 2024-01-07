package com.example.wetter.appContainer


import com.example.wetter.WetterApplication
import com.example.wetter.abstractions.AppContainer
import com.example.wetter.abstractions.LocationService
import com.example.wetter.abstractions.WeatherService
import com.example.wetter.data.local.LocationDatabase
import com.example.wetter.data.local.LocationRepository
import com.example.wetter.data.remote.api.WeatherRepository
import com.example.wetter.services.LocationServiceImpl
import com.example.wetter.services.WeatherServiceImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class AppContainerImpl(wetterApplication: WetterApplication) : AppContainer {
    companion object {
        private const val baseUrl = "https://api.openweathermap.org/data/2.5/"
    }

    private val JsonSerializer = Json { ignoreUnknownKeys = true}

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(JsonSerializer.asConverterFactory("application/json".toMediaType()))
        .client(okHttpClient)
        .build()

    private val weatherRepository: WeatherRepository by lazy {
        retrofit.create(WeatherRepository::class.java)
    }

    private val locationDatabase: LocationDatabase by lazy {
        LocationDatabase.getLocationDatabase(wetterApplication)
    }

    private val locationRepository: LocationRepository by lazy {
        LocationRepository(locationDatabase.LocationDao())
    }

    override val weatherService: WeatherService by lazy {
        WeatherServiceImpl(weatherRepository)
    }

    override val locationService: LocationService by lazy {
        LocationServiceImpl(locationRepository)
    }
}