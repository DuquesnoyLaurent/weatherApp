package com.example.wetter.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocationEntity(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "longitude") val longitude: String,
    @ColumnInfo(name="latitude") val latitude: String,
    @ColumnInfo(name="name") val locationName: String
)
