package com.example.exchangerate.model

import kotlinx.serialization.Serializable

@Serializable
data class MyCurrency(
    val symbol: String,
    val name: String
)