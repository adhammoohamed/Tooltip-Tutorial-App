package com.example.tooltiptutorialapp.screens.profile

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.tooltiptutorialapp.navigation.Destination

fun NavGraphBuilder.profileScreen(
    modifier: Modifier,
    navController: NavController,
    selectedIndex: Int,
    onItemSelected: (Int, Offset) -> Unit
) {
    composable<Destination.ProfileScreen> {
        ProfileScreen(
            modifier = modifier,
            navController = navController,
            selectedIndex = selectedIndex,
            onItemSelected = onItemSelected
        )
    }
}

fun NavController.navigateToProfileScreen() {
    navigate(Destination.ProfileScreen)
}