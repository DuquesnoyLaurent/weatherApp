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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationsListViewModel(locationService: LocationService) : ViewModel() {
    private val _locationService = locationService
    private val _uiState = MutableStateFlow(LocationsListUiState())
    val uiState = _uiState.asStateFlow()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun getLocations(){
        var locationsList: List<Location> = emptyList()

        coroutineScope.launch {
            try{
                locationsList = _locationService.getLocations()
                _uiState.update { currentState ->
                    currentState.copy(locationsList = locationsList, locationsLoaded = true) }
            }catch(e : Exception){
                _uiState.value.locationsIoError = e.message
            }
        }


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