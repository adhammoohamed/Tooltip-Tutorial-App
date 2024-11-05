package com.example.tooltiptutorialapp.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.tooltiptutorialapp.navigation.TooltipTutorialNavGraph
import com.example.tooltiptutorialapp.ui.theme.TooltipTutorialAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            TooltipTutorialAppTheme {

                val navController = rememberNavController()
                var selectedIndex by remember { mutableStateOf(0) }
                var currentOffset by remember { mutableStateOf(Offset.Zero) }

                TooltipTutorialNavGraph(modifier = Modifier.padding(16.dp), navController, selectedIndex) { index, offset ->
                    selectedIndex = index
                    currentOffset = offset
                }
            }
        }
    }
}
