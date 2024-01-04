package com.example.wetter.data.local

import com.example.wetter.model.Location
class LocationRepository {
    fun getLocations(): List<Location> {
        return listOf(
            Location("Ghent", "51.053822", "3.722270"),
            Location("Brussels", "50.850346", "4.351721"),
            Location("New York", "40.712776", "-74.005974"),
            Location("Berlin", "52.520008", "13.404954"),
            Location("Beijing", "39.904202", "116.407394"),
            Location("Sydney", "-33.865143", "151.209900"),
        )
    }
}