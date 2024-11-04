package com.example.tooltiptutorialapp.util.utility_model

import androidx.compose.ui.graphics.painter.Painter

data class BottomNavigationItem(
    val label: String,
    val selectedIcon: Painter,
    val unselectedIcon: Painter,
    val navigate: () -> Unit
)