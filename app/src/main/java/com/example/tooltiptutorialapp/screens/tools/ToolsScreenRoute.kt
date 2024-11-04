package com.example.tooltiptutorialapp.screens.tools

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.tooltiptutorialapp.navigation.Destination

fun NavGraphBuilder.toolsScreen(modifier: Modifier) {
    composable<Destination.ToolsScreen> {
        ToolsScreen(modifier)
    }
}

fun NavController.navigateToToolsScreen() {
    navigate(Destination.ToolsScreen)
}