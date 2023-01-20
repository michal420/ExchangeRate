package com.example.exchangerate.data

import com.example.exchangerate.network.CurrenciesApiService

// GIT

// Repository that fetches data
interface CurrenciesRepository {
    suspend fun getCurrencies(): Map<String, String>
}

// Network implementation of Repository from currenciesApi
class NetworkCurrenciesRepository(
    private val currenciesApiService: CurrenciesApiService
) : CurrenciesRepository {
    /** Fetches list of currencies from apo*/
    override suspend fun getCurrencies(): Map<String, String> = currenciesApiService.getCurrencies()
}
