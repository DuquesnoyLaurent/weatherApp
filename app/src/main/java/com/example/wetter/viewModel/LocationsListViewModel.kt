package com.example.wetter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.wetter.WetterApplication
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.example.wetter.abstractions.WeatherService

class LocationsListViewModel(weatherService: WeatherService) : ViewModel() {


    companion object {
        val Initiator : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY]) as WetterApplication
                val weatherService = application.container.weatherService
                LocationsListViewModel(weatherService)
            }
        }
    }
}