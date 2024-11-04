package com.example.tooltiptutorialapp.screens.connect

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.tooltiptutorialapp.navigation.Destination

fun NavGraphBuilder.connectScreen(modifier: Modifier) {
    composable<Destination.ConnectScreen> {
        ConnectScreen(modifier)
    }
}

fun NavController.navigateToConnectScreen() {
    navigate(Destination.ConnectScreen)
}