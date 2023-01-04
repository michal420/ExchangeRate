package com.example.exchangerate.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.exchangerate.ConversionRate
import com.example.exchangerate.MyCurrency
import com.example.exchangerate.conversionRates
import com.example.exchangerate.ui.theme.ExchangeRateTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import androidx.compose.material.Card


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
        items(conversionList) { conversion ->
            ConversionRateCard(conversionRate = conversion)
        }
    }
}

@Composable
fun ConversionRateCard(conversionRate: ConversionRate, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(1.dp)
            .fillMaxWidth(),
        elevation = 4.dp) {
        Column {
            Text(
                text = "${conversionRate.currencySymbol} = ${conversionRate.currencyRate}",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

//@Composable
//fun ConversionRateCard(conversionRate: ConversionRate, modifier: Modifier = Modifier) {
//    Card(
//        modifier = modifier
//            .padding(1.dp)
//            .fillMaxWidth(),
//        elevation = 4.dp
//    ) {
//        Column {
//            androidx.compose.material.Text(
//                text = "${currency.currencySymbol} ${currency.currencyAbbreviation} - ${currency.currencyName}",
//                modifier = Modifier.padding(16.dp)
//            )
//        }
//    }
//}


@Preview(showBackground = true, heightDp = 720, widthDp = 380)
@Composable
fun LatestPreview() {
    ExchangeRateTheme {
        Latest()
    }
}
