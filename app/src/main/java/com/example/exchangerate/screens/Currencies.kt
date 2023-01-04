package com.example.exchangerate.screens

import android.icu.util.Currency
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


@Composable
fun Currencies() {
//    val cl = currenciesList
    CurrencyList()
}

//@Composable
//fun CurrencyList(currencyList: List<MyCurrency>) {
//    LazyColumn {
//        currencyList as MutableList<MyCurrency>
//        currencyList.sortBy { it.currencyName }
//        items(currencyList) { currency ->
//            CurrencyCard(currency)
//        }
//    }
//}

@Composable
fun CurrencyList() {
    val currenciesSet: Set<Currency> = Currency.getAvailableCurrencies()
    val currenciesList = currenciesSet.toMutableList()
    currenciesList.sortBy { it.displayName }
//    Log.d("tag", "${it.symbol} / ${it.currencyCode} / ${it.displayName}")
    LazyColumn {
        items(currenciesList) { currency ->
            CurrencyCard(currency)
        }
    }
//    currencies.forEach {
//        Log.d("tag", " ${it.symbol} / ${it.currencyCode} / ${it.displayName}")
//    }
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
//    CurrencyCard(currency = MyCurrency("Australian Dollar", "AUD", "$"))
    Currencies()
}
