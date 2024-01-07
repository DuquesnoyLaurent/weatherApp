package com.example.wetter.view

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wetter.R
import com.example.wetter.model.Location
import com.example.wetter.viewModel.WeatherAppWrapperViewModel

@Composable
fun WeatherAppWrapper(
    weatherAppWrapperViewModel: WeatherAppWrapperViewModel = viewModel(factory = WeatherAppWrapperViewModel.Initiator)
) {
    val navController = rememberNavController()
    fun navigateToWeatherReport(location: Location) {
        weatherAppWrapperViewModel.setSelectedLocation(location)
        navController.navigate(NavigationGraph.WeatherReport.name)
    }

    NavHost(
        navController = navController,
        startDestination = NavigationGraph.LocationsList.name
    ) {
        composable(NavigationGraph.LocationsList.name) {
            LocationsList(goToWeatherReport = { navigateToWeatherReport(location = it) })
        }

        composable(NavigationGraph.WeatherReport.name) {
            WeatherReport(location = weatherAppWrapperViewModel.getSelectedLocation())
        }
    }
}

enum class NavigationGraph(@StringRes val destination: Int) {
    LocationsList(destination = R.string.locationsList),
    WeatherReport(destination = R.string.weatherReport)
}