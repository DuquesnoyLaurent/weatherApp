package com.example.wetter.view.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.wetter.model.WeatherInfo

@Composable
fun WeatherDetail(weatherInfo: WeatherInfo) {
    Text(weatherInfo.weatherDescription)
}