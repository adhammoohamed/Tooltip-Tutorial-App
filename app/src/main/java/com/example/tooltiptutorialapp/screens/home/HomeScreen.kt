package com.example.tooltiptutorialapp.screens.home

import CustomFullScreenOverlayWithTooltip
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tooltiptutorialapp.R
import com.example.tooltiptutorialapp.TooltipTutorialApp
import com.example.tooltiptutorialapp.core.ui.BaseScreen
import com.example.tooltiptutorialapp.core.ui.OnboardingOverlay
import com.example.tooltiptutorialapp.core.ui.ScreenHeader
import com.example.tooltiptutorialapp.core.ui.SectionTitle
import com.example.tooltiptutorialapp.core.ui.UnitCircleView
import com.example.tooltiptutorialapp.core.ui.Username
import com.example.tooltiptutorialapp.core.ui.bottomNavBarGlobalOffsets
import com.example.tooltiptutorialapp.core.ui.bottomNavBarGlobalSizes
import com.example.tooltiptutorialapp.ui.theme.SecondColor
import com.example.tooltiptutorialapp.util.utility_model.unitList

@Composable
fun HomeScreen(
    modifier: Modifier,
    navController: NavController,
    selectedIndex: Int,
    onItemSelected: (Int, Offset) -> Unit
) {
    val sharedPreferencesManager =
        (LocalContext.current.applicationContext as TooltipTutorialApp).sharedPreferencesManager

    var showTooltipOverLay by remember { mutableStateOf(false) }
    var showOnboardingOverlay by remember { mutableStateOf(true) }

    val viewModel: HomeScreenViewModel = viewModel { HomeScreenViewModel(sharedPreferencesManager) }
    BaseScreen(
        navController = navController,
        selectedIndex = selectedIndex,
        overlayContent = {
            if (showOnboardingOverlay) {
                OnboardingOverlay {
                    showOnboardingOverlay = false
                    showTooltipOverLay = true
                }
            }
            if (showTooltipOverLay && bottomNavBarGlobalOffsets.size > selectedIndex) {
                CustomFullScreenOverlayWithTooltip(
                    onDismiss = {
                        showTooltipOverLay = false
                    },
                    xOffset = bottomNavBarGlobalOffsets[0].x,
                    yOffset = bottomNavBarGlobalOffsets[0].y,
                    rectWidth = bottomNavBarGlobalSizes[0].width.toFloat(),
                    rectHeight = bottomNavBarGlobalSizes[0].height.toFloat()
                )
            }
        }
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                // Header with notification icon
                item {
                    UserHeader()
                    Username(stringResource(id = R.string.user_name))
                    Spacer(Modifier.height(16.dp))
                    SectionTitle(stringResource(id = R.string.study_plan))
                }

                // List of study units
                itemsIndexed(unitList) { index, unit ->
                    UnitCircleView(unit, index, unitList.size)
                }
            }

            // Onboarding overlay if applicable
            if (viewModel.isOverlayVisible) {
                OnboardingOverlay {
                    viewModel.hideOverlay()
                }
            }
        }
    }
}

@Composable
fun UserHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        ScreenHeader(stringResource(id = R.string.home))
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            modifier = Modifier.size(30.dp),
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "",
            tint = SecondColor,
        )
    }
}


