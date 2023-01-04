package com.example.exchangerate

data class ConversionRate(val currencySymbol: String, val currencyRate: Double)

val conversionRates: List<ConversionRate> = listOf(
    ConversionRate(
        currencySymbol = "CAD",
        currencyRate = 1.2568
    ),
    ConversionRate(
        currencySymbol = "AUD",
        currencyRate = 1.5626
    ),
    ConversionRate(
        currencySymbol = "USD",
        currencyRate = 1.0516
    ),
    ConversionRate(
        currencySymbol = "GBP",
        currencyRate = 1.8615
    ),
    ConversionRate(
        currencySymbol = "EUR",
        currencyRate = 1.000
    ),

)


