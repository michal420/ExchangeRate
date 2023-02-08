package com.example.exchangerate.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

// List of bar items
object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Latest rates",
            image = Icons.Filled.BarChart,
            route = "latest"
        ),
        BarItem(
            title = "Converter",
            image = Icons.Filled.CurrencyExchange,
            route = "converter"
        ),
        BarItem(
            title = "Currency list",
            image = Icons.Filled.AttachMoney,
            route = "currencies"
        )
    )
}