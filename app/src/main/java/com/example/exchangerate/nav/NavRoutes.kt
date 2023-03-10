package com.example.exchangerate.nav

// Class to hold navigation objects/destination routes
sealed class NavRoutes(val route: String) {
    object Latest : NavRoutes("latest")
    object Converter :  NavRoutes("converter")
    object Currencies :  NavRoutes("currencies")
}
