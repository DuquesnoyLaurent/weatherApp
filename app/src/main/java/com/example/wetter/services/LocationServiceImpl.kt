package com.example.wetter.services

import com.example.wetter.abstractions.LocationService
import com.example.wetter.data.local.LocationRepository
import com.example.wetter.model.Location

class LocationServiceImpl(private val locationRepository: LocationRepository) : LocationService {
    override suspend fun getLocations() : List<Location>{
        return locationRepository.getLocations()
    }
}