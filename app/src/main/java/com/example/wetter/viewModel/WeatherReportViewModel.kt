package com.example.wetter.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.wetter.WetterApplication
import com.example.wetter.abstractions.WeatherService
import com.example.wetter.model.Location
import com.example.wetter.model.WeatherInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherReportViewModel(weatherService: WeatherService) : ViewModel() {
    private val _weatherService = weatherService
    private val _uiState = MutableStateFlow(WeatherReportUiState())
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val uiState = _uiState.asStateFlow()
    var weatherApiStatus: WeatherApiStatus by mutableStateOf(WeatherApiStatus.Loading)
        private set

    fun setSelectedLocation(location: Location) {
        _uiState.update { currentState -> currentState.copy(selectedLocation = location) }
    }

    fun getWeatherReportForLocation() {
        val location = uiState.value.selectedLocation!!

        coroutineScope.launch {
            try {
                val weatherReport = _weatherService.getWeatherForLocation(location)

                setWeatherData(weatherReport)
                weatherApiStatus = WeatherApiStatus.Success
            } catch (e: Exception) {
                _uiState.value.weatherReportError = e.message.toString()
                weatherApiStatus = WeatherApiStatus.Failed
            }
        }
    }

    private fun setWeatherData(weatherInfo: WeatherInfo) {
        _uiState.update { currentState ->
            currentState.copy(
                weatherInfo = weatherInfo
            )
        }
    }

    companion object {
        val Initiator: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as WetterApplication
                val weatherService = application.container.weatherService
                WeatherReportViewModel(weatherService)
            }
        }
    }
}

data class WeatherReportUiState(
    var selectedLocation: Location? = null,
    var weatherReportError: String = "",
    var locationName: String = "",
    var weatherInfo: WeatherInfo? = null
)

enum class WeatherApiStatus {
    Loading, Success, Failed
}