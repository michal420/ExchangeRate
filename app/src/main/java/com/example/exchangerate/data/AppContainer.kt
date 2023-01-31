package com.example.exchangerate.data

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.exchangerate.network.CurrenciesApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface AppContainer {
    val currenciesRepository: CurrenciesRepository
    val ratesRepository: RatesRepository
}

class DefaultAppContainer(context: Context) : AppContainer {
    private val BASE_URL = "https://api.frankfurter.app/"

    val client = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor(context))
        .build()

    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */
    private val retrofit = Retrofit.Builder()
        //    .addConverterFactory(ScalarsConverterFactory.create())
        //    .addConverterFactory(MoshiConverterFactory.create())
        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
        .client(client)
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: CurrenciesApiService by lazy {
        retrofit.create(CurrenciesApiService::class.java)
    }

    // DI implementation of currencies repository
    override val currenciesRepository: CurrenciesRepository by lazy {
        NetworkCurrenciesRepository(retrofitService)
    }

    override val ratesRepository: RatesRepository by lazy {
        NetworkRatesRepository(retrofitService)
    }
}