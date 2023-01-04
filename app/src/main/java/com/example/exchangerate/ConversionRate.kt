package com.example.exchangerate

data class ConversionRate(val currencySymbol: String, val currencyRate: Double)

val conversionRates = listOf(
    ConversionRate(
        currencySymbol = "CAD",
        currencyRate = 1.5626
    ),
    ConversionRate(
        currencySymbol = "AUD",
        currencyRate = 0.5626
    ),
    ConversionRate(
        currencySymbol = "GBP",
        currencyRate = 1.8615
    ),
    ConversionRate(
        currencySymbol = "USD",
        currencyRate = 1.0516
    ),
)


