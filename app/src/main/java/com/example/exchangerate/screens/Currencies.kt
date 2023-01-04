package com.example.exchangerate.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.exchangerate.*
import com.example.exchangerate.ui.theme.ExchangeRateTheme

@Composable
fun Currencies() {

}

@Composable
fun CurrencyCard(currency: Currency, modifier: Modifier = Modifier) {
    Card(modifier = modifier.padding(8.dp), elevation = 4.dp) {
        Column {
            Text(
                text = "${currency.currencySymbol} ${currency.currencyAbbreviation} - ${currency.currencyName}",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun CurrencyList(currencyList: List<Currency>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(currencyList) { currency ->
            CurrencyCard(currency)
        }
    }
}


@Preview
@Composable
fun CurrenciesPreview() {
    ExchangeRateTheme {
    }
}
