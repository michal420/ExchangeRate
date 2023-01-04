package com.example.exchangerate.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
    val cl = currenciesList
    CurrencyList(currencyList = cl)
}

@Composable
fun CurrencyList(currencyList: List<MyCurrency>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(currencyList) { currency ->
            CurrencyCard(currency)
        }
    }
}

@Composable
fun CurrencyCard(currency: MyCurrency, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(1.dp)
            .fillMaxWidth(),
        elevation = 4.dp) {
        Column {
            Text(
                text = "${currency.currencySymbol} ${currency.currencyAbbreviation} - ${currency.currencyName}",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
private fun CurrencyCardPreview() {
    CurrencyCard(currency = MyCurrency("Australian Dollar", "AUD", "$"))
}
