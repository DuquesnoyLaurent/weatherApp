package com.example.wetter.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wetter.model.Location
import com.example.wetter.viewModel.WeatherReportViewModel

@Composable
fun WeatherReport(
    weatherReportViewModel: WeatherReportViewModel = viewModel(factory = WeatherReportViewModel.Initiator),
    location: Location
) {
    Text(location.locationName)

}