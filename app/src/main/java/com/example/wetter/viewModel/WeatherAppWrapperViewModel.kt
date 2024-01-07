package com.example.wetter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.wetter.model.Location

class WeatherAppWrapperViewModel : ViewModel() {
    private var _selectedLocation: Location? = null

    fun setSelectedLocation(location: Location){
        _selectedLocation = location
    }

    fun getSelectedLocation() : Location {
        if( _selectedLocation != null) return _selectedLocation!!

        throw IllegalStateException("There is no location selected")
    }

    companion object{
        val Initiator : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                WeatherAppWrapperViewModel()
            }
        }
    }
}