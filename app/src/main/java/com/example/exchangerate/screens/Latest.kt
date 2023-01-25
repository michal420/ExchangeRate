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
import androidx.lifecycle.viewmodel.compose.viewModel
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

    Column {
        Row(modifier.padding(16.dp)) {
            Text(
                text = "Date: $formattedDate",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
            )
        }

        val ratesViewModel: RatesViewModel = viewModel(factory = RatesViewModel.Factory)
        RatesHomeScreen(
            ratesUiState = ratesViewModel.ratesUiState,
            retryAction = ratesViewModel::getRates
        )
    }
}

@Preview(showBackground = true, heightDp = 720, widthDp = 380)
@Composable
fun ConversionRateCardPreview() {
    ExchangeRateTheme {
        Latest()
    }
}
