package com.example.exchangerate

import androidx.compose.ui.graphics.vector.ImageVector

// Data class/template for each item in the bottom bar
data class BarItem(
    val title: String,
    val image: ImageVector,
    val route: String
)
