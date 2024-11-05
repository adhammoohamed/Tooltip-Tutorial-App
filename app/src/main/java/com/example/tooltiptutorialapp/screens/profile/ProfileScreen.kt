package com.example.tooltiptutorialapp.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.navigation.NavController
import com.example.tooltiptutorialapp.core.ui.BaseScreen

@Composable
fun ProfileScreen(
    modifier: Modifier,
    navController: NavController,
    selectedIndex: Int,
    onItemSelected: (Int, Offset) -> Unit
) {
    BaseScreen(
        navController = navController,
        selectedIndex = selectedIndex,
        overlayContent = {}

    ) {
        Column(modifier) {
            Text(text = "Profile Screen")
        }
    }
}