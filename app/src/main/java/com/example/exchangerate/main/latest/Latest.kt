package com.example.exchangerate.main.latest

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Latest() {
    Column {
        val ratesViewModel: LatestRatesViewModel = viewModel(factory = LatestRatesViewModel.Factory)
        RatesHomeScreen(
            ratesUiState = ratesViewModel.ratesUiState,
            retryAction = ratesViewModel::getRates
        )
    }
}
