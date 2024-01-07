package com.example.wetter.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wetter.R
import com.example.wetter.model.Location
import com.example.wetter.view.components.WeatherDetail
import com.example.wetter.viewModel.WeatherApiStatus
import com.example.wetter.viewModel.WeatherReportViewModel

@Composable
fun WeatherReport(
    weatherReportViewModel: WeatherReportViewModel = viewModel(factory = WeatherReportViewModel.Initiator),
    location: Location
) {
    val weatherApiStatus = weatherReportViewModel.weatherApiStatus
    val weatherReportUiState by weatherReportViewModel.uiState.collectAsState()
    val modifier: Modifier = Modifier

    weatherReportViewModel.setSelectedLocation(location)
    weatherReportViewModel.getWeatherReportForLocation()

    when (weatherApiStatus) {
        WeatherApiStatus.Loading ->
            Box(
                modifier = modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(32.dp)
                        .width(32.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }

        WeatherApiStatus.Success ->
            WeatherDetail(
                locationName = location.locationName,
                weatherInfo = weatherReportUiState.weatherInfo!!
            )

        WeatherApiStatus.Failed ->
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.loadingWeatherFailed),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.error
                )
                Text(
                    text = weatherReportUiState.weatherReportError ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
    }

}