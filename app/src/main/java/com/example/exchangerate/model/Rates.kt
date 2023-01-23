package com.example.exchangerate.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rates(
    @SerialName("AUD")
    val aUD: Double,
    @SerialName("BGN")
    val bGN: Double,
    @SerialName("BRL")
    val bRL: Double,
    @SerialName("CAD")
    val cAD: Double,
    @SerialName("CHF")
    val cHF: Double,
    @SerialName("CNY")
    val cNY: Double,
    @SerialName("CZK")
    val cZK: Double,
    @SerialName("DKK")
    val dKK: Double,
    @SerialName("GBP")
    val gBP: Double,
    @SerialName("HKD")
    val hKD: Double,
    @SerialName("HUF")
    val hUF: Double,
    @SerialName("IDR")
    val iDR: Int,
    @SerialName("ILS")
    val iLS: Double,
    @SerialName("INR")
    val iNR: Double,
    @SerialName("ISK")
    val iSK: Double,
    @SerialName("JPY")
    val jPY: Double,
    @SerialName("KRW")
    val kRW: Double,
    @SerialName("MXN")
    val mXN: Double,
    @SerialName("MYR")
    val mYR: Double,
    @SerialName("NOK")
    val nOK: Double,
    @SerialName("NZD")
    val nZD: Double,
    @SerialName("PHP")
    val pHP: Double,
    @SerialName("PLN")
    val pLN: Double,
    @SerialName("RON")
    val rON: Double,
    @SerialName("SEK")
    val sEK: Double,
    @SerialName("SGD")
    val sGD: Double,
    @SerialName("THB")
    val tHB: Double,
    @SerialName("TRY")
    val tRY: Double,
    @SerialName("USD")
    val uSD: Double,
    @SerialName("ZAR")
    val zAR: Double
)