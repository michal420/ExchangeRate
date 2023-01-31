package com.example.exchangerate.main.converter

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exchangerate.main.latest.LatestRatesViewModel

@Composable
fun Converter() {
    val ratesViewModel: LatestRatesViewModel = viewModel(factory = LatestRatesViewModel.Factory)
    ConverterHomeScreen(
        ratesUiState = ratesViewModel.ratesUiState,
        retryAction = ratesViewModel::getRates
    )
}

