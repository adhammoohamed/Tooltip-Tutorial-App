package com.example.tooltiptutorialapp.navigation
import kotlinx.serialization.Serializable

@Serializable
sealed interface Destination {
    @Serializable
    data object HomeScreen : Destination

    @Serializable
    data object ConnectScreen : Destination

    @Serializable
    data object QuestionsScreen : Destination

    @Serializable
    data object ToolsScreen : Destination

    @Serializable
    data object ProfileScreen : Destination

}