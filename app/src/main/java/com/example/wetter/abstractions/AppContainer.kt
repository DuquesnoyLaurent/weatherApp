package com.example.wetter.abstractions

import com.example.wetter.abstractions.WeatherService
import com.example.wetter.abstractions.LocationService
import com.example.wetter.data.remote.api.LocationRepository

interface AppContainer {
    val weatherService : WeatherService
    val locationService : LocationService
}