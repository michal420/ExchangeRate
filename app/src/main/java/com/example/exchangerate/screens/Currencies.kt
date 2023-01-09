package com.example.exchangerate.screens

import android.icu.util.Currency
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.exchangerate.ConversionRate
import java.util.*

@Composable
fun Currencies() {
    CurrencyList()
}

@Composable
fun CurrencyList() {
    val currenciesSet: Set<Currency> = Currency.getAvailableCurrencies()
//    val currenciesList = currenciesSet.toMutableList()
    val currenciesList = currenciesSet.sortedBy { it.displayName }
//    currenciesList.sortedBy { it.displayName }
    LazyColumn {
        items(currenciesList) { currency ->
            CurrencyCard(currency)
//            ConversionRateCard(currency)
        }
    }
}

@Composable
fun CurrencyCard(currency: Currency, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(4.dp, 2.dp)
            .fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column {
            Text(
                text = "${currency.symbol} / ${currency.currencyCode} - ${currency.displayName}",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true, heightDp = 720, widthDp = 380)
@Composable
private fun CurrencyCardPreview() {
    Currencies()
}
