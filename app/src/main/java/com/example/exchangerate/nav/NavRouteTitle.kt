package com.example.exchangerate.nav

import android.content.Context
import com.example.exchangerate.R

// Function to get title string of the route
fun getTitleByRoute(context: Context, route: String?): String {
    return when (route) {
        "latest" -> context.getString(R.string.latest_conversion)
        // other cases
        "converter" -> context.getString(R.string.currency_converter)
        else -> context.getString(R.string.currencies_list)
    }
}