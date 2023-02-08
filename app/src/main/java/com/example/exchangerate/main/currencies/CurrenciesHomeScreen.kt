package com.example.exchangerate.main.currencies

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
import java.text.NumberFormat
import java.util.*

@Composable
fun CurrenciesHomeScreen(
    currenciesUiState: CurrenciesUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (currenciesUiState) {
        is CurrenciesUiState.Loading -> LoadingScreen(modifier)
        is CurrenciesUiState.Success -> CurrenciesListScreen(currenciesUiState.currencies)
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
        Text(stringResource(R.string.loading_text))
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

/*
The Home screen displaying the success - list of currencies
 */
@Composable
fun CurrenciesListScreen(currencies: Map<String, String>) {
    val currenciesList = makeCurrenciesList(currencies)
    var cur: Currency

    LazyColumn {
        items(items = currenciesList, key = { currency -> currency.shortName }) { currency ->
            cur = Currency.getInstance(currency.shortName)
//            CurrenciesListCard(cur.symbol, symbol = currency.shortName, name = currency.name)

            if (cur.symbol == currency.shortName) {
                CurrenciesListCard("", shortName = currency.shortName, name = currency.name)
            } else {
                CurrenciesListCard(cur.symbol.last().toString(), shortName = currency.shortName, name = currency.name)
            }

        }
    }
}

@Composable
fun CurrenciesListCard(
    curSymbol: String,
    shortName: String,
    name: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(4.dp, 2.dp)
            .fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column {
            Text(
                text = "${curSymbol} $shortName - $name",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.body1
            )
        }
    }
}