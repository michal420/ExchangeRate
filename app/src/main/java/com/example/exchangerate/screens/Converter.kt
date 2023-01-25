package com.example.exchangerate.screens

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Converter(modifier: Modifier = Modifier) {
    val ratesViewModel: RatesViewModel = viewModel(factory = RatesViewModel.Factory)
    ConverterHomeScreen(
        ratesUiState = ratesViewModel.ratesUiState,
        retryAction = ratesViewModel::getRates
    )
}

