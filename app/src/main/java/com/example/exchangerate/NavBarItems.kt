package com.example.exchangerate

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.exchangerate.screens.Latest
import com.example.exchangerate.ui.theme.ExchangeRateTheme

// List of bar items
object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Latest rates",
            image = Icons.Filled.WatchLater,
            route = "latest"
        ),
        BarItem(
            title = "Converter",
            image = Icons.Filled.CurrencyExchange,
            route = "converter"
        ),
        BarItem(
            title = "Currencies list",
            image = Icons.Filled.List,
            route = "currencies"
        )
    )
}