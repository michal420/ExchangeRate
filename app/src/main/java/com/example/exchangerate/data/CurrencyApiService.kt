package com.example.exchangerate.network

import com.example.exchangerate.model.*
import retrofit2.http.GET

/**
 * Public interface that exposes [getCurrencies] and [getRates] methods
 */
interface CurrenciesApiService {
    @GET("currencies")
    suspend fun getCurrencies(): Map<String, String>

  @GET("latest")
  suspend fun getRates(): CurrencyRates

}







