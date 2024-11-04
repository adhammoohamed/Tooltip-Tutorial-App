package com.example.tooltiptutorialapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tooltiptutorialapp.screens.connect.connectScreen
import com.example.tooltiptutorialapp.screens.home.homeScreen
import com.example.tooltiptutorialapp.screens.profile.profileScreen
import com.example.tooltiptutorialapp.screens.questions.questionsScreen
import com.example.tooltiptutorialapp.screens.tools.toolsScreen

@Composable
fun TooltipTutorialNavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = Destination.HomeScreen) {
        homeScreen(modifier)

        connectScreen(modifier)

        questionsScreen(modifier)

        toolsScreen(modifier)

        profileScreen(modifier)
    }
}