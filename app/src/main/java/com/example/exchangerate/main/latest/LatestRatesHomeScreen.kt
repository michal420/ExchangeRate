package com.example.exchangerate.main.latest

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.exchangerate.model.CurrencyRates
import com.example.exchangerate.model.Rate
import com.example.exchangerate.main.currencies.ErrorScreen
import com.example.exchangerate.main.currencies.LoadingScreen
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun RatesHomeScreen(
    ratesUiState: RatesUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (ratesUiState) {
        is RatesUiState.Loading -> LoadingScreen(modifier)
        is RatesUiState.Success -> RatesScreen(ratesUiState.rates)
        is RatesUiState.Error -> ErrorScreen(retryAction, modifier)
    }
}

@Composable
fun RatesScreen(currencyRates: CurrencyRates) {
    var cur: Currency
    // Return currency shortName and rates as the list of MyRates
    val myRatesList = makeRatesList(currencyRates.rates)

    LazyColumn {
        item {
            CurrentDate()
        }
        items(myRatesList) { r ->
            cur = Currency.getInstance(r.shortName)
            RatesCard(cur.symbol.last().toString(), r)
        }
    }
}

@Composable
fun CurrentDate(modifier: Modifier = Modifier) {
    // Get current date, format it and store in the variable
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val formattedDate = currentDate.format(formatter)

    Card(
        modifier = modifier
            .padding(4.dp, 2.dp)
            .fillMaxWidth(),
        elevation = 4.dp
    ) {
        Row(modifier.padding(16.dp)) {
            Text(
                text = "Date: $formattedDate",
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun RatesCard(cur: String, rates: Rate, modifier: Modifier = Modifier) {
    val euroSymbol = Currency.getInstance(Locale.getDefault()).symbol

    Card(
        modifier = modifier
            .padding(4.dp, 2.dp)
            .fillMaxWidth(),
        elevation = 4.dp
    ) {
        Text(
            text = "1$euroSymbol = ${cur}${rates.rate} ${rates.shortName}",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.body1
        )
    }
}