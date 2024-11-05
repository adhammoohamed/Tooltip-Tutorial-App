package com.example.tooltiptutorialapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.tooltiptutorialapp.screens.connect.connectScreen
import com.example.tooltiptutorialapp.screens.home.homeScreen
import com.example.tooltiptutorialapp.screens.profile.profileScreen
import com.example.tooltiptutorialapp.screens.questions.questionsScreen
import com.example.tooltiptutorialapp.screens.tools.toolsScreen

@Composable
fun TooltipTutorialNavGraph(
    modifier: Modifier,
    navController: NavHostController,
    selectedIndex: Int,
    onItemSelected: (Int, Offset) -> Unit
) {
    NavHost(navController = navController, startDestination = Destination.HomeScreen) {
        homeScreen(
            modifier = modifier,
            navController = navController,
            selectedIndex = selectedIndex,
            onItemSelected = onItemSelected
        )

        connectScreen(
            modifier = modifier,
            navController = navController,
            selectedIndex = selectedIndex,
            onItemSelected = onItemSelected
        )

        questionsScreen(
            modifier = modifier,
            navController = navController,
            selectedIndex = selectedIndex,
            onItemSelected = onItemSelected
        )

        toolsScreen(
            modifier = modifier,
            navController = navController,
            selectedIndex = selectedIndex,
            onItemSelected = onItemSelected
        )

        profileScreen(
            modifier = modifier,
            navController = navController,
            selectedIndex = selectedIndex,
            onItemSelected = onItemSelected
        )
    }
}