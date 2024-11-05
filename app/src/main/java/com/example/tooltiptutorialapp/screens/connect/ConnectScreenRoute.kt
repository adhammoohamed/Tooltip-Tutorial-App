package com.example.tooltiptutorialapp.screens.connect

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.tooltiptutorialapp.navigation.Destination

fun NavGraphBuilder.connectScreen(modifier: Modifier, navController: NavController, selectedIndex: Int, onItemSelected: (Int, Offset) -> Unit) {
    composable<Destination.ConnectScreen> {
        ConnectScreen(modifier, navController, selectedIndex, onItemSelected)
    }
}

fun NavController.navigateToConnectScreen() {
    navigate(Destination.ConnectScreen)
}