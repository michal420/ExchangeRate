package com.example.exchangerate.data

import com.example.exchangerate.network.CurrenciesApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

// GIT

interface AppContainer {
    val currenciesRepository: CurrenciesRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = "https://api.frankfurter.app/"

    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
//    .addConverterFactory(ScalarsConverterFactory.create())
//    .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: CurrenciesApiService by lazy {
        retrofit.create(CurrenciesApiService::class.java)
    }

    // DI implementation of currencies repository
    override val currenciesRepository: CurrenciesRepository by lazy {
        NetworkCurrenciesRepository(retrofitService)
    }

}