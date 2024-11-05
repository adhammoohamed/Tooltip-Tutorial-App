package com.example.tooltiptutorialapp.screens.questions

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.tooltiptutorialapp.navigation.Destination

fun NavGraphBuilder.questionsScreen(modifier: Modifier, navController: NavController, selectedIndex: Int, onItemSelected: (Int, Offset) -> Unit) {
    composable<Destination.QuestionsScreen> {
        QuestionsScreen(modifier, navController, selectedIndex, onItemSelected)
    }
}


fun NavController.navigateToQuestionsScreen() {
    navigate(Destination.QuestionsScreen)
}