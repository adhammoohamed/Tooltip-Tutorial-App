package com.example.tooltiptutorialapp.screens.profile

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.tooltiptutorialapp.navigation.Destination

fun NavGraphBuilder.profileScreen(modifier: Modifier) {
    composable<Destination.ProfileScreen> {
        ProfileScreen(modifier)
    }
}

fun NavController.navigateToProfileScreen() {
    navigate(Destination.ProfileScreen)
}