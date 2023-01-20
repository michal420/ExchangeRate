package com.example.exchangerate.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.exchangerate.R
import com.example.exchangerate.model.MyCurrency

@Composable
fun HomeScreen(
    currenciesUiState: CurrenciesUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (currenciesUiState) {
        is CurrenciesUiState.Loading -> LoadingScreen(modifier)
        is CurrenciesUiState.Success -> LatestCurrenciesScreen(currenciesUiState.currencies, modifier)
        is CurrenciesUiState.Error -> ErrorScreen(retryAction, modifier)
    }
}

/**
 * The home screen displaying the loading message.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text("Retrieving currencies")
    }
}

/**
 * The home screen displaying error message
 */
@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Column {
            Text(stringResource(R.string.loading_failed))
            Button(onClick = retryAction) {
                Text(stringResource(R.string.retry))
            }
        }
    }
}

//@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ResultScreen(currencies: String, modifier: Modifier = Modifier) {
    Column {
        Text("$currencies")
//        LatestCurrenciesCard(currencies)
    }


//    val myList: List<String> = currencies.toList().map { "(${it.first}, ${it.second})" }
//    myList.forEach {
//        Log.d("tag", "$it")
//    }
//
//    for ((k, v) in currencies) {
//        Text("$k - $v")
//    }
}

fun makeList(m: Map<String, String>): List<MyCurrency> {
    val currenciesList = mutableListOf<MyCurrency>()
    val iter = m.keys.iterator()
    while (iter.hasNext()) {
        val key = iter.next()
        val value = m[key]
        if (value != null) {
            currenciesList.add(MyCurrency(key, value))
        }
    }
    return currenciesList
}

@Composable
fun LatestCurrenciesScreen(currencies: Map<String, String>, modifier: Modifier = Modifier) {
    val currenciesList = makeList(currencies)

    LazyColumn {
        items(items = currenciesList, key = { currency -> currency.symbol }) { currency ->
            LatestCurrenciesCard(symbol = currency.symbol, name = currency.name)
        }
    }
}

@Composable
fun LatestCurrenciesCard(symbol: String, name: String, modifier: Modifier = Modifier) {

    Card(
        modifier = modifier
            .padding(4.dp, 2.dp)
            .fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column {
            Text(
                text = "$symbol - $name",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.body1
            )
        }
    }
}