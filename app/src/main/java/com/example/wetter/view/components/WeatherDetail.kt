package com.example.wetter.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SevereCold
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.Water
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.WindPower
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.wetter.R
import com.example.wetter.model.WeatherInfo

@Composable
fun WeatherDetail(locationName : String,weatherInfo: WeatherInfo, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "${locationName}: ${weatherInfo.weatherDescription}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            WeatherInfoItem(
                title = stringResource(id = R.string.temperature),
                value = "${weatherInfo.currentTemperature}",
                icon = {Icon(Icons.Filled.Thermostat, null)}
            )
            WeatherInfoItem(
                title = stringResource(id = R.string.feelsLike),
                value = "${weatherInfo.feelsLike}°",
                icon = { Icon(Icons.Filled.WaterDrop, null) }
            )
            WeatherInfoItem(
                title = stringResource(id = R.string.temperatureMin),
                value = "${weatherInfo.temperatureMin}°",
                icon = { Icon(Icons.Filled.SevereCold, null) }
            )
            WeatherInfoItem(
                title = stringResource(id = R.string.temperatureMax),
                value = "${weatherInfo.temperatureMax}°",
                icon = { Icon(Icons.Filled.WbSunny, null) }
            )
            WeatherInfoItem(
                title = stringResource(id = R.string.humidity),
                value = "${weatherInfo.humidity}%",
                icon = { Icon(Icons.Filled.Water, null) }
            )
            WeatherInfoItem(
                title = stringResource(id = R.string.windSpeed),
                value = "${weatherInfo.windSpeed} km/h",
                icon = { Icon(Icons.Filled.Speed, null) }
            )
            WeatherInfoItem(
                title = stringResource(id = R.string.windDirection),
                value = "${weatherInfo.windDirection} deg",
                icon = { Icon(Icons.Filled.WindPower, null) }
            )
        }
    }
}

@Composable
fun WeatherInfoItem(
    title: String,
    value: String,
    icon: @Composable () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        icon()
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}