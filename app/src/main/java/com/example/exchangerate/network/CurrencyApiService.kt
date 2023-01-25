package com.example.exchangerate.network

import com.example.exchangerate.model.*
import retrofit2.http.GET
import retrofit2.http.Query

// GIT

/**
 * Public interface that exposes [getCurrencies] method
 */
interface CurrenciesApiService {
    @GET("currencies")
    suspend fun getCurrencies(): Map<String, String>

  @GET("latest")
  suspend fun getRates(): CurrencyRates

}







