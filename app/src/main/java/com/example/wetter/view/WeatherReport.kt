package com.example.wetter.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wetter.R
import com.example.wetter.model.Location
import com.example.wetter.view.components.WeatherDetail
import com.example.wetter.viewModel.WeatherApiStatus
import com.example.wetter.viewModel.WeatherReportViewModel

@Composable
fun WeatherReport(
    weatherReportViewModel: WeatherReportViewModel = viewModel(factory = WeatherReportViewModel.Initiator),
    location: Location
) {
    val weatherApiStatus = weatherReportViewModel.weatherApiStatus
    val weatherReportUiState by weatherReportViewModel.uiState.collectAsState()

    weatherReportViewModel.setSelectedLocation(location)
    weatherReportViewModel.getWeatherReportForLocation()

    when (weatherApiStatus) {
        WeatherApiStatus.Loading -> Text(stringResource(id = R.string.loadingWeather))
        WeatherApiStatus.Success -> WeatherDetail(weatherReportUiState.weatherInfo!!)
        WeatherApiStatus.Failed -> Text("${stringResource(id = R.string.loadingWeatherFailed)}: ${weatherReportUiState.weatherReportError} ")
    }
}