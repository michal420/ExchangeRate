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

//@Composable
//fun CurrencyList() {
//    val currenciesSet: Set<Currency> = Currency.getAvailableCurrencies()
////    val currenciesList = currenciesSet.toMutableList()
//    val currenciesList = currenciesSet.sortedBy { it.displayName }
////    currenciesList.sortedBy { it.displayName }
//    LazyColumn {
//        items(currenciesList) { currency ->
//            CurrencyCard(currency)
////            ConversionRateCard(currency)
//        }
//    }
//}
//
//@Composable
//fun CurrencyCard(currency: Currency, modifier: Modifier = Modifier) {
//    Card(
//        modifier = modifier
//            .padding(4.dp, 2.dp)
//            .fillMaxWidth(),
//        elevation = 4.dp
//    ) {
//        Column {
//            Text(
//                text = "${currency.symbol} / ${currency.currencyCode} - ${currency.displayName}",
//                modifier = Modifier.padding(16.dp)
//            )
//        }
//    }
//}
//
//@Preview(showBackground = true, heightDp = 720, widthDp = 380)
//@Composable
//private fun CurrencyCardPreview() {
//    Currencies()
//}