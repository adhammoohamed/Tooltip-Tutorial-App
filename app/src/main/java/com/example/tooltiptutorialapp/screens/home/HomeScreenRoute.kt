package com.example.tooltiptutorialapp.screens.home

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.tooltiptutorialapp.navigation.Destination

fun NavGraphBuilder.homeScreen(
    modifier: Modifier,
    selectedIndex: Int,
    navController: NavController,
    onItemSelected: (Int, Offset) -> Unit
) {
    composable<Destination.HomeScreen> {
        HomeScreen(
            modifier = modifier,
            navController = navController,
            selectedIndex = selectedIndex,
            onItemSelected = onItemSelected
        )
    }
}

fun NavController.navigateToHomeScreen() {
    navigate(Destination.HomeScreen)
}