package com.example.tooltiptutorialapp.screens.questions

import CustomFullScreenOverlayWithTooltip
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tooltiptutorialapp.R
import com.example.tooltiptutorialapp.core.ui.BaseScreen
import com.example.tooltiptutorialapp.core.ui.QuestionWritingModel
import com.example.tooltiptutorialapp.core.ui.QuestionsOralCard
import com.example.tooltiptutorialapp.core.ui.ScreenHeader
import com.example.tooltiptutorialapp.core.ui.bottomNavBarGlobalOffsets
import com.example.tooltiptutorialapp.core.ui.bottomNavBarGlobalSizes
import com.example.tooltiptutorialapp.ui.theme.SecondColor
import com.example.tooltiptutorialapp.ui.theme.ThirdColor
import com.example.tooltiptutorialapp.ui.theme.TransparentSecondColor
import com.example.tooltiptutorialapp.util.utility_model.oralModels
import com.example.tooltiptutorialapp.util.utility_model.writingModels

@Composable
fun QuestionsScreen(
    modifier: Modifier,
    navController: NavController,
    selectedIndex: Int,
    onItemSelected: (Int, Offset) -> Unit,
    viewModel: QuestionsViewModel = viewModel()
) {
    val tabTitles = listOf("Writing 1", "Oral")
    val tabIcons = listOf(R.drawable.ic_pen, R.drawable.ic_microphone)
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    var currentOverlay by remember { mutableStateOf(Overlay.FIRST) }


    var showFirstTooltipOverLay by remember { mutableStateOf(true) }
    var showSecondOverLay by remember { mutableStateOf(false) }
    var showThirdOverLay by remember { mutableStateOf(false) }

    var buttonSize by remember { mutableStateOf(IntSize.Zero) }
    var buttonOffset by remember { mutableStateOf(Offset.Zero) }

    var firstGridItemOffset by remember { mutableStateOf(Offset.Zero) }
    var firstGridItemSize by remember { mutableStateOf(IntSize.Zero) }

    BaseScreen(
        navController = navController,
        selectedIndex = selectedIndex,
        onItemSelected = onItemSelected,
        overlayContent = {
            when (currentOverlay) {
                Overlay.FIRST -> {
                    CustomFullScreenOverlayWithTooltip(
                        onDismiss = {
                            currentOverlay = Overlay.SECOND
                        },
                        xOffset = bottomNavBarGlobalOffsets[2].x,
                        yOffset = bottomNavBarGlobalOffsets[2].y,
                        rectWidth = bottomNavBarGlobalSizes[2].width.toFloat(),
                        rectHeight = bottomNavBarGlobalSizes[2].height.toFloat()
                    )
                }

                Overlay.SECOND -> {
                    CustomFullScreenOverlayWithTooltip(
                        onDismiss = {
                            currentOverlay = Overlay.THIRD
                        },
                        rectWidth = buttonSize.width.toFloat(),
                        rectHeight = buttonSize.height.toFloat(),
                        xOffset = buttonOffset.x,
                        yOffset = buttonOffset.y,
                        outerCornerRadius = 0.dp,
                        innerCornerRadius = 0.dp
                    )
                }

                Overlay.THIRD -> {
                    CustomFullScreenOverlayWithTooltip(
                        onDismiss = {
                            currentOverlay = Overlay.NONE // Hide the overlay
                        },
                        rectWidth = firstGridItemSize.width.toFloat(),
                        rectHeight = firstGridItemSize.height.toFloat(),
                        xOffset = firstGridItemOffset.x,
                        yOffset = firstGridItemOffset.y,
                        abovePercent = 0.4f
                    )
                }

                Overlay.NONE -> {} // Do nothing if no overlay is to be shown
            }
        },
    ) {
        Column(
            modifier = modifier.wrapContentSize()
        ) {
            ScreenHeader("Questions")

            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier
                    .width(300.dp)
                    .padding(horizontal = 16.dp),
            ) {
                tabTitles.forEachIndexed { index, title ->
                    TabWithIcon(icon = tabIcons[index],
                        title = title,
                        isSelected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index })
                }
            }

            Button(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .onGloballyPositioned { layoutCoordinates ->
                        // Capture the button offset
                        buttonOffset = layoutCoordinates.localToWindow(Offset.Zero)
                        buttonSize = layoutCoordinates.size

                    },
                onClick = { /* Handle filter action */ },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = TransparentSecondColor)
            ) {
                Row(modifier = Modifier.wrapContentSize()) {
                    Text(
                        text = "Filter",
                        textAlign = TextAlign.Center,
                        color = ThirdColor,
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Icon(
                        painter = painterResource(R.drawable.ic_menu),
                        contentDescription = "Filter",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(15.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }

            AnimatedVisibility(visible = selectedTabIndex == 0) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    itemsIndexed(writingModels) { index, model ->
                        QuestionWritingModel(
                            model = model, modifier = Modifier

                        ) { offset, size ->
                            if (index == 0) {
                                firstGridItemOffset = offset
                                firstGridItemSize = size
                            }
                        }
                    }
                }
            }

            AnimatedVisibility(visible = selectedTabIndex == 1) {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp), modifier = Modifier.fillMaxWidth()
                ) {
                    items(oralModels) {
                        QuestionsOralCard(it)
                    }
                }
            }

        }


    }
}

@Composable
fun TabWithIcon(
    icon: Int, title: String, isSelected: Boolean, onClick: () -> Unit
) {
    Tab(
        selected = isSelected,
        onClick = onClick,
        selectedContentColor = SecondColor,
        unselectedContentColor = Color.LightGray,
        modifier = Modifier.clip(MaterialTheme.shapes.large)
    ) {
        Row {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier
                    .size(15.dp)
                    .align(Alignment.CenterVertically)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = title,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp)
            )
        }
    }
}

enum class Overlay {
    NONE,
    FIRST,
    SECOND,
    THIRD
}
