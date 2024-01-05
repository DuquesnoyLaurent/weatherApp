package com.example.wetter.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wetter.data.local.model.LocationEntity

@Dao
interface LocationDao {
    @Query("select * from locationentity order by id asc")
    fun getAllLocations(): List<LocationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(locationEntity: LocationEntity)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertMany(locations: List<LocationEntity>)
}