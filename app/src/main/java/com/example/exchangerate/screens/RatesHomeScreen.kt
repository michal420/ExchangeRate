package com.example.exchangerate.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.exchangerate.model.CurrencyRates
import com.example.exchangerate.model.MyCurrency
import com.example.exchangerate.model.MyRate
import com.example.exchangerate.model.Rates
import com.example.exchangerate.ui.theme.ExchangeRateTheme
import java.util.*

@Composable
fun RatesHomeScreen(
    ratesUiState: RatesUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (ratesUiState) {
        is RatesUiState.Loading -> LoadingScreen(modifier)
        is RatesUiState.Success -> RatesScreen(ratesUiState.rates, modifier)
        is RatesUiState.Error -> ErrorScreen(retryAction, modifier)
    }
}

// Function to convert array to list
fun makeRatesList(m: Map<String, Double>): List<MyRate> {
    val ratesList = mutableListOf<MyRate>()
    val iter = m.keys.iterator()
    while (iter.hasNext()) {
        val key = iter.next()
        val value = m[key]
        if (value != null) {
            ratesList.add(MyRate(key, value))
        }
    }
    return ratesList
}

@Composable
fun RatesScreen(currencyRates: CurrencyRates, modifier: Modifier = Modifier) {

    // Return currency symbol and rates as the list of MyRates
    val myRatesList = makeRatesList(currencyRates.rates)

    LazyColumn {
        items(myRatesList) { r ->
            RatesCard(r)
        }
    }
}

@Composable
fun RatesCard(myRate: MyRate, modifier: Modifier = Modifier) {
    val euroSymbol = Currency.getInstance(Locale.GERMANY).symbol

    Card(
        modifier = modifier
            .padding(4.dp, 2.dp)
            .fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column() {
            Column {
                Text(
                    text = "1$euroSymbol = ${myRate.rate} ${myRate.symbol}",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}

//@Composable
//fun CurrenciesListScreen(currencies: Map<String, String>, modifier: Modifier = Modifier) {
//    val currenciesList = makeList(currencies)
//
//    LazyColumn {
//        items(items = currenciesList, key = { currency -> currency.symbol }) { currency ->
//            CurrenciesListCard(symbol = currency.symbol, name = currency.name)
//        }
//    }
//}