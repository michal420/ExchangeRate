package com.example.exchangerate.model

import kotlinx.serialization.Serializable

@Serializable
data class Currencies(
    val symbol: String,
    val name: String
)