package com.example.wetter.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wetter.R
import com.example.wetter.model.Location
import com.example.wetter.viewModel.LocationsListViewModel

@Composable
fun LocationsList(
    locationsListViewModel: LocationsListViewModel = viewModel(factory = LocationsListViewModel.Initiator)
) {
    val uiState by locationsListViewModel.uiState.collectAsState()
    locationsListViewModel.getLocations()

    Column {
        if (!uiState.locationsLoaded) {
            Text(text = stringResource(id = R.string.loading_locations))
        } else {
            Column {
                uiState.locationsList.forEach { location -> LocationItem(location) }
            }
        }
    }
}

@Composable
fun LocationItem(location: Location) {
    Text(location.locationName)
}