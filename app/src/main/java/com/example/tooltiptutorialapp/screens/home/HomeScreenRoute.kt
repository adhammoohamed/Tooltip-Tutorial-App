package com.example.tooltiptutorialapp.screens.home

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.tooltiptutorialapp.navigation.Destination

fun NavGraphBuilder.homeScreen(modifier: Modifier) {
    composable<Destination.HomeScreen> {
        HomeScreen(modifier = modifier)
    }
}

fun NavController.navigateToHomeScreen() {
    navigate(Destination.HomeScreen)
}