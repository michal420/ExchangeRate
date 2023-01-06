package com.example.exchangerate.screens

import android.icu.util.Currency
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.exchangerate.ConversionRate
import com.example.exchangerate.conversionRates
import com.example.exchangerate.ui.theme.ExchangeRateTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun Latest(modifier: Modifier = Modifier) {
    // Get current date, format it and store in the variable
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val formattedDate = currentDate.format(formatter)

    // Store conversion rates in the list
    val conversionList: List<ConversionRate> = conversionRates

    Column {
        Row(modifier.padding(16.dp)) {
            Text(
                text = "Date: $formattedDate",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
            )
        }
        ConversionRateList(conversionList = conversionList)
    }
} // end Latest

@Composable
fun ConversionRateList(conversionList: List<ConversionRate>) {
    LazyColumn {
        conversionList as MutableList<ConversionRate>
        conversionList.sortBy { it.currencySymbol }
        items(conversionList) { conversion ->
            ConversionRateCard(conversionRate = conversion)
        }
    }
}

@Composable
fun ConversionRateCard(conversionRate: ConversionRate, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(4.dp, 2.dp)
            .fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column {
            val euroSymbol = Currency.getInstance(Locale.GERMANY).symbol
            Text(
                text = "$euroSymbol = ${conversionRate.currencyRate} ${conversionRate.currencySymbol}",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true, heightDp = 720, widthDp = 380)
@Composable
fun ConversionRateCardPreview() {
    ExchangeRateTheme {
        Latest()
    }
}
