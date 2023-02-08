package com.example.exchangerate.data

import com.example.exchangerate.model.CurrencyRates

// Repository that fetches data
interface CurrenciesRepository {
    suspend fun getCurrencies(): Map<String, String>
}

interface RatesRepository {
    suspend fun getRates(): CurrencyRates
}

// Network implementation of Repository from currenciesApi
class NetworkCurrenciesRepository(
    private val currenciesApiService: CurrenciesApiService
) : CurrenciesRepository {
    /** Fetches list of currencies from api*/
    override suspend fun getCurrencies(): Map<String, String> = currenciesApiService.getCurrencies()
}

class NetworkRatesRepository(
    private val currenciesApiService: CurrenciesApiService
) : RatesRepository {
    override suspend fun getRates(): CurrencyRates = currenciesApiService.getRates()
}