package com.example.wetter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.wetter.WetterApplication
import com.example.wetter.abstractions.LocationService
import com.example.wetter.model.Location
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LocationsListViewModel(locationService: LocationService) : ViewModel() {
    private val _locationService = locationService
    private val uiState = MutableStateFlow(LocationsListUiState())
    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())


    private fun getLocations(): List<Location> {
        var locationsList: List<Location> = emptyList()

        coroutineScope.launch {
            try{
                locationsList = _locationService.getLocations()
            }catch(e : Exception){
                uiState.value.locationsIoError = e.message
            }
        }

        TODO("implement roomDB first")
    }
    companion object {
        val Initiator : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY]) as WetterApplication
                val locationService = application.container.locationService
                LocationsListViewModel(locationService)
            }
        }
    }
}


data class LocationsListUiState(
    var locationsList: List<Location> = emptyList(),
    var locationsIoError: String? = null,
    var locationsLoaded: Boolean = false
)