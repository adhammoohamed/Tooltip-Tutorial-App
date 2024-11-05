package com.example.tooltiptutorialapp.screens.connect

import CustomFullScreenOverlayWithTooltip
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tooltiptutorialapp.R
import com.example.tooltiptutorialapp.core.ui.BaseScreen
import com.example.tooltiptutorialapp.core.ui.ScreenHeader
import com.example.tooltiptutorialapp.core.ui.SmallSectionTitle
import com.example.tooltiptutorialapp.core.ui.SuggestionCard
import com.example.tooltiptutorialapp.core.ui.bottomNavBarGlobalOffsets
import com.example.tooltiptutorialapp.core.ui.bottomNavBarGlobalSizes
import com.example.tooltiptutorialapp.ui.theme.SecondColor
import com.example.tooltiptutorialapp.util.utility_model.studyPartnerList


@Composable
fun ConnectScreen(modifier: Modifier, navController: NavController, selectedIndex: Int, onItemSelected: (Int, Offset) -> Unit) {
    val viewModel: ConnectScreenViewModel = viewModel()
    val pages =
        listOf(stringResource(id = R.string.suggestions), stringResource(id = R.string.chat))
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var showOverLay by remember { mutableStateOf(true) }

    BaseScreen(
        navController = navController,
        selectedIndex = selectedIndex,
        onItemSelected = onItemSelected,
        overlayContent = {
            if (showOverLay && bottomNavBarGlobalOffsets.size > selectedIndex) {
                CustomFullScreenOverlayWithTooltip(
                    onDismiss = {
                        showOverLay = false
                    },
                    xOffset = bottomNavBarGlobalOffsets[selectedIndex].x,
                    yOffset = bottomNavBarGlobalOffsets[selectedIndex].y,
                    rectWidth = bottomNavBarGlobalSizes[selectedIndex].width.toFloat(),
                    rectHeight = bottomNavBarGlobalSizes[selectedIndex].height.toFloat()
                )
            }
        }
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
        ) {
            item {
                ScreenHeader(stringResource(id = R.string.connect))
            }

            item {
                ConnectTabRow(
                    pages = pages,
                    selectedTabIndex = selectedTabIndex,
                    onTabSelected = { selectedTabIndex = it }
                )
            }

            when (selectedTabIndex) {
                0 -> item { SuggestionsTab() }
                1 -> item { ChatTab() }
            }
        }
    }
}

@Composable
fun ConnectTabRow(
    pages: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier.fillMaxWidth()
    ) {
        pages.forEachIndexed { index, pageTitle ->
            Tab(
                modifier = Modifier.clip(MaterialTheme.shapes.large),
                selectedContentColor = SecondColor,
                unselectedContentColor = Color.LightGray,
                text = {
                    Text(
                        text = pageTitle,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 10.dp)
                            .clip(RoundedCornerShape(5.dp))
                    )
                },
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) }
            )
        }
    }
}

@Composable
fun SuggestionsTab() {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SmallSectionTitle(stringResource(id = R.string.suggested_study_partners))
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                modifier = Modifier.size(30.dp),
                onClick = { /* Handle filter click */ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "",
                    tint = SecondColor
                )
            }
        }

        studyPartnerList.forEach {
            SuggestionCard(it)
        }
    }
}

@Composable
fun ChatTab() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(stringResource(id = R.string.chat_content_placeholder))
    }
}
