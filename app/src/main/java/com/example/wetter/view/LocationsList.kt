package com.example.wetter.view

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wetter.viewModel.LocationsListViewModel

@Composable
fun LocationsList(
    locationsListViewModel: LocationsListViewModel =
        viewModel(factory = LocationsListViewModel.Initiator)) {


}