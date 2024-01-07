package com.example.wetter

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.wetter.data.local.LocationDao
import com.example.wetter.data.local.LocationDatabase
import com.example.wetter.data.local.model.LocationEntity
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [28])
class LocationsDbTests {
    private lateinit var locationEntityDao: LocationDao
    private lateinit var locationDatabase: LocationDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        locationDatabase = Room.databaseBuilder(
            context, LocationDatabase::class.java, "location-database"
        )
            .allowMainThreadQueries()
            .build()

        locationEntityDao = locationDatabase.LocationDao()
    }

    @After
    fun cleanUp() {
        locationDatabase.close()
    }

    @Test
    fun testInsertAndGet() {
        val testLocation =
            LocationEntity(longitude = "test", latitude = "test", locationName = "Test")
        locationEntityDao.insertOne(testLocation)

        val result = locationEntityDao.getAllLocations()
        assertEquals(result.size, 1)

    }

    @Test
    fun testGetAllEntities() {
        val testLocation1 =
            LocationEntity(longitude = "test", latitude = "test", locationName = "Test")
        val testLocation2 =
            LocationEntity(longitude = "test", latitude = "test", locationName = "Test")
        locationEntityDao.insertOne(testLocation1)
        locationEntityDao.insertOne(testLocation2)

        val result = locationEntityDao.getAllLocations()
        assertEquals(result.size, 2)

    }
}