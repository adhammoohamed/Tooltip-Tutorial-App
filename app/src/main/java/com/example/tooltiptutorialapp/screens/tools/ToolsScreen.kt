package com.example.tooltiptutorialapp.screens.tools

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.navigation.NavController
import com.example.tooltiptutorialapp.core.ui.BaseScreen

@Composable
fun ToolsScreen(
    modifier: Modifier,
    navController: NavController,
    selectedIndex: Int,
    onItemSelected: (Int, Offset) -> Unit
) {

    BaseScreen(
        navController = navController,
        selectedIndex = selectedIndex,
        onItemSelected = onItemSelected,
        overlayContent = {}

    ) {
        Column(modifier) {
            Text(text = "Tools Screen")
        }
    }
}