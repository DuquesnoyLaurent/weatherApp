package com.example.wetter.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wetter.model.Location
import com.example.wetter.viewModel.LocationsListViewModel

@Composable
fun LocationsList(
    locationsListViewModel: LocationsListViewModel = viewModel(factory = LocationsListViewModel.Initiator),
    goToWeatherReport: (location : Location) -> Unit
) {
    val uiState by locationsListViewModel.uiState.collectAsState()
    locationsListViewModel.getLocations()


    Column(
        modifier = Modifier.padding(horizontal = 2.dp)
    ) {
        Column {
            uiState.locationsList.forEach { location ->
                LocationItem(location,
                goToWeatherReport = { goToWeatherReport(location) })
            }
        }
    }
}

@Composable
fun LocationItem(
    location: Location,
    goToWeatherReport:() -> Unit
) {
    Column(modifier = Modifier.clickable { goToWeatherReport() }) {
        Text(location.locationName)
        Text("${location.longitude},  ${location.latitude}")
    }
}