package com.example.tooltiptutorialapp.screens.connect

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tooltiptutorialapp.R
import com.example.tooltiptutorialapp.core.ui.ScreenHeader
import com.example.tooltiptutorialapp.core.ui.SmallSectionTitle
import com.example.tooltiptutorialapp.core.ui.SuggestionCard
import com.example.tooltiptutorialapp.ui.theme.SecondColor
import com.example.tooltiptutorialapp.util.utility_model.studyPartnerList
import kotlinx.coroutines.launch


@Composable
fun ConnectScreen(modifier: Modifier) {
    // Obtain the ViewModel for managing UI-related data
    val viewModel: ConnectScreenViewModel = viewModel()

    // Define the tabs for navigation within the screen
    val pages = listOf("Suggestions", "Chat")

    // Initialize coroutine scope for handling asynchronous actions
    val scope = rememberCoroutineScope()
    var selectedTabIndex by remember { mutableIntStateOf(0) } // State variable to track the selected tab

    // Main layout of the Connect screen using LazyColumn for scrolling content
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header for the Connect screen
        item {
            ScreenHeader("Connect")
        }

        // Tab layout for navigating between Suggestions and Chat
        item {
            Column {
                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    // Create tabs dynamically from the pages list
                    pages.forEachIndexed { index, pageTitle ->
                        Tab(
                            modifier = Modifier
                                .clip(MaterialTheme.shapes.large) // Rounded corners for tabs
                                .zIndex(6f), // Bring tabs above other UI elements
                            selectedContentColor = SecondColor,
                            unselectedContentColor = Color.LightGray,
                            text = {
                                Text(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(5.dp))
                                        .padding(horizontal = 5.dp, vertical = 10.dp),
                                    text = pageTitle,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            selected = selectedTabIndex == index, // Highlight the selected tab
                            onClick = {
                                scope.launch {
                                    selectedTabIndex = index // Update the selected tab index
                                }
                            },
                        )
                    }
                }
            }
        }

        // Content displayed based on the selected tab
        when (selectedTabIndex) {
            0 -> { // Suggestions Tab
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            SmallSectionTitle("Suggested Study Partners") // Title for the suggestions section
                            Spacer(modifier = Modifier.weight(1f)) // Spacer for layout balance
                            IconButton(
                                modifier = Modifier.size(30.dp),
                                onClick = {
                                    // Handle filter button click
                                }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_menu),
                                    contentDescription = "Filter", // Icon description for accessibility
                                    tint = SecondColor
                                )
                            }
                        }
                    }
                }

                // List of suggested study partners displayed as cards
                items(studyPartnerList) {
                    SuggestionCard(it) // Display each study partner as a suggestion card
                }
            }

            1 -> { // Chat Tab
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center // Center chat content
                    ) {
                        Text("Chat Content Here") // Placeholder for chat content
                    }
                }
            }
        }
    }
}