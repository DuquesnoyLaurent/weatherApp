package com.example.wetter

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.wetter.model.Location
import com.example.wetter.model.WeatherInfo
import com.example.wetter.view.WeatherReport
import com.example.wetter.viewModel.WeatherApiStatus
import com.example.wetter.viewModel.WeatherReportUiState
import com.example.wetter.viewModel.WeatherReportViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.given
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [28])
class WeatherReportTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testWeatherReportLoading() {
        val viewModel = mock<WeatherReportViewModel>()
        val location = Location( "51.053822", "3.722270", "Ghent")
        val weatherApiStatus = WeatherApiStatus.Loading
        val uiState = MutableStateFlow(WeatherReportUiState())

        `when`(viewModel.weatherApiStatus).thenReturn(weatherApiStatus)
        given(viewModel.uiState).willReturn(uiState)

        composeTestRule.setContent {
            WeatherReport(weatherReportViewModel = viewModel, location = location)
        }

        composeTestRule.onNodeWithTag("loading").assertIsDisplayed()
        composeTestRule.onNodeWithTag("content").assertDoesNotExist()
    }

    @Test
    fun testWeatherReportSuccess() {
        val viewModel = mock<WeatherReportViewModel>()
        val location = Location( "51.053822", "3.722270", "Ghent")
        val weatherApiStatus = WeatherApiStatus.Success
        val weatherInfo = WeatherInfo(
            currentTemperature = 20.0,
            feelsLike = 18.0,
            temperatureMin = 14.0,
            temperatureMax = 23.0,
            weatherDescription = "Partly cloudy",
            humidity = 50.0,
            windSpeed = 10.0,
            windDirection = 270
        )
        val uiState = MutableStateFlow(WeatherReportUiState(weatherInfo = weatherInfo))

        `when`(viewModel.weatherApiStatus).thenReturn(weatherApiStatus)
        given(viewModel.uiState).willReturn(uiState)

        composeTestRule.setContent {
            WeatherReport(weatherReportViewModel = viewModel, location = location)
        }

        composeTestRule.onNodeWithTag("loading").assertDoesNotExist()
        composeTestRule.onNodeWithTag("content").assertIsDisplayed()

    }

    @Test
    fun testWeatherReportFailed() {
        val viewModel = mock<WeatherReportViewModel>()
        val location = Location( "51.053822", "3.722270", "Ghent")
        val weatherApiStatus = WeatherApiStatus.Failed
        val uiState = MutableStateFlow(WeatherReportUiState())

        `when`(viewModel.weatherApiStatus).thenReturn(weatherApiStatus)
        given(viewModel.uiState).willReturn(uiState)

        composeTestRule.setContent {
            WeatherReport(weatherReportViewModel = viewModel, location = location)
        }

        composeTestRule.onNodeWithTag("failed").assertIsDisplayed()
    }
}