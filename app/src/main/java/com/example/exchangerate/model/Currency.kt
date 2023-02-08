package com.example.exchangerate.model

import kotlinx.serialization.Serializable

@Serializable
data class Currency(
    val shortName: String,
    val name: String
)

fun testMethod() : Int {
    return 5
}

fun stringMethod(test: Int?): String {
    return if (test != null) {
        "You passed number: $test"
    } else {
        "You passed null"
    }
}