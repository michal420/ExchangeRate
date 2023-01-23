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

//    @GET("/latest")
//    suspend fun getRates(
//        @Query("rates") rates: Map<String, Double>
//    ): CurrencyRates

  @GET("latest")
  suspend fun getRates(): CurrencyRates

//interface CurrencyApi {
//    @GET("/latest")
//    suspend fun getRates(
//        @Query("base") base: String
//    ): Response<CurrencyRates>
//}

}







