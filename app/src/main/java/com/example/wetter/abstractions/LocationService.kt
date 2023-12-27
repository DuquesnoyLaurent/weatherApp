package com.example.wetter.abstractions

import com.example.wetter.model.Location


interface LocationService {
    suspend fun getLocations(): List<Location>
}