package com.example.exchangerate.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.exchangerate.ui.theme.ExchangeRateTheme
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exchangerate.ConversionRate
import com.example.exchangerate.conversionRates
import com.example.exchangerate.model.CurrencyRates
import com.example.exchangerate.model.MyCurrency
import com.example.exchangerate.model.MyRate

//val currenciesList = conversionRates as MutableList<ConversionRate>

@Composable
fun Converter(modifier: Modifier = Modifier) {
//    var selectedCurrency by rememberSaveable { mutableStateOf("") }
    val ratesViewModel: RatesViewModel = viewModel(factory = RatesViewModel.Factory)

    ConverterHomeScreen(
        ratesUiState = ratesViewModel.ratesUiState,
        retryAction = ratesViewModel::getRates
    )
//    Column(
//        modifier.padding(16.dp)
//    ) {
//        Text(text = "From")
//        CurrencyMenuBoxFrom(modifier = Modifier)
//        Text(text = "To")
////        CurrencyMenuBoxTo(modifier = Modifier)
//        CurrencyMenuBoxTo(modifier = Modifier)
//        ConvertButton()
//    } // end Column

} // end Converter

