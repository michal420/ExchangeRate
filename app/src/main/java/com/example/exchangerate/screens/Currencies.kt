package com.example.exchangerate.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Currencies() {
    val currenciesViewModel: CurrenciesViewModel = viewModel(factory = CurrenciesViewModel.Factory)
    CurrenciesHomeScreen(
        currenciesUiState = currenciesViewModel.currenciesUiState,
        retryAction = currenciesViewModel::getCurrencies
    )
}