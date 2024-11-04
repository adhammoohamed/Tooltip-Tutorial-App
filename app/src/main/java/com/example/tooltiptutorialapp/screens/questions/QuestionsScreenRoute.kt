package com.example.tooltiptutorialapp.screens.questions

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.tooltiptutorialapp.navigation.Destination

fun NavGraphBuilder.questionsScreen(modifier: Modifier) {
    composable<Destination.QuestionsScreen> {
        QuestionsScreen(modifier)
    }
}


fun NavController.navigateToQuestionsScreen() {
    navigate(Destination.QuestionsScreen)
}