package com.example.wetter.data.local

import com.example.wetter.data.local.model.LocationEntity
import com.example.wetter.model.Location

class LocationRepository(
    private val locationDao: LocationDao
) {
    suspend fun getLocations(): List<Location> {
        if (locationDao.getAllLocations().isEmpty()) seedLocations()
        val locationsEntities =  locationDao.getAllLocations()
        val locations = mutableListOf<Location>()

        for(entity in locationsEntities){
            locations.add(convertToDomainModel(entity))
        }

        return locations
    }

    private suspend fun seedLocations() {
        locationDao.insertMany(
            listOf(
                convertToEntity(Location( "51.053822", "3.722270", "Ghent")),
                convertToEntity(Location( "50.850346", "4.351721","Brussels")),
                convertToEntity(Location( "40.712776", "-74.005974","New York")),
                convertToEntity(Location( "52.520008", "13.404954", "Berlin")),
                convertToEntity(Location( "39.904202", "116.407394", "Beijing")),
                convertToEntity(Location( "-33.865143", "151.209900", "Sydney"))
            )
        )
    }

    private fun convertToEntity(location: Location): LocationEntity {
        return LocationEntity(
            locationName = location.locationName,
            longitude = location.longitude,
            latitude = location.latitude
        )
    }

    private fun convertToDomainModel(entity: LocationEntity): Location {
        return Location(
            locationName = entity.locationName,
            longitude = entity.longitude,
            latitude = entity.latitude
        )
    }
}