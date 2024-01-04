package com.example.wetter.abstractions

interface AppContainer {
    val weatherService : WeatherService
    val locationService : LocationService
}